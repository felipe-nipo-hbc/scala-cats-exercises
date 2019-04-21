package monads

import cats._
import cats.implicits._
import monads.StackSafety.foldRight

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Program extends App {
  val foldResult = foldRight((1 to 100000).toList, 0L)(_ + _)
  println(foldResult.value)

  val r = Await.result(Future.sequence(Vector(
    Future(Writer.factorial(3)),
    Future(Writer.factorial(3))
  )), 5.seconds)
  println(r)

  val r2 = for {
    a <- Writer.wFactorial(3)
    b <- Writer.wFactorial(3)
  } yield a + b
  r2.written.foreach(println(_))
}
