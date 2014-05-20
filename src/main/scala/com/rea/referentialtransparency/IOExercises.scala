package com.rea.referentialtransparency

import scalaz._, Scalaz._
import scalaz.effect.IO

object IOExercises {

  def readLineFromConsole(): IO[String] = ???

  def writeToConsole(s: String): IO[Unit] = ???

  def readFromConsoleAndWriteToConsole: IO[Unit] = ???

  def run(effect: IO[Unit]): Unit = ???

}
