package com.rea.higherorder

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
  def foldLeft[A, B](initialValue: B, list: List[A])(f: (B, A) => B): B = ???

  /**
   * foldRight is the same as foldLeft, except it processes the list from right to left.
   */
  def foldRight[A,B](initialValue:B, list: List[A])(f: (A,B) => B):B = ???

  /**
   * Remember these, from our recursion exercises?  They can all be implemented with either
   * foldLeft or foldRight.
   */

  def sum(l: List[Int]): Int = ???

  def length[A](x: List[A]): Int = ???

  def map[A, B](x: List[A], f: A => B): List[B] = ???

  def filter[A](x: List[A], f: A => Boolean): List[A] = ???

  def append[A](x: List[A], y: List[A]): List[A] = ???

  def flatten[A](x: List[List[A]]): List[A] = ???

  def flatMap[A, B](x: List[A], f: A => List[B]): List[B] = ???

  // Maximum of the empty list is 0
  def maximum(x: List[Int]): Int = ???

  def reverse[A](x: List[A]): List[A] = ???

  def main(args: Array[String]) = {
    assert(foldLeft(0, List(1,2,3))(_+_) == 6)
    assert(foldLeft(List[Int](), List(1,2,3))((a,e) =>e :: a) == List(3,2,1))
    assert(foldRight(List[Int](), List(1,2,3))((e,a) =>e :: a) == List(1,2,3))
    assert(foldRight(0, List(1,2,3))(_+_) == 6)
  }

}
