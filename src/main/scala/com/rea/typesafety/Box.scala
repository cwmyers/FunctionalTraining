package com.rea.typesafety

case class Box[A](get: A) {
  def map[B](f: A => B): Box[B] = Box(f(this.get))

  def flatMap[B](f: A => Box[B]): Box[B] = f(this.get)
}

object BoxExercises extends App {
  println("Box[6] + 3 = Box[9]: " + Box(6).map(_ + 3))
  println("Box[4] + 2 = Box[6]: " + Box(4).flatMap(b => Box(b + 2)))
}

