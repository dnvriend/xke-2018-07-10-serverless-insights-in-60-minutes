import sbt.{Def, _}
import sbt.Keys._
import sbt.plugins.JvmPlugin

object GlobalSettings extends AutoPlugin {
  override def trigger: PluginTrigger = allRequirements

  override def requires: JvmPlugin.type = plugins.JvmPlugin

  object autoImport {
    lazy val stage: SettingKey[String] = SettingKey("stage", "The stage")
  }

  import autoImport._

  override def globalSettings: Seq[Def.Setting[_]] = Seq(
    organization := "io.binx",
    scalaVersion := "2.12.6",
    resolvers += Resolver.bintrayRepo("dnvriend", "maven"),
    version := "1.0.0-SNAPSHOT",
    stage := "bnx",
  )
}
