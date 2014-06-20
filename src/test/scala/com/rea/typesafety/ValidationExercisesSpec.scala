package com.rea.typesafety

import org.specs2.mutable.Specification
import ValidationExercises._
import scalaz.NonEmptyList
import org.specs2.scalaz.ValidationMatchers

class ValidationExercisesSpec extends Specification with ValidationMatchers {


  val allBad = Map[String, String]()
  val goodInput = Map("firstName" -> "Vladimir", "lastName" -> "Putin", "password" -> "crimea14")
  val passwordIsTooShort = goodInput + ("password" -> "crim3a")
  val passwordNoNumbers = goodInput + ("password" -> "crimeaasd")
  val passwordNoNumbersAndTooShort = goodInput + ("password" -> "crime")
  val noFirstName = goodInput - "firstName"
  val noLastName = goodInput - "lastName"
  val emptyFirstName = goodInput + ("firstName" -> "")
  val emptyLastName = goodInput + ("lastName" -> "")

  "Good input" in {
    validateInput(goodInput) should beSuccessful(Person("Vladimir", "Putin", "crimea14"))
  }

  "All Bad input" in {
    validateInput(allBad) should beFailing(NonEmptyList(keyNotFound("firstName"), keyNotFound("lastName"), keyNotFound("password")))
  }

  "password too short" in {
    validateInput(passwordIsTooShort) should beFailing(NonEmptyList(passwordTooShort))
  }

  "password too weak" in {
    validateInput(passwordNoNumbers) should beFailing(NonEmptyList(passwordTooWeak))
  }
  "password too short and too weak" in {
    validateInput(passwordNoNumbersAndTooShort) should beFailing(NonEmptyList(passwordTooShort, passwordTooWeak))
  }
  "no first name" in {
    validateInput(noFirstName) should beFailing(NonEmptyList(keyNotFound("firstName")))
  }
  "no last name" in {
    validateInput(noLastName) should beFailing(NonEmptyList(keyNotFound("lastName")))
  }
  "empty first name" in {
    validateInput(emptyFirstName) should beFailing(NonEmptyList(nameIsEmpty("firstName")))
  }
  "empty last name" in {
    validateInput(emptyLastName) should beFailing(NonEmptyList(nameIsEmpty("lastName")))
  }

}
