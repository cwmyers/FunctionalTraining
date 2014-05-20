package com.rea.typesafety

case class Box[A](get: A) {
  def map[B](f: A => B): Box[B] = Box(f(get))

  // Implementation with flatMap
  def map2[B](f: A => B): Box[B] = flatMap(f andThen unit[B])

  def unit[B](b: B) = Box(b)

  def flatMap[B](f: A => Box[B]): Box[B] = f(this.get)
}

