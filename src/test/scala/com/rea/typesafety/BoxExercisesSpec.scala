package com.rea.typesafety

import org.specs2.mutable.Specification

class BoxExercisesSpec extends Specification {


  "Box[6] + 3 = Box[9]" in {
    Box(6).map(_ + 3) === Box(9)
  }

  "Box[4] + 2 = Box[6]" in {
    Box(4).flatMap(b => Box(b + 2)) === Box(6)
  }

  "Box[Box['Cat']] joined = Box['Cat']" in {
    Box.join[String](Box(Box("Cat"))) === Box("Cat")
  }

}
