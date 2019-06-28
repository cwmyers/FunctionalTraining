package ft.streams

import org.specs2.mutable.Specification

class MonixSpecs extends Specification {

  "Monix stream" should {
    "count to ten plus 1" in {
      Monix.createStream must beSome("")
    }
  }

}
