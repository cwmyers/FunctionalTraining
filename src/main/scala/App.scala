package bskyb.com.functionaltraining

object App {
  def main(args: Array[String]) {
	println("The host name is: "+ OptionalExercises.host)
	println("The length of host name is: "+ OptionalExercises.lengthOfHost)
	println("The port plus 1000 is: "+ OptionalExercises.portPlus1000 +" (expecting 9080)")

	import OptionFlatMapExercises._
	println("Environment for host1 is "+getEnvForHost("host1")+ " expecting 'prod'")
	println("Environment for host2 is "+getEnvForHost("host2")+ " expecting 'test'" )
	println("Environment for host3 is "+getEnvForHost("host3")+ " expecting 'couldn't resolve'")
	println("Environment for host4 is "+getEnvForHost("host4")+ " expecting 'couldn't resolve'")

	println("Should be connected to sky.com "+connectToSkyHostOnly("host1"))
	println("Should be connected to bskyb.com "+connectToSkyHostOnly("host2"))
	println("Should not be connected to netflix.com "+connectToSkyHostOnly("host3"))
	println("Should not be connected to unknown host "+connectToSkyHostOnly("host4"))
  }
}

object OptionalExercises {

        val config = Map[String,String]( "host" -> "sky.com", "port" -> "8080")

	def host = config.get("host").getOrElse("Host not found in config")

	def lengthOfHost = config.get("host").map(_.length)

	def portPlus1000 = config.get("port").map(Integer.parseInt(_)).map(_+1000).map(_.toString)
}

object OptionFlatMapExercises {

	val hosts = Map("host1" -> "sky.com", "host2" -> "bskyb.com", "host3" -> "netflix.com")
	val envs = Map("sky.com" -> "prod", "bskyb.com" -> "test")

	def getEnvForHost(host:String) = hosts.get(host).flatMap(envs.get(_)).getOrElse("couldn't resolve")

	def connectToSkyHostOnly(host:String) = hosts.get(host).flatMap{x:String => if (x.contains("sky")) Some(createConnection) else None}

	def createConnection = "Connected"

}
