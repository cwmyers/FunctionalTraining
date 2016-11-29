package com.rea.typesafety

/**
  * Use pattern matching and recursion.  No vars, no loops, no overriding.
  *
  * A Maybe is our implementation of optional functionality. Scala's is called Option.
  *
  * We've made these exercises to give you greater insight into how an optional pattern
  * might work in a functional language.
  *
  * When you see Maybe think: "Maybe it exists, maybe it doesn't"
  *
  * There are two ways to construct a Maybe:
  *
  *   Just() represents something that exists
  *
  *   Nothing represents something that doesn't exist
  *
  * We use Maybe in situations where there isn't certainty that a meaningful
  * value will be returned to us.
  *
  *   The `get()` method on the key to value store `Map` is a great example of this.
  *
  *     We expect get() to take a key and give us a value in return.
  *
  *     But what happens when our Map doesn't know about the key we gave it?
  *
  *   A Map here is the same as in any other language,
  *   we just need to tell it about the types we're working with.
  *
  *     This is the type of the key
  *                      |
  *                      |     This is the type of the value
  *                      |      |
  *                      |      |
  *     val myMap = Map[Int, String]( 1 -> "one", 2 -> "two, ...)
  *
  *
  *   When we call get() on Map we will always get back a Maybe type
  *
  *     myMap.get(1) = Just("one")  //The value exists and it's the string "one"
  *
  *     myMap.get(0) = Nothing      //The value doesn't exist so we get Nothing
  *
  *   Just("one") and Nothing are both of the type Maybe
  *
  *   Since Just and Nothing are the same type we can pattern match on them!
  *
  *   We can have one set of logic when we get Just back and a different set
  *   of logic when we get Nothing back!
  *
  *   val mightBeSomething: Maybe[String] = myMap.get(3)
  *
  *   val result: String = mightBeSomething match {
  *     case Just(string) => "I got a String back!"
  *     case Nothing => "I got Nothing back"
  *   }
  *
  * Good luck!
  *
  */
object OptionalExercises1 {

  val config = Map[String, String]("host" -> "rea.com", "port" -> "8080")

  def getFromConfig(key: String): Option[String] = ???

  def lengthOfHost(): Option[Int] = ???

  def portPlus1000(): Option[Int] = ???
}

object OptionalExercises2 {

  val hosts = Map("host1" -> "rea.com", "host2" -> "test.rea.com", "host3" -> "netflix.com")
  val envs = Map("rea.com" -> "prod", "test.rea.com" -> "test", "amazon.com" -> "stage")

  def getEnvForHost(host: String): Option[String] = ???

  // See how many ways you can implement this.
  // Will either return "Connected to rea.com" or "not connected"
  def connectToReaHostsOnly(host: String): String = ???

  def createConnection(domain: String): String = s"connected to $domain"
}

object OptionalExercises3 {

  sealed trait Maybe[+A]

  case class Just[A](get: A) extends Maybe[A]

  case object Nothing extends Maybe[Nothing]

  def flatMap[A, B](m: Maybe[A])(f: A => Maybe[B]): Maybe[B] = ???

  def map[A, B](m: Maybe[A])(f: A => B): Maybe[B] = ???

  def fold[A, B](m: Maybe[A], default: => B, f: A => B): B = ???

  def orElse[A](m: Maybe[A], otherwise: => Maybe[A]): Maybe[A] = ???

  def orSome[A](m: Maybe[A], default: => A): A = ???

  def map2[A, B, C](f: (A, B) => C)(m1: Maybe[A], m2: Maybe[B]): Maybe[C] = ???

  def sequence[A](l: List[Maybe[A]]): Maybe[List[A]] = ???

  def ap[A, B](m1: Maybe[A], m2: Maybe[A => B]): Maybe[B] = ???
}
