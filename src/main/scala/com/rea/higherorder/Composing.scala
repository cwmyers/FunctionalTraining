package com.rea.higherorder

object Composing {
  def main(args: Array[String]) {
    println(addTwoMinusThree(6) + " should equal 5")

  }

  def add(a: Int, b: Int) = a + b

  // We want to partially apply add
  def addTwo: Int => Int = ???

  def subtract(a: Int, b: Int) = a - b

  // We want to partially apply subtract
  def minusThree: Int => Int = ???

  // How do we create a new function from addTwo and minusThree?
  val addTwoMinusThree: Int => Int = ???

}
