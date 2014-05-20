package com.rea.typesafety

import org.specs2.mutable.Specification
import OptionalExercises1._

class OptionalExercises1Spec extends Specification {

  "getting Host Name from config" should {
    "be rea.com for host" in {
      getFromConfig("host") should beSome("rea.com")
    }
    "be none for other" in {
      getFromConfig("other") should beNone
    }

  }

  "The length of host name" should {
    "be 7" in {
      lengthOfHost() should beSome(7)
    }
  }

  "The port plus 1000" in {
    portPlus1000() should beSome(9080)
  }


}
