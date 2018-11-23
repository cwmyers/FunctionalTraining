package ft.higherkinds

import cats.effect.IO

import scala.io.StdIn

/**
  * In this exercise we have some strong opinions on how we represent effects (using IO),
  * how we represent errors (using Either) and how we represent optionality (with Options).
  *
  * This is quite clear but you can see that all these types don't mesh well together, plus there
  * are other ways of representing these concepts such as using `Task` or `Future`.
  *
  * How can we refactor to make more generic and allow the steps to mesh better together?
  *
  * Step 1) Lift the Either and the Option into IO to get rid of the stair stepping
  * Step 2) Refactor to use the typeclass `Sync`
  * Step 3) Refactor to use typeclass MonadError
  * Step 4) How far can you push it?
  */

object Generic {

  val messages = Map(
    "1" -> "Hello!",
    "2" -> "You are amazing",
    "3" -> "Please tell me how to monad",
    "4" -> "Three is the magic number"
  )

  def main(args: Array[String]): Unit = {
    selectMessage.attempt.unsafeRunSync() match {
      case Right(_) => ()
      case Left(e) => println(e.getMessage)
    }
  }

  def selectMessage: IO[Unit] = for {
    _ <- printLine("Please enter a number")
    input <- readLine
    number <- validateNumber(input) match {
        // Ugly stair stepping, how can we avoid?
      case Right(m) => IO(m)
      case Left(e) => IO.raiseError(new RuntimeException(e))
    }
    _ <- printLine(s"You entered number $number")
    message <- getMessage(number) match {
      // Move ugly stair stepping, this time of a different type!
      case Some(m) => IO(m)
      case None => IO.raiseError(new RuntimeException("No message for that number"))
    }
    _ <- printLine(message)
  } yield ()


  private def printLine(string: String): IO[Unit] = IO {
    println(string)
  }


  private def readLine: IO[String] = IO {
    StdIn.readLine()
  }


  def validateNumber(input: String): Either[String, String] = {
    if (input.matches("^\\d+$")) {
      Right(input)
    } else {
      Left("Not a valid number")
    }
  }

  def getMessage(number:String): Option[String] = messages.get(number)
}
