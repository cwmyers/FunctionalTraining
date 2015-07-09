package com.rea.typesafety

import org.specs2.mutable.Specification
import OptionalExercises3._

class OptionalExercises3Spec extends Specification {
  "flatMap" should {
    "operate on a Just" in {
      flatMap(Just(1), {a:Int => Just(a + 1)}) should be equalTo Just(2)
    }
  }

  "ap" should {
    "apply functions when all is some" in {

      case class Person(name: String, age: Int, address: String)

      val personConstructor: (String) => (Int) => (String) => Person = Person.curried

      val name = Just("Tom")
      val age = Just(40)
      val address = Just("London")

      val person: Maybe[Person] = ap(address, ap(age, OptionalExercises3.map(name, personConstructor)))

      person should be equalTo Just(Person("Tom", 40, "London"))

    }
  }


}
