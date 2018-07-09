package com.github.dnvriend.publisher.tweet

import com.github.dnvriend.kinesis.{KinesisProducer, KinesisRecord}
import com.github.dnvriend.lambda.annotation.ScheduleConf
import com.github.dnvriend.lambda.annotation.policy.{AmazonKinesisFullAccess, CloudWatchFullAccess, ComprehendFullAccess, ComprehendReadOnly}
import com.github.dnvriend.lambda.{SamContext, ScheduledEvent, ScheduledEventHandler}
import play.api.libs.json.Json

// rate can be can be minute(s), hour(s), or day(s)
@ComprehendReadOnly
@CloudWatchFullAccess
@AmazonKinesisFullAccess
@ScheduleConf(
  schedule = "rate(1 minute)",
  reservedConcurrentExecutions = 1
)
class TweetPublisher extends ScheduledEventHandler {

  override def handle(event: ScheduledEvent, ctx: SamContext): Unit = {
    val records: List[KinesisRecord] = {
      TweetGenerator
        .tweetList(500)
        .map(Tweet.analyzeTweet)
        .map(tweet => (tweet.id, Json.toJson(tweet)))
        .map({ case (id, json) => KinesisRecord(id, (json.toString() + "\n").getBytes("UTF-8")) })
    }
    KinesisProducer(ctx).produce("import:tweet-data-segment:dnvriend-tweet-data-stream", records)
    println(s"Number of simulated tweets produced: ${records.size}")
  }
}
