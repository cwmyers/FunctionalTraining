name := "FunctionalTraining"

organization := "cwmyers.github.io"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.4.0",
  "org.typelevel" %% "cats-effect" % "1.0.0",
  "org.typelevel" %% "cats-mtl-core" % "0.4.0",
  "org.typelevel" %% "mouse" % "0.20",
  "io.monix" %% "monix" % "3.0.0-RC2",
  "org.specs2" %% "specs2-core" % "4.3.4" % "test",
  "org.specs2" %% "specs2-cats" % "4.3.4" % "test"
)

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.8")

scalacOptions += "-Ypartial-unification"

scalacOptions in Test ++= Seq("-Yrangepos")

