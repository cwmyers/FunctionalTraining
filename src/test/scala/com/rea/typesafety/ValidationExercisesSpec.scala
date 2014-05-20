package com.rea.typesafety

import org.specs2.mutable.Specification
import ValidationExercises._
import scalaz.{Failure, Success}

class ValidationExercisesSpec extends Specification {

  val allBad = Map[String, String]()
  val goodInput = Map("firstName" -> "Vladimir", "lastName" -> "Putin", "password" -> "crimea14")
  val passwordIsTooShort = goodInput + ("password" -> "crim3a")
  val passwordNoNumbers = goodInput + ("password" -> "crimeaasd")
  val passwordNoNumbersAndTooShort = goodInput + ("password" -> "crime")
  val noFirstName = goodInput - "firstName"
  val noLastName = goodInput - "lastName"

  
  "Good input" in {
    validateInput(goodInput) === Success(Person("Vladimir", "Putin", "crimea14"))
  }

  "All Bad input" in {
    validateInput(allBad) === Failure(List())
  }

  "password too short" in {
    validateInput(passwordIsTooShort) === Failure(List())
  }

  "password too weak" in {
    validateInput(passwordNoNumbers)
  }
  "password too short and too weak" in {
    validateInput(passwordNoNumbersAndTooShort)
  }
  "no first name" in {
    validateInput(noFirstName)
  }
  "no last name" in {
    validateInput(noLastName)
  }


}
