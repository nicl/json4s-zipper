import java.util.jar._

name := "json4s-zipper"

version in ThisBuild := "1.0-SNAPSHOT"

organization in ThisBuild := "com.gu"

scalaVersion in ThisBuild := "2.10.0"

crossScalaVersions in ThisBuild := Seq("2.9.2", "2.10.0")

publishArtifact := true

packageOptions in ThisBuild <+= (version, name) map { (v, n) =>
  Package.ManifestAttributes(
    Attributes.Name.IMPLEMENTATION_VERSION -> v,
    Attributes.Name.IMPLEMENTATION_TITLE -> n,
    Attributes.Name.IMPLEMENTATION_VENDOR -> "guardian.co.uk"
  )
}

publishTo in ThisBuild <<= (version) { version: String =>
    val publishType = if (version.endsWith("SNAPSHOT")) "snapshots" else "releases"
    Some(
        Resolver.file(
            "guardian github " + publishType,
            file(System.getProperty("user.home") + "/guardian.github.com/maven/repo-" + publishType)
        )
    )
}

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-core" % "3.1.0",
  "org.scalaz" %% "scalaz-core" % "7.0.0-M8",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test",
  "org.json4s" %% "json4s-native" % "3.1.0" % "test"
)