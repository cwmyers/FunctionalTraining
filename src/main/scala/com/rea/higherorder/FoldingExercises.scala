package com.rea.higherorder

import scala.annotation.tailrec

/*
 * DO NOT ATTEMPT these exercises until you've completed the recursion ones.
 */

object FoldingExercises {

  /**
   *
   * foldLeft will reduce a list of A's down to a B. It takes an initial value of type B
   * and a list of A's.  It also takes a function which takes the accumulated value of type B
   * and the next value in the list (of type A) and returns a value which will be feed back into
   * the accumulator of the next call.
   *
   * As the name suggests it processes the list from left to right.
   *
   * Have a close look at your implementations from the RecursionExercises.  Which parts could you
   * pull out to a function to make them all common?  Your implementation will be very close to
   * foldLeft.
   *
   * Good luck!
   *
   */
  def foldLeft[A, B](initialValue: B, list: List[A])(f: (B, A) => B): B = {
    @tailrec
    def fl(acc: B, l: List[A]): B = l match {
      case Nil => acc
      case h :: t => fl(f(acc, h), t)
    }
    fl(initialValue, list)
  }

  /**
   * foldRight is the same as foldLeft, except it processes the list from right to left.
   */
  def foldRight[A, B](initialValue: B, list: List[A])(f: (A, B) => B): B = {
    def fr(l: List[A], acc: B): B = l match {
      case Nil => acc
      case h :: t => f(h, fr(t, acc))
    }
    fr(list, initialValue)
  }


  /**
   * Remember these, from our recursion exercises?  They can all be implemented with either
   * foldLeft or foldRight.
   */

  def sum(l: List[Int]): Int = foldLeft(0, l)(_ + _)

  def length[A](x: List[A]): Int = foldLeft(0, x)((a, _) => a + 1)

  def map[A, B](x: List[A])(f: A => B): List[B] = foldRight[A, List[B]](Nil, x)((e, acc) => f(e) :: acc)

  def filter[A](x: List[A], f: A => Boolean): List[A] = foldRight[A, List[A]](Nil, x)((e, acc) => if (f(e)) e :: acc else acc)

  def append[A](x: List[A], y: List[A]): List[A] = foldRight(y, x)(_ :: _)

  def flatten[A](x: List[List[A]]): List[A] = foldRight(List[A](), x)(append)

  def flatMap[A, B](x: List[A], f: A => List[B]): List[B] = flatten(map(x)(f))

  // Maximum of the empty list is 0
  def maximum(x: List[Int]): Int = foldLeft(0, x)((acc, e) => if (e > acc) e else acc)

  def reverse[A](x: List[A]): List[A] = foldLeft[A, List[A]](Nil, x)((acc, e) => e :: acc)

}
