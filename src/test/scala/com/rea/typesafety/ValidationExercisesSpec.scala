package com.rea.typesafety

import org.specs2.mutable.Specification
import ValidationExercises._
import cats.data.NonEmptyList
import org.specs2.matcher.ValidatedMatchers

class ValidationExercisesSpec extends Specification with ValidatedMatchers {


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
    validateInput(goodInput) should beValid(Person("Vladimir", "Putin", "crimea14"))
  }

  "All Bad input" in {
    validateInput(allBad) should beInvalid(NonEmptyList(keyNotFound("firstName"), List(keyNotFound("lastName"), keyNotFound("password"))))
  }

  "password too short" in {
    validateInput(passwordIsTooShort) should beInvalid(NonEmptyList(passwordTooShort, Nil))
  }

  "password too weak" in {
    validateInput(passwordNoNumbers) should beInvalid(NonEmptyList(passwordTooWeak, Nil))
  }
  "password too short and too weak" in {
    validateInput(passwordNoNumbersAndTooShort) should beInvalid(NonEmptyList(passwordTooShort, List(passwordTooWeak)))
  }
  "no first name" in {
    validateInput(noFirstName) should beInvalid(NonEmptyList(keyNotFound("firstName"), Nil))
  }
  "no last name" in {
    validateInput(noLastName) should beInvalid(NonEmptyList(keyNotFound("lastName"), Nil))
  }
  "empty first name" in {
    validateInput(emptyFirstName) should beInvalid(NonEmptyList(nameIsEmpty("firstName"), Nil))
  }
  "empty last name" in {
    validateInput(emptyLastName) should beInvalid(NonEmptyList(nameIsEmpty("lastName"), Nil))
  }

}
