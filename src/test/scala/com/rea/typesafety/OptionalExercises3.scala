package com.rea.typesafety

import org.specs2.mutable.Specification
import OptionalExercises3._

class OptionalExercises3Spec extends Specification {
  "flatMap" should {
    "operate on a Just" in {
      flatMap(Just(1), {a:Int => Just(a + 1)}) should be equalTo (Just(2))
    }
  }


}
