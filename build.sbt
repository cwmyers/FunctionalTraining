name := "FunctionalTraining"

organization := "rea-group.com"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq("org.scalaz" %% "scalaz-core" % "7.0.6", "org.specs2" %% "specs2" % "2.3.11")

initialCommands := "import com.rea.higherorder._; import com.rea.typesafety._; import Composing._; import ValidationExercises._"
