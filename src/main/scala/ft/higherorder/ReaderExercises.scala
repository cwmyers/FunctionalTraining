package ft.higherorder

import cats.Id
import cats.data.{Kleisli, Reader}
import cats.implicits._

object ReaderExercises {

  case class Config(prefix: String = "Hi", suffix: String = "Bye")

  type MyReader[A] = Reader[Config, A]

  def format(s: String) = s"$s is formatted"

  def doStuff() = {


    val reader: MyReader[String] = getA1("yunlong")
    reader.run(Config())
  }

  // B => C

  // A => B

  // A => C

  // M[N[A]] => MN[A]


  def getA1(s: String): Reader[Config, String] = getB1(s)

  def getB1(s: String): Reader[Config, String] = {
    val c: Reader[String, String] = getC1(s)
    val configToString: Reader[Config, String] = c.local((config: Config) => config.prefix)
    configToString.flatMap(s => Reader { config => s + config.suffix })
  }

  def getC1(s: String): Reader[String, String] = Reader { prefix => prefix + s }

}


object XXX {

  def f(a:String): Int = ???

  f.compose((d:Double) => d.toString)

  case class MyList[A](a:A)

  // Animal => Cat

  // MyList[Animal] !=> MyList[Cat]


}
