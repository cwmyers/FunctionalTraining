package ft.cowsandbulls

import cats.implicits._
import ft.cowsandbulls.Concurrency.fromOption
import monix.eval.Task
import monix.reactive.Observable
import mouse.all._

import scala.io.StdIn
import scala.util.Random

sealed abstract case class Digit(value: Int) {
  def equalTo(digit: Digit): Boolean = value == digit.value
}

object Digit {
  def fromInt(i: Int): Option[Digit] = if (i >= 0 && i <= 9) Some(new Digit(i) {}) else None
}

sealed abstract case class Code(value: List[Digit]) {
  def show: String = value.map(_.value).mkString
}

object Code {
  def fromList(list: List[Digit]): Option[Code] =
    if (list.length == 4) Some(new Code(list) {}) else None
}

case class Secret(code: Code)

case class Guess(code: Code)

object Concurrency {
  def fromOption[A](o: Option[A], errorMessage: String): Task[A] = o match {
    case Some(a) => Task.delay(a)
    case None => Task.raiseError(new Throwable(errorMessage))
  }
}

sealed trait Result {
  def show: String
}

case object FullMatch extends Result {
  def show = "FullMatch"
}

case class PartialMatch(cows: Int, bulls: Int) extends Result {
  def show = s"cows = $cows bulls = $bulls"
}


object CowsAndBulls {

  def getInput: Task[Guess] =
    Task.eval(StdIn.readLine()).flatMap(s => fromOption(parseInput(s), "Invalid guess"))

  def parseInput(input: String): Option[Guess] = {
    val maybeDigits: Option[List[Digit]] = input.split("").toList.traverse[Option, Digit](c => c.parseIntOption.flatMap(Digit.fromInt))
    maybeDigits.flatMap(d => Code.fromList(d).map(Guess))
  }


  def createSecret: Task[Secret] = {
    val digits = Range.inclusive(1, 4).toList.traverse[Task, Digit](_ => generateDigit)
    digits.flatMap(l => fromOption(Code.fromList(l).map(Secret), "Couldn't create secret"))
  }

  def generateDigit: Task[Digit] = Task {
    Random.nextInt(10)
  }.flatMap {
    digit =>
      fromOption(Digit.fromInt(digit), "Failed to generate correct digit")
  }

  def gameLoop(secret: Secret): Observable[Result] = {
    Observable.range(1, 10)
      .mapEval(attempt => print(s"Attempt $attempt"))
      .mapEval(_ => getGuess)
      .map(compare(_, secret))
      .mapEval(reportResult)
      .takeWhile {
        case FullMatch => false
        case _ => true
      }
  }

  def reportError(message: String): Task[Unit] = Task.delay {
    println(s"Error: $message")
  }

  def reportResult(result: Result): Task[Result] = {
    print(s"result ${result.show}") >> Task.delay(result)
  }

  def removeFirstMatchingDigit(list: List[Digit], d: Digit): List[Digit] =
    list.foldLeft[(List[Digit], Boolean)]((Nil, false)) {
      case ((l, flag), s) =>
        if (s.equalTo(d) && !flag)
          (l, true)
        else
          (s :: l, flag)
    }._1

  def calculateBulls(leftOver: List[(Digit, Digit)]): Int = {
    val (guessList, secretList) = leftOver.unzip
    val digits: List[Digit] = guessList.foldLeft(secretList)((l, g) => removeFirstMatchingDigit(l, g))
    secretList.length - digits.length
  }

  def compare(guess: Guess, secret: Secret): Result = {
    val comparison: List[(Digit, Digit)] = guess.code.value.zip(secret.code.value)
    val (exact, leftOver): (List[(Digit, Digit)], List[(Digit, Digit)]) =
      comparison.partition { case (a, b) => a.equalTo(b) }

    val bulls = calculateBulls(leftOver)

    if (exact.length == 4)
      FullMatch
    else
      PartialMatch(exact.length, bulls)
  }

  val getGuess: Task[Guess] = (for {
    _ <- print(s"Enter your guess:")
    input <- getInput
  } yield input).recoverWith {
    case e => reportError(e.getMessage) >> getGuess
  }

  def print(s: String): Task[Unit] = Task.eval(println(s))

  def createGame: Task[Unit] = for {
    secret <- createSecret
    _ <- print(secret.code.show)
    _ <- gameLoop(secret).lastOptionL
  } yield ()

}
