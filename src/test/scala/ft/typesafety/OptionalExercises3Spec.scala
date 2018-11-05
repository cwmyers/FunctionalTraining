package ft.typesafety

import org.specs2.mutable.Specification
import OptionalExercises3._

class OptionalExercises3Spec extends Specification {

  "flatMap" should {

    "operate on a Just" in {
      flatMap(Just(1))(a => Just(a + 1)) === Just(2)
    }

    "operate on a Nothing" in {
      flatMap(Nothing: Maybe[Int])(a => Just(a + 1)) === Nothing
    }
  }

  "map" should {

    "operate on a Just" in {
      OptionalExercises3.map(Just(1))(a => a + 1) === Just(2)
    }

    "operate on a Nothing" in {
      OptionalExercises3.map(Nothing: Maybe[Int])(a => a + 1) === Nothing
    }
  }

  "fold" should {

    "operate on a Just" in {
      fold[Int, Int](Just(1), 0, a => a + 1) === 2
    }

    "operate on a Nothing" in {
      fold[Int, Int](Nothing, 0, a => a + 1) === 0
    }
  }

  "orElse" should {

    "operate on a Just" in {
      orElse(Just(1), Just(2)) === Just(1)
    }

    "operate on a Nothing" in {
      orElse(Nothing, Just(2)) === Just(2)
    }
  }

  "orSome" should {

    "operate on a Just" in {
      orSome(Just(1), 2) === 1
    }

    "operate on a Nothing" in {
      orSome(Nothing, 2) === 2
    }
  }

  "map2" should {

    "operate on a Just" in {
      map2((a: Int, b: Int) => a + b)(Just(1), Just(2)) === Just(3)
    }

    "operate on a Nothing" in {
      map2((a: Int, b: Int) => a + b)(Just(1), Nothing) === Nothing
    }
  }

  "sequence" should {

    "operate on a List of Justs" in {
      sequence(List(Just(1), Just(2))) === Just(List(1, 2))
    }

    "operate on a List of Justs and Nothings" in {
      sequence(List(Just(1), Nothing)) === Nothing
    }
  }

  "ap" should {

    "apply functions when all is some" in {

      case class Person(name: String, age: Int, address: String)

      val personConstructor: (String) => (Int) => (String) => Person = Person.curried

      val name = Just("Tom")
      val age = Just(40)
      val address = Just("London")

      val person: Maybe[Person] = ap(address, ap(age, OptionalExercises3.map(name)(personConstructor)))

      person should be equalTo Just(Person("Tom", 40, "London"))
    }
  }
}
