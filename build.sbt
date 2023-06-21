import Dependencies._

ThisBuild / scalaVersion := "2.13.11"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"
lazy val cucumberVersion = "8.14.1"

lazy val root = (project in file("."))
  .settings(
    name := "dr2-e2e-tests",
    libraryDependencies ++= Seq(
      // https://mvnrepository.com/artifact/com.amazonaws/aws-lambda-java-core
      "com.amazonaws" % "aws-lambda-java-core" % "1.2.2" % Test,
      "io.cucumber" %% "cucumber-scala" % cucumberVersion % Test,
      "io.cucumber" % "cucumber-junit-platform-engine" % "7.11.1" % Test,
      "io.cucumber" % "cucumber-junit" % "7.12.1" % Test,
      "org.scalatest" %% "scalatest" % "3.2.15" % Test,
      "com.github.sbt" % "junit-interface" % "0.13.3" % Test,
      "uk.gov.nationalarchives" %% "da-s3-client" % "0.1.3" % Test,
      "co.fs2" %% "fs2-reactive-streams" % "3.7.0" % Test
    ),
    libraryDependencies += munit % Test
  )

Project.inConfig(Test)(baseAssemblySettings)
(assembly / assemblyMergeStrategy) := {
  case PathList("META-INF", x, xs@_*) if x.toLowerCase == "services" => MergeStrategy.filterDistinctLines
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case PathList("reference.conf") => MergeStrategy.concat
  case _ => MergeStrategy.first
}

(assembly / assemblyJarName) := "e2e-tests.jar"
