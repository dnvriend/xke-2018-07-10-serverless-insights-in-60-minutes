
lazy val `xke-serverless-insights` = (project in file("."))
  .disablePlugins(SAMPlugin)
  .aggregate(
    `tweet-publisher-simulator`
  )

lazy val `tweet-data-segment` = (project in file("tweet-data-segment"))
  .settings(samStage := stage.value)
  .enablePlugins(SAMPlugin)

 lazy val `tweet-publisher-simulator` = (project in file("tweet-publisher-simulator"))
   .settings(samStage := stage.value)
   .enablePlugins(SAMPlugin)
   .enablePlugins(TweetSettings)
