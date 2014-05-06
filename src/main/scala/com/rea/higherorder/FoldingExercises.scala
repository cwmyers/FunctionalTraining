package com.rea.higherorder

object FoldingExercises {

  /**
   *
   * foldLeft will reduce a list of A's down to a B. It takes an initial value of type B
   * and a list of A's.  It also takes a function which takes the accumulated value of type B
   * and the next value in the list (of type A) and returns a value which will be feed back into
   * the accumulator of the next call.
   *
   */
  def foldLeft[A, B](initialValue: B, list: List[A])(f: (B, A) => B): B = ???

  def foldRight[A,B](initialValue:B, list: List[A])(f: (A,B) => B):B = ???

  def main(args: Array[String]) = {
    assert(foldLeft(0, List(1,2,3))(_+_) == 6)
    assert(foldLeft(List[Int](), List(1,2,3))((a,e) =>e :: a) == List(3,2,1))
    assert(foldRight(List[Int](), List(1,2,3))((e,a) =>e :: a) == List(1,2,3))
    assert(foldRight(0, List(1,2,3))(_+_) == 6)
  }

}
