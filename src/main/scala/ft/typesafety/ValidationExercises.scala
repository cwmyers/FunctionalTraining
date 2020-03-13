package ft.typesafety

import cats.data.ValidatedNel
import cats.implicits._

object ValidationExercises {

  /**
   * Write a function that retrieves the value associated with a given `key` in the `input` `Map`.
   * If the `key` cannot be found, return an appropriate `ErrorCode`,
   * otherwise return the value associated with the `key`.
   */
  def validateKey(key: String, input: Map[String, String]): ValidatedNel[ErrorCode, String] = ???

  /**
   * Validate `name` to ensure that it is not empty.
   * If it is empty, return an appropriate `ErrorCode`,
   * otherwise return the `name`.
   */
  def nameValidation(name: String, label: String): ValidatedNel[ErrorCode, String] = ???

  /**
   * Validate the `password` to ensure that it has at least a numeric character.
   * If it does not, return an appropriate `ErrorCode`,
   * otherwise return the `password`.
   *
   * Hint: You may need to use `.exists` and `.isDigit`
   */
  def passwordStrengthValidation(password: String): ValidatedNel[ErrorCode, String] = ???

  /**
   * Validate the `password` to ensure that its length is greater than 6.
   * If the password is too short, return an appropriate `ErrorCode`,
   * otherwise return the `password`.
   */
  def passwordLengthValidation(password: String): ValidatedNel[ErrorCode, String] = ???

  /**
   * Convert this `input` `Map` into a `Person` if it contains valid `firstName`, `lastName` and `password`.
   * You will want to compose all the functions you have written above to do this.
   * If validation errors are encountered, this function should return all the errors that it has encountered.
   *
   * Hint: Use `.mapN` to compose multiple `ValidatedNel`s.
   */
  def validateInput(input: Map[String, String]): ValidatedNel[ErrorCode, Person] = ???

}

case class Person(firstName: String, lastName: String, password: String)

sealed trait ErrorCode

case object passwordTooShort extends ErrorCode

case object passwordTooWeak extends ErrorCode

case class keyNotFound(key: String) extends ErrorCode

case class nameIsEmpty(label: String) extends ErrorCode


/*

Interesting Validator combinators

scala> val a:ValidatedNel[String,Int]  = 1.validNel
a: ValidatedNel[String,Int] = Valid(1)

scala> val b:ValidatedNel[String,Int]  = 2.validNel
b: ValidatedNel[String,Int] = Valid(2)

scala> val c:ValidatedNel[String,String]  = "error1".invalidNel
c: ValidatedNel[String,String] = Invalid(NonEmptyList(error1))

scala> val d:ValidatedNel[String,String]  = "error2".invalidNel
d: ValidatedNel[String,String] = Invalid(NonEmptyList(error2))

scala> a productL b
res0: ValidatedNel[String,Int] = Valid(1)

scala> a productR b
res1: ValidatedNel[String,Int] = Valid(2)

scala> c productL d
res2: ValidatedNel[String,Int] = Invalid(NonEmptyList(error1, error2))

scala> a productL d
res3: ValidatedNel[String,Int] = Invalid(NonEmptyList(error2))

scala> a.andThen(_ => b)
res4: ValidatedNel[String,Int] = Valid(2)

scala> a.andThen(n => if (n == 1) (n * 2).validNel else "fine, be that way!".invalidNel)
res6: ValidatedNel[String,Int] = Valid(2)

scala> d.andThen(n => if (n == 1) (n * 2).ValidNel else "fine, be that way!".invalidNel)
res7: ValidatedNel[String,Int] = Invalid(NonEmptyList(error2))

scala> b.flatMap(n => if (n == 1) (n * 2).ValidNel else "fine, be that way!".invalidNel)
res8: ValidatedNel[String,Int] = Invalid(NonEmptyList(fine, be that way!))

scala> a.map(n => n + 10)
res5: ValidatedNel[String,Int] = Valid(11)


 */
