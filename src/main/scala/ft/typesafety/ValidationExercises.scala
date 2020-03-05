package ft.typesafety

import cats.data.ValidatedNel
import cats.implicits._

object ValidationExercises {

  def validateKey(key: String, input: Map[String, String]): ValidatedNel[ErrorCode, String] = ???

  def nameValidation(name: String, label: String): ValidatedNel[ErrorCode, String] = ???

  def passwordStrengthValidation(password: String): ValidatedNel[ErrorCode, String] = ???

  def passwordLengthValidation(password: String): ValidatedNel[ErrorCode, String] = ???

  def validateInput(input: Map[String, String]): ValidatedNel[ErrorCode, Person] = ???


}

case class Person(firstName: String, lastName: String, password: String)

sealed trait ErrorCode

case object passwordTooShort extends ErrorCode

case object passwordTooWeak extends ErrorCode

case class keyNotFound(key: String) extends ErrorCode

case class nameIsEmpty(key: String) extends ErrorCode


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
