package ft.referentialtransparency

import cats.effect.IO

object IOExercises {

  def readLineFromConsole(): IO[String] = ???

  def writeToConsole(s: String): IO[Unit] = ???

  def readFromConsoleAndWriteToConsole: IO[Unit] = ???

  def run(effect: IO[Unit]): Unit = ???

}
