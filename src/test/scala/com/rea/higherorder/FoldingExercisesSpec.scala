package com.rea.higherorder

import org.specs2.mutable.Specification
import FoldingExercises._

class FoldingExercisesSpec extends Specification {

  "Folding over a list" should {
    assert(foldLeft(0, List(1, 2, 3))(_ + _) == 6)
    assert(foldLeft(List[Int](), List(1, 2, 3))((a, e) => e :: a) == List(3, 2, 1))
    assert(foldRight(List[Int](), List(1, 2, 3))((e, a) => e :: a) == List(1, 2, 3))
    assert(foldRight(0, List(1, 2, 3))(_ + _) == 6)
  }


  "Sum of List(1,2,3,4,5,6) = 21" in {
    sum(List(1, 2, 3, 4, 5, 6)) === 21
  }
  "Length of List(1,2,3,4,5,6) = 6" in {
    FoldingExercises.length(List(1, 2, 3, 4, 5, 6)) === 6
  }

  "Add one to List(1,2,3,4,5,6) = List(2,3,4,5,6,7)" in {
    FoldingExercises.map(List(1, 2, 3, 4, 5, 6))(_ + 1) === List(2,3,4,5,6,7)
  }

  "Remove elements under 4 for List(1,2,3,4,5,6) = List(4,5,6): " in {
    filter(List(1, 2, 3, 4, 5, 6), 4 <= (_:Int)) === List(4, 5, 6)
  }

  "Append List(a,b,c) with List(d,e,f) = List(a,b,c,d,e,f): " in {
    append(List('a', 'b', 'c'), List('d', 'e', 'f')) === List('a', 'b', 'c', 'd', 'e', 'f')
  }

  "Flatten a List(List(a,b,c),List(d,e,f,g), List(h,i,j)) = List(a,b,c,d,e,f,g,h,i,j): " in {
    flatten(List(List('a', 'b', 'c'), List('d', 'e', 'f','g'), List('h', 'i', 'j'))) === List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')
  }

  "Run a flatMap over List(hello, world) with function split = List(h,e,l,l,o,w,o,r,l,d): " in {
    flatMap(List("hello", "world"), (_: String).toCharArray.toList) === List('h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd')
  }

  "maxium of List(4,3,5,7,1,2,6,3,4,5,6) = 7" in {
    maximum(List(4, 3, 5, 7, 1, 2, 6, 3, 4, 5, 6)) === 7
  }

  "Reverse a List(1,2,3,4,5,6) = List(6,5,4,3,2,1)" in {
    reverse(List(1, 2, 3, 4, 5, 6)) === List(6, 5, 4, 3, 2, 1)
  }

}
