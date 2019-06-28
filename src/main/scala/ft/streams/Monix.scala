package ft.streams

import monix.eval.Task
import monix.execution.{CancelableFuture, Scheduler}
import monix.reactive.Observable

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.StdIn
import scala.util.Random

object Monix {


  // Extract Transform Load

  implicit val scheduler = Scheduler.fixedPool("MyTest", 20)

  def createStream = {


    val last: CancelableFuture[Option[String]] =
      Observable.fromIterable(Stream.continually(Random.nextInt()))
        .map(s => s + "!")
        .mapParallelUnordered(20)(waitForABit)
        .dump("Int = ")
        .runAsyncGetLast
    val maybeInt = Await.result(last, Duration.Inf)
    maybeInt

  }

  def waitForABit[A](i: A): Task[A] = {
    Task {
      Thread.sleep(10 * 1000)
      i
    }
  }

}
