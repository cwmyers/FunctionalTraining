package com.rea.typesafety


object OptionalExercises1 {
  def main(args: Array[String]) {
    println("The host name is: " + getFromConfig("host").getOrElse("Not Found"))
    println("The length of host name is: " + lengthOfHost().getOrElse(0))
    println("The port plus 1000 is: " + portPlus1000().getOrElse(0) + " (expecting 9080)")
  }

  val config = Map[String, String]("host" -> "rea.com", "port" -> "8080")

  def getFromConfig(key: String): Option[String] = ???

  def lengthOfHost(): Option[Int] = ???

  def portPlus1000(): Option[Int] = ???
}

object OptionalExercises2 {
  def main(args: Array[String]) {
    println("Environment for host1 is " + getEnvForHost("host1") + " expecting 'prod'")
    println("Environment for host2 is " + getEnvForHost("host2") + " expecting 'test'")
    println("Environment for host3 is " + getEnvForHost("host3") + " expecting 'couldn't resolve'")
    println("Environment for host4 is " + getEnvForHost("host4") + " expecting 'couldn't resolve'")

    println("Should be connected to rea.com " + connectToSkyHostOnly("host1"))
    println("Should be connected to bskyb.com " + connectToSkyHostOnly("host2"))
    println("Should not be connected to netflix.com " + connectToSkyHostOnly("host3"))
    println("Should not be connected to unknown host " + connectToSkyHostOnly("host4"))
  }

  val hosts = Map("host1" -> "rea.com", "host2" -> "bskyb.com", "host3" -> "netflix.com")
  val envs = Map("sky.com" -> "prod", "bskyb.com" -> "test")

  def getEnvForHost(host: String): Option[String] = ???

  def connectToSkyHostOnly(host: String): String = ???

  def createConnection(host: String): String = s"Connected to $host"

}
