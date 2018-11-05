package ft.higherorder

import org.specs2.mutable.Specification
import ft.higherorder.ComposingExercises._

class ComposingExercisesSpec extends Specification {

  "ComposingExercises" should {

    "addTwoMinusThree" in {
      addTwoMinusThree(6) === 5
    }

    "addTwoMinusThreePrettyPrint" in {
      addTwoMinusThreePrettyPrint(2) === "The number is 1"
    }

    "composeTwoFunctionsTogether" in {
      val f: Int => Int = x => x+1
      val g: Int => Int = y => y-3
      compose_(f, g)(2) === 0
    }
  }
}
