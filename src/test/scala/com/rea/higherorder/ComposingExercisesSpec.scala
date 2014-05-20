package com.rea.higherorder

import org.specs2.mutable.Specification


class ComposingExercisesSpec extends Specification {

  "ComposingExercises" should {
    "addTwoMinusThree" in {
      ComposingExercises.addTwoMinusThree(6) === 5

    }

    "addTwoMinusThreePrettyPrint" in {
      ComposingExercises.addTwoMinusThreePrettyPrint(2) === "The number is 1"
    }
  }

}
