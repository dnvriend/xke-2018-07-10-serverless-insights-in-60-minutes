addSbtPlugin("com.github.dnvriend" % "sbt-sam-plugin" % "1.0.31-SNAPSHOT")

resolvers += Resolver.url("bintray-dnvriend-ivy-sbt-plugins", url("http://dl.bintray.com/dnvriend/sbt-plugins"))(Resolver.ivyStylePatterns)
resolvers += Resolver.bintrayRepo("dnvriend", "maven") 
