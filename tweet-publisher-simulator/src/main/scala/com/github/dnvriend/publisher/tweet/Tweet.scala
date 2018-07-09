package com.github.dnvriend.publisher.tweet

import com.amazonaws.services.comprehend.model.DetectSentimentResult
import com.github.dnvriend.comprehend.Comprehend
import play.api.libs.json.{Format, Json}

import scala.util.Try

/**
  * Tweet provides JSON serialization capabilities for Tweet records
  */
object Tweet {
  implicit val format: Format[Tweet] = Json.format

  def analyzeTweet(tweet: Tweet): Tweet = {
    val client = Comprehend.client()
    (for {
      language <- Try(Comprehend.detectDominantLanguage(tweet.text, client))
      lng = language.headOption.map(_.getLanguageCode).getOrElse("en")
      sentiment <- Try(Comprehend.detectSentiment(tweet.text, lng, client))
      entities <- Try(Comprehend.detectEntities(tweet.text, lng, client))
      phrases <- Try(Comprehend.detectKeyPhrases(tweet.text, lng, client))
    } yield {
      tweet
        .copy(sentimentScore = SentimentScore.fromSentiment(sentiment))
        .copy(positiveScore = sentiment.getSentimentScore.getPositive)
        .copy(negativeScore = sentiment.getSentimentScore.getNegative)
        .copy(mixedScore = sentiment.getSentimentScore.getMixed)
        .copy(neutralScore = sentiment.getSentimentScore.getNeutral)
        .copy(dominantLanguage = language.headOption.map(_.getLanguageCode).getOrElse(""))
        .copy(geoLocation = GeoLocation(tweet.lat, tweet.lng))
        .copy(dominantLanguages = DominantLanguage.fromDominantLanguages(language.map(x => x.getLanguageCode -> x.getScore.toFloat)))
        .copy(keyPhrases = KeyPhrase.fromScore(phrases.map(x => x.getText -> x.getScore.toFloat)))
        .copy(entities = Entity.fromScore(entities.map(x => (x.getText, x.getScore.toFloat, x.getType))))
    }).recover { case t: Throwable =>
      t.printStackTrace()
      tweet
    }.getOrElse(tweet)
  }
}

/**
  * Tweet defines the data definition of a tweet record
  */
final case class Tweet(
                        id: String,
                        createdAt: Long,
                        text: String,
                        city: String,
                        lat: Double,
                        lng: Double,
                        dominantLanguage: String = "",
                        positiveScore: Float = 0,
                        negativeScore: Float = 0,
                        neutralScore: Float = 0,
                        mixedScore: Float = 0,
                        sentimentScore: SentimentScore = SentimentScore(),
                        geoLocation: GeoLocation = GeoLocation(),
                        keyPhrases: List[KeyPhrase] = List.empty,
                        entities: List[Entity] = List.empty,
                        dominantLanguages: List[DominantLanguage] = List.empty,
                      )


object SentimentScore {
  implicit val format: Format[SentimentScore] = Json.format

  def fromSentiment(sentiment: DetectSentimentResult): SentimentScore = {
    val score = sentiment.getSentimentScore
    SentimentScore(score.getPositive, score.getNegative, score.getNeutral, score.getMixed)
  }
}

final case class SentimentScore(
                               positive: Float = 0,
                               negative: Float = 0,
                               neutral: Float = 0,
                               mixed: Float = 0,
                               )

object GeoLocation {
  implicit val format: Format[GeoLocation] = Json.format
}

final case class GeoLocation(lat: Double = 0, lng: Double = 0)

object KeyPhrase {
  implicit val format: Format[KeyPhrase] = Json.format

  def fromScore(xs: List[(String, Float)]): List[KeyPhrase] = {
    xs.map {
      case (code, score) => KeyPhrase(code, score)
    }
  }
}

final case class KeyPhrase(text: String, score: Float)

object Entity {
  implicit val format: Format[Entity] = Json.format

  def fromScore(xs: List[(String, Float, String)]): List[Entity] = {
    xs.map {
      case (code, score, etype) => Entity(code, score, etype)
    }
  }
}

final case class Entity(text: String, score: Float, entityType: String)

object DominantLanguage {
  implicit val format: Format[DominantLanguage] = Json.format

  def fromDominantLanguages(xs: List[(String, Float)]): List[DominantLanguage] = {
    xs.map {
      case (code, score) => DominantLanguage(code, score)
    }
  }
}

final case class DominantLanguage(languageCode: String, score: Float)