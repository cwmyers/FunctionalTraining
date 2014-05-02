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
  def main(args: Array[String]) {
    println("Environment for host1 is " + getEnvForHost("host1") + " expecting 'prod'")
    println("Environment for host2 is " + getEnvForHost("host2") + " expecting 'test'")
    println("Environment for host3 is " + getEnvForHost("host3") + " expecting 'couldn't resolve'")
    println("Environment for host4 is " + getEnvForHost("host4") + " expecting 'couldn't resolve'")

    println("Should be connected to rea.com " + connectToReaHostsOnly("host1"))
    println("Should be connected to test.rea.com " + connectToReaHostsOnly("host2"))
    println("Should not be connected to netflix.com " + connectToReaHostsOnly("host3"))
    println("Should not be connected to unknown host " + connectToReaHostsOnly("host4"))
  }

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
