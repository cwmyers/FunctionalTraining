name := "FunctionalTraining"

organization := "cwmyers.github.io"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.4.0",
  "org.typelevel" %% "cats-effect" % "1.0.0",
  "org.specs2" %% "specs2-core" % "4.3.4" % "test",
  "org.specs2" %% "specs2-cats" % "4.3.4" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

initialCommands := "import com.rea.higherorder._; import com.rea.typesafety._; import Composing._; import ValidationExercises._"
