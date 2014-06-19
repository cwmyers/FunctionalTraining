package com.rea.typesafety


object OptionalExercises1 {
  val config = Map[String, String]("host" -> "rea.com", "port" -> "8080")

  def getFromConfig(key: String): Option[String] = config.get(key)

  def lengthOfHost(): Option[Int] = getFromConfig("host").map(_.length)

  def portPlus1000(): Option[Int] = getFromConfig("port").map(_.toInt).map(_ + 1000)
}

object OptionalExercises2 {

  val hosts = Map("host1" -> "rea.com", "host2" -> "test.rea.com", "host3" -> "netflix.com")
  val envs = Map("rea.com" -> "prod", "test.rea.com" -> "test", "amazon.com" -> "stage")

  def getEnvForHost(host: String): String = hosts.get(host).flatMap(envs.get).getOrElse("couldn't resolve")

  // See how many ways you can implement this.
  // Will either return "Connected to rea.com" or "not connected"
  def connectToReaHostsOnly(host: String): String = hosts.get(host)
    .flatMap(domain => if (domain.contains("rea.com")) Some(domain) else None)
    .fold("not connected")(createConnection)

  def connectToReaHostsOnly2(host: String): String = hosts.get(host)
    .filter(_.endsWith("rea.com")).fold("not connected")(createConnection)

  def createConnection(domain: String): String = s"connected to $domain"

}

object OptionalExercises3 {

  sealed trait Maybe[+A]

  case class Just[A](get: A) extends Maybe[A]

  case object Nothing extends Maybe[Nothing]

  def flatMap[A, B](m: Maybe[A], f: A => Maybe[B]): Maybe[B] = m match {
    case Just(a) => f(a)
    case _ => Nothing
  }

  def map[A, B](m: Maybe[A], f: A => B): Maybe[B] = m match {
    case Just(a) => Just(f(a))
    case _ => Nothing
  }

  def fold[A, B](m: Maybe[A], default: => B, f: A => B): B = m match {
    case Just(a) => f(a)
    case _ => default
  }

  def orElse[A](m: Maybe[A], otherwise: => Maybe[A]): Maybe[A] = m match {
    case Just(a) => m
    case _ => otherwise
  }

  def orSome[A](m: Maybe[A], default: => A): A = m match {
    case Just(a) => a
    case _ => default
  }

  def map2[A, B, C](f: (A, B) => C)(m1: Maybe[A], m2: Maybe[B]): Maybe[C] = flatMap(m1, { a: A =>
    map(m2, { b: B => f(a, b)})
  })

  def sequence[A](l: List[Maybe[A]]): Maybe[List[A]] = l.foldRight[Maybe[List[A]]](Just(Nil)) {
    map2((_ :A) :: (_ : List[A]))
  }




}

