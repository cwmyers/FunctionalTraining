package com.rea.typesafety

import scalaz._, Scalaz._

object ValidationExercises {

  val allBad = Map[String, String]()
  val goodInput = Map("firstName" -> "Vladimir", "lastName" -> "Putin", "password" -> "crimea14")
  val passwordIsTooShort = goodInput + ("password" -> "crim3a")
  val passwordNoNumbers = goodInput + ("password" -> "crimeaasd")
  val passwordNoNumbersAndTooShort = goodInput + ("password" -> "crime")
  val noFirstName = goodInput - "firstName"
  val noLastName = goodInput - "lastName"

  def validateKey(key: String, input: Map[String, String]): ValidationNel[ErrorCode, String] = ???

  def nameValidation(name: String): ValidationNel[ErrorCode, String] = ???

  def passwordStrengthValidation(password: String): ValidationNel[ErrorCode, String] = ???

  def passwordLengthValidation(password: String): ValidationNel[ErrorCode, String] = ???

  def validateInput(input: Map[String, String]): ValidationNel[ErrorCode, Person] = ???

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


/*

Interesting Validator combinators

scala> val a:ValidationNel[String,String]  = "hi".successNel
a: scalaz.ValidationNel[String,String] = Success(hi)

scala> val b:ValidationNel[String,String]  = "world".successNel
b: scalaz.ValidationNel[String,String] = Success(world)

scala> val c:ValidationNel[String,String]  = "error1".failNel
c: scalaz.ValidationNel[String,String] = Failure(NonEmptyList(error1))

scala> val d:ValidationNel[String,String]  = "error2".failNel
d: scalaz.ValidationNel[String,String] = Failure(NonEmptyList(error2))

scala> a <* b
res0: scalaz.Validation[scalaz.NonEmptyList[String],String] = Success(hi)

scala> a *> b
res1: scalaz.Validation[scalaz.NonEmptyList[String],String] = Success(world)

scala> c <* d
res2: scalaz.Validation[scalaz.NonEmptyList[String],String] = Failure(NonEmptyList(error1, error2))

scala> a <* d
res3: scalaz.Validation[scalaz.NonEmptyList[String],String] = Failure(NonEmptyList(error2))


 */
