package ft.higherkinds

import cats.ApplicativeError
import cats.effect.{IO, Sync}
import cats.implicits._

import scala.io.StdIn
import scala.language.higherKinds

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
  * Step 3) Refactor to use typeclass MonadError or ApplicativeError
  * Step 4) How far can you push it?
  */

object Generic {

  type HasThrowableError[A[_]] = ApplicativeError[A, Throwable]


  val messages = Map(
    "1" -> "Hello!",
    "2" -> "You are amazing",
    "3" -> "Please tell me how to monad",
    "4" -> "Three is the magic number"
  )

  def main(args: Array[String]): Unit = {
    selectMessage[IO].attempt.unsafeRunSync match {
      case Right(_) => ()
      case Left(e) => println(e.getMessage)
    }
  }

  def selectMessage[F[_] : Sync]: F[Unit] = for {
    // Observe how we have been able to flatten for-comprehension
    _ <- printLine("Please enter a number")
    input <- readLine
    number <- validateNumber[F](input)
    _ <- printLine(s"You entered number $number")
    message <- getMessage[F](number, "No message for that number")
    _ <- printLine(message)
  } yield ()


  private def printLine[F[_] : Sync](string: String): F[Unit] = Sync[F].delay {
    println(string)
  }


  private def readLine[F[_] : Sync]: F[String] = Sync[F].delay {
    StdIn.readLine()
  }


  // Each function only demands what it cares about.  Here all we need is
  //
  def validateNumber[F[_] : HasThrowableError](input: String): F[String] = {
    val AE = ApplicativeError[F, Throwable]
    if (input.matches("^\\d+$")) {
      AE.pure(input)
    } else {
      AE.raiseError(new RuntimeException("Not a valid number"))
    }
  }

  def getMessage[F[_] : HasThrowableError](number: String, message:String): F[String] = {
    ApplicativeError.liftFromOption[F](messages.get(number), new RuntimeException(message))
  }

}
