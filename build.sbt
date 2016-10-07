
lazy val root = (project in file(".")).
  settings(
    name := "awslambda-upload",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.11.8",
    organization := "org.aj",
    retrieveManaged := true
  )

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
  "com.amazonaws" % "aws-lambda-java-events" % "1.0.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.4"
)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*)  => MergeStrategy.discard
  case x                              => MergeStrategy.first
}