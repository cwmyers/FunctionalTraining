package com.rea.higherorder

object ComposingExercises {


  def add(a: Int, b: Int) = a + b

  // We want to partially apply add
  def addTwo: Int => Int = add(2,_)

  def subtract(a: Int, b: Int) = a - b

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


