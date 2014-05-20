package com.rea.typesafety


object OptionalExercises1 {
  def main(args: Array[String]) {
    println("The host name is: " + getFromConfig("host").getOrElse("Not Found"))
    println("The length of host name is: " + lengthOfHost().getOrElse(0))
    println("The port plus 1000 is: " + portPlus1000().getOrElse(0) + " (expecting 9080)")
  }

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
    .map(createConnection)
    .getOrElse("not connected")

  def connectToReaHostsOnly2(host: String): String = hosts.get(host)
    .filter(_.endsWith("rea.com")).map(createConnection)
    .getOrElse("not connected")

  def createConnection(domain: String): String = s"Connected to $domain"

}

