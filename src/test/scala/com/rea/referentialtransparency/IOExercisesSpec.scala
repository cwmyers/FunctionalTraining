package com.rea.referentialtransparency

import org.specs2.mutable.Specification
import IOExercises._
class IOExercisesSpec extends Specification {

  "Run effects" in {
    println("Pausing for user input... *psst* hit return")
    run(readFromConsoleAndWriteToConsole)
    true
  }

}
