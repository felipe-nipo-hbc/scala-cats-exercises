package monads

import cats.Eval
import monads.StackSafety.foldRight

object Program extends App {
  val foldResult = foldRight((1 to 100000).toList, 0L)(_ + _)
  println(foldResult.value)
}
