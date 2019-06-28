package ft.cowsandbulls

import monix.execution.Scheduler
import org.specs2.mutable.Specification
import cats.implicits._

class CowsAndBullsTest extends Specification {

  implicit val scheduler = Scheduler.fixedPool("MyTest", 20)

  "parseInput" should {
    "correctly parse valid input" in {
      CowsAndBulls.parseInput("1234").map(_.code.show) must beSome("1234")
    }
    "fail on short input" in {
      CowsAndBulls.parseInput("123") must beNone
    }

    "fail on long input" in {
      CowsAndBulls.parseInput("12345") must beNone
    }

    "fail on non-integer" in {
      CowsAndBulls.parseInput("abcd") must beNone
    }

    "fail with one integer" in {
      CowsAndBulls.parseInput("1bcd") must beNone
    }
  }

  "calculateBulls" should {
    "return 3" in {
      val Some(guess) = List(1,2,3).traverse(Digit.fromInt)
      val Some(secret) = List(3,1,2).traverse(Digit.fromInt)
      CowsAndBulls.calculateBulls(guess.zip(secret)) === 3
    }

    "return 1 with doubles" in {
      val Some(guess) = List(1,1,0,0).traverse(Digit.fromInt)
      val Some(secret) = List(3,3,1,2).traverse(Digit.fromInt)
      CowsAndBulls.calculateBulls(guess.zip(secret)) must beEqualTo(1)

    }

    "return 2 with doubles" in {
      val Some(guess) = List(1,1,0,0).traverse(Digit.fromInt)
      val Some(secret) = List(3,3,1,1).traverse(Digit.fromInt)
      CowsAndBulls.calculateBulls(guess.zip(secret)) must beEqualTo(2)

    }

    "return 1 with doubles on the secret" in {
      val Some(guess) = List(1,0,0,0).traverse(Digit.fromInt)
      val Some(secret) = List(3,3,1,1).traverse(Digit.fromInt)
      CowsAndBulls.calculateBulls(guess.zip(secret)) must beEqualTo(1)

    }
    "return 1 with doubles on the secret" in {
      val Some(guess) = List(1,1,1,0).traverse(Digit.fromInt)
      val Some(secret) = List(3,3,1,1).traverse(Digit.fromInt)
      CowsAndBulls.calculateBulls(guess.zip(secret)) must beEqualTo(2)

    }
  }
  "createGame" should {
    "run" in {
      CowsAndBulls.createGame.runSyncUnsafe()
      ok
    }
  }

}
