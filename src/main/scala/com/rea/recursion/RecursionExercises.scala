package com.rea.recursion


// Based largely on http://tmorris.net/posts/scala-exercises-for-beginners/index.html
object RecursionExercises {

  def plusOne(n: Int) = n + 1

  def minusOne(n: Int) = n - 1

  // Add two Integers together.  You are only allowed to use plusOne and minusOne above
  def add(a: Int, b: Int) = ???

  // You are not permitted to use any list functions such as map, flatMap, ++, flatten etc
  def sum(l: List[Int]): Int = ???


  def main(args: List[String]) = {
    println("10 + 34 = 44: " + add(10, 34))
    println("0 + 34 = 34: " + add(0, 34))
    println("0 + 0 = 0: " + add(0, 0))

    println("Sum of List(1,2,3,4,5,6) = 21: " + sum(List(1, 2, 3, 4, 5, 6)))
  }
}
