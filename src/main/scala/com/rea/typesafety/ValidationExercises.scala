package com.rea.typesafety

import scalaz._, Scalaz._

object ValidationExercises {
  type VE[+A] = ValidationNel[ErrorCode, A]

  val allBad = Map[String, String]()
  val goodInput = Map("firstName" -> "Vladimir", "lastName" -> "Putin", "password" -> "crimea14")
  val passwordIsTooShort = goodInput + ("password" -> "crim3a")
  val passwordNoNumbers = goodInput + ("password" -> "crimeaasd")
  val passwordNoNumbersAndTooShort = goodInput + ("password" -> "crime")
  val noFirstName = goodInput - "firstName"
  val noLastName = goodInput - "lastName"

  def validateKey(input: Map[String, String], key: String): ValidationNel[ErrorCode, String] =
    input.get(key).toSuccess(keyNotFound(key)).toValidationNel

  def nameValidation(name: String, label: String): ValidationNel[ErrorCode, String] =
    if (!name.isEmpty) name.successNel else nameIsEmpty(label).failNel

  def passwordStrengthValidation(password: String): ValidationNel[ErrorCode, String] =
    if (password.matches( """.*\d+.*""")) password.successNel else passwordTooWeak.failNel

  def passwordLengthValidation(password: String): ValidationNel[ErrorCode, String] =
    if (password.length >= 8) password.successNel else passwordTooShort.failNel

  def validateInput(input: Map[String, String]): ValidationNel[ErrorCode, Person] = {
    val vKey = validateKey(input, _: String)
    def vName(name: String) = vKey(name).flatMap(nameValidation(_, name))

    val firstName = vName("firstName")
    val lastName = vName("lastName")


    val password = vKey("password").flatMap(
      password => passwordLengthValidation(password) <* passwordStrengthValidation(password)
    )

    Apply[VE].apply3(firstName, lastName, password)(Person)

  }

  def main(args: Array[String]) {
    println("Good input = " + validateInput(goodInput))
    println("All Bad input =" + validateInput(allBad))
    println("password too short = " + validateInput(passwordIsTooShort))
    println("password too weak = " + validateInput(passwordNoNumbers))
    println("password too short and too weak = " + validateInput(passwordNoNumbersAndTooShort))
    println("no first name = " + validateInput(noFirstName))
    println("no last name = " + validateInput(noLastName))
  }


}

case class Person(firstName: String, lastName: String, password: String)

sealed trait ErrorCode

case object passwordTooShort extends ErrorCode

case object passwordTooWeak extends ErrorCode

case class keyNotFound(key: String) extends ErrorCode

case class nameIsEmpty(key: String) extends ErrorCode
