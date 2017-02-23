package com.rea.higherorder

/**
  * You're going to hear this a lot in the functional world:
  *
  *   "If I have a function from A => B and another function from B => C then
  *   I can compose those two functions and make a NEW function from A => C"
  *
  *   These exercises are all about getting you familiar with this concept.
  *
  *
  * "What is an `A`?" you may ask. "Or a `B` and `C`?"
  *
  * These capital letters represent generics in a function signature,
  * all the As have to be the same type as the other As,
  * but only in that particular function
  *
  *   This A
  *   |
  *   |    Has to be the same type as this A
  *   |    |
  *   A => A
  *
  *   Int => Int
  *   String => String
  *
  *   So when we say A => B and B => C, the Bs are the only things that need to be
  *   the same.
  *
  *
  * You may also run into these kinds of function definitions
  *
  *   def someFunc[A, B](x: A, y: B): B
  *
  *   Here in the square brackets, we're merely stating that there are two different
  *   possible types of generics in this function. Every A has to be the same type as
  *   every other A, every B the same as every other B, but the As and the Bs don't
  *   necessarily have to be the same type.
  *
  *   eg: def someFunc[Int, Int](x: Int, y: Int): Int
  *       def someFunc[Int, String](x: Int, y: String): String
  *
  *
  * Good luck!
  *
  */

object ComposingExercises {

  // A boring add function takes two Ints and Returns an Int
  def add(a: Int, b: Int): Int = a + b

  // addTwo uses add and partially applies 2 to it.
  // All that was needed was to fill in the x
  def addTwo: Int => Int = x => add(x, 2)

  // Write a boring subtract function
  def subtract(a: Int, b: Int): Int = a - b

  // We want to partially apply subtract
  def minusThree: Int => Int = subtract(_,3)

  // How do we create a new function from addTwo and minusThree?
  val addTwoMinusThree: Int => Int = addTwo compose minusThree
  
  def prettyPrint(i:Int) = s"The number is $i"
  
  val addTwoMinusThreePrettyPrint: Int => String =  prettyPrint _ compose addTwoMinusThree

  // How do we convert (compose) f and g into a brand new function?
  def compose_[A, B, C](f: B => C, g: A => B): A => C = {
    x: A => f(g(x))
  }
}
