name := "FunctionalTraining"

organization := "cwmyers.github.io"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.0.0",
  "org.typelevel" %% "cats-effect" % "2.1.2",
  "org.specs2" %% "specs2-core" % "4.9.2" % Test,
  "org.specs2" %% "specs2-cats" % "4.9.2" % Test
)

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

scalacOptions in Test ++= Seq("-Yrangepos")

initialCommands := "import com.ft.rea.higherorder._; import com.ft.rea.typesafety._; import Composing._; import ValidationExercises._"
