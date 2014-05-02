package com.rea.higherorder

object Composing {
  def main(args: Array[String]) {
    println(addTwoMinusThree(6) + " should equal 5")
    println(addTwoMinusThreePrettyPrint(2) + " should equal 'The number is 1'")
  }

  def add(a: Int, b: Int) = a + b

  // We want to partially apply add
  def addTwo: Int => Int = ???

  def subtract(a: Int, b: Int) = a - b

  // We want to partially apply subtract
  def minusThree: Int => Int = ???

  // How do we create a new function from addTwo and minusThree?
  val addTwoMinusThree: Int => Int = ???
  
  def prettyPrint(i:Int) = s"The number is $i"
  
  val addTwoMinusThreePrettyPrint: Int => String = ???

  // How do we convert f and g into a brand new function?
  def compose_[A, B, C](f: B => C, g: A => B): A => C = ???

}


