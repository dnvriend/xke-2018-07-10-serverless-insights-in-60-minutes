import sbt.{Def, _}
import sbt.Keys._
import sbt.plugins.JvmPlugin

object TweetSettings extends AutoPlugin {
  override def trigger: PluginTrigger = noTrigger

  override def requires: JvmPlugin.type = plugins.JvmPlugin

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    libraryDependencies ++= libDeps,
  )

  lazy val libDeps: Seq[ModuleID] = Seq(
    "com.github.dnvriend" %% "sam-annotations" % Versions.samVersion,
    "com.github.dnvriend" %% "sam-lambda" % Versions.samVersion,
    "com.amazonaws" % "aws-lambda-java-core" % Versions.awsLambdaCore,
    "com.typesafe.play" %% "play-json" % Versions.playframework,
    "com.typesafe" % "config" % Versions.typesafeConfig,
    "com.amazonaws" % "aws-java-sdk-kinesis" % Versions.awsSdk,
    "com.amazonaws" % "aws-java-sdk-sns" % Versions.awsSdk,
    "com.fasterxml.jackson.dataformat" % "jackson-dataformat-cbor" % Versions.jsonCbor,
    "org.scalacheck" %% "scalacheck" % Versions.scalaCheckVersion,
  )
}
