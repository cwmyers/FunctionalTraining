package com.rea.recursion

import scala.annotation.tailrec


// Taken from http://tmorris.net/posts/scala-exercises-for-beginners/index.html
object RecursionExercises {

  def plusOne(n: Int) = n + 1

  def minusOne(n: Int) = n - 1

  // Add two positive Integers together.  You are only allowed to use plusOne and minusOne above
  @tailrec
  def add(a: Int, b: Int): Int = if (a == 0) b else add(minusOne(a), plusOne(b))

  // You are not permitted to use any list functions such as map, flatMap, ++, flatten etc
  def sum(l: List[Int]): Int = {
    @tailrec
    def sum0(acc: Int, l1: List[Int]): Int = l1 match {
      case Nil => acc
      case (x: Int) :: xs => sum0(add(x, acc), xs)
    }

    sum0(0, l)
  }

  //Again no list functions are permitted for the following
  def length[A](x: List[A]): Int = {
    @tailrec
    def length0(acc: Int, l: List[A]): Int = l match {
      case Nil => acc
      case h :: tail => length0(plusOne(acc), tail)
    }
    length0(0, x)
  }

  // Do you notice anything similar between sum and length? Hmm...

  // Mapping over a list.  You are given a List of type A and a function converting an A to a B
  // and you give back a list of type B.  No list functions allowed!
  def map[A, B](x: List[A], f: A => B): List[B] = x match {
    case Nil => Nil
    case h :: t => f(h) :: map(t, f)
  }

  // Given a function from A => Boolean, return a list with only those item where the function returned true.
  def filter[A](x: List[A], f: A => Boolean): List[A] = x match {
    case Nil => Nil
    case h :: t => {
      val filter1: List[A] = filter(t, f)
      if (f(h))
        h :: filter1
      else
        filter1
    }
  }

  // This pattern should be familiar by now... psst... look at add.
  def append[A](x: List[A], y: List[A]): List[A] = ???

  def main(args: Array[String]) = {
    println("10 + 34 = 44: " + add(10, 34))
    println("0 + 34 = 34: " + add(0, 34))
    println("0 + 0 = 0: " + add(0, 0))

    println("Sum of List(1,2,3,4,5,6) = 21: " + sum(List(1, 2, 3, 4, 5, 6)))
    println("Length of List(1,2,3,4,5,6) = 6: " + length(List(1, 2, 3, 4, 5, 6)))

    println("Add one to List(1,2,3,4,5,6) = List(2,3,4,5,6,7): " + map(List(1, 2, 3, 4, 5, 6), plusOne))

    println("Remove elements under 4 for List(1,2,3,4,5,6) = List(4,5,6): " + filter(List(1, 2, 3, 4, 5, 6), {
      x: Int => x >= 4
    }))

  }
}
