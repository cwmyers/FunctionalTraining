package com.rea.typesafety

case class Box[A](get: A) {
  def map[B](f: A => B): Box[B] = ???

  def flatMap[B](f: A => Box[B]): Box[B] = ???
}

