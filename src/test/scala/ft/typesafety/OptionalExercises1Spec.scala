package ft.typesafety

import org.specs2.mutable.Specification
import OptionalExercises1._

class OptionalExercises1Spec extends Specification {

  "getting Host Name from config" should {
    "be rea.com for host" in {
      getFromConfig("host") === Some("rea.com")
    }

    "be none for other" in {
      getFromConfig("other") === None
    }
  }

  "The length of host name" should {
    "be 7" in {
      lengthOfHost() === Some(7)
    }
  }

  "The port plus 1000" in {
    portPlus1000() === Some(9080)
  }
}
