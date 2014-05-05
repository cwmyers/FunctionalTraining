package com.rea.recursion


// Taken from http://tmorris.net/posts/scala-exercises-for-beginners/index.html
object RecursionExercises {

  def plusOne(n: Int) = n + 1

  def minusOne(n: Int) = n - 1

  // Add two positive Integers together.  You are only allowed to use plusOne and minusOne above
  def add(a: Int, b: Int): Int = if (a == 0) b else add(minusOne(a), plusOne(b))

  // You are not permitted to use any list functions such as map, flatMap, ++, flatten etc
  def sum(l: List[Int]): Int = {
    def sum0(acc: Int, l1: List[Int]): Int = l1 match {
      case Nil => acc
      case (x: Int) :: xs => sum0(add(x, acc), xs)
    }

    sum0(0, l)
  }

  //Again no list functions are permitted for the following
  def length[A](x: List[A]): Int = {
    def length0(acc: Int, l: List[A]): Int = l match {
      case Nil => acc
      case h :: tail => length0(plusOne(acc), tail)
    }
    length0(0, x)
  }

  // Mapping over a list.  You are given a List of type A and a function converting an A to a B
  // and you give back a list of type B.  No list functions allowed!
  def map[A, B](x: List[A], f: A => B): List[B] = ???


  def main(args: Array[String]) = {
    println("10 + 34 = 44: " + add(10, 34))
    println("0 + 34 = 34: " + add(0, 34))
    println("0 + 0 = 0: " + add(0, 0))

    println("Sum of List(1,2,3,4,5,6) = 21: " + sum(List(1, 2, 3, 4, 5, 6)))
    println("Length of List(1,2,3,4,5,6) = 6: " + length(List(1, 2, 3, 4, 5, 6)))
  }
}
