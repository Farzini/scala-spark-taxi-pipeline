ThisBuild / scalaVersion := "2.12.18"

lazy val sparkVersion = "3.5.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.typelevel"    %% "cats-core" % "2.10.0",
  "com.github.pureconfig" %% "pureconfig" % "0.17.6",
  "ch.qos.logback"   %  "logback-classic" % "1.5.6",
  "org.scalatest"    %% "scalatest" % "3.2.18" % Test
)

Compile / run / fork := true

// Assembly (fat-jar) settings
import sbtassembly.AssemblyPlugin.autoImport._
assembly / mainClass := Some("com.example.Main")
assembly / assemblyJarName := "scala-spark-taxi-pipeline-assembly.jar"
