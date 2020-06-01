name := "AkkaStreamsTechPrimer"
version := "0.1"
scalaVersion := "2.13.2"

val akkaStreamsDependency = Seq (
  "com.typesafe.akka" %% "akka-stream" % "2.6.5"
)

val json4sDep = Seq (
  "org.json4s" %% "json4s-core" % "3.6.8",
  "org.json4s" %% "json4s-native" % "3.6.8"
)

val commonDep = Seq (
  "commons-collections" % "commons-collections" % "3.2.2",
  "com.univocity" % "univocity-parsers" % "2.8.4",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

libraryDependencies ++= akkaStreamsDependency ++ commonDep ++ json4sDep