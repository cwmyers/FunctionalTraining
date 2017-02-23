package com.rea.typesafety

/**
  * When talking about functional programming you may hear
  * the words functor, applicative and monad thrown around
  *
  * Anything that implements the map() method:
  *     def map[B](f: A => B): F[B]
  * is said to exhibit the behavior of a functor
  *
  * Anything that implements the ap() method:
  *     def ap[B](bf: F[A => B]): F[B]
  * is said to exhibit the behavior of an applicative
  *
  * Anything that implements the flatMap() method:
  *     def flatMap[B](f: A => M[B]): M[B] = ???
  * is said to exhibit the behavior of a monad
  *
  *   Where F is a functor, and M is a monad
  *
  * We say "is said to exhibit the behavior of" because
  * there are more laws that are required to be obeyed
  * to legally classify them as a functor / applicative / monad
  *
  *   Those other laws are out of the scope of these exercises
  *
  * The goal of these exercises is to implement map, ap and flatMap
  * on our Box class so that it has "pseudo" functor, applicative and
  * monad capabilities
  *
  */

case class Box[A](get: A) {

  //Make it a Functor*
  def map[B](f: A => B): Box[B] = ???

  //Make it an Applicative*
  def ap[B](bf: Box[A => B]): Box[B] = ???

  //Make it a Monad*
  def flatMap[B](f: A => Box[B]): Box[B] = ???
}

object Box {
  def join[A](box: Box[Box[A]]): Box[A] = box.flatMap(identity)
}
