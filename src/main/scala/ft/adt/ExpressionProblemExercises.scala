package ft.adt

sealed trait Expr
case class Const(value: Boolean) extends Expr
case class And(a: Expr, b: Expr) extends Expr
case class Or(a: Expr, b: Expr) extends Expr
case class Not(expr: Expr) extends Expr

/**
 * Use pattern matching and recursion.  No vars, no loops, no overriding.
 */

object Expr {
  
  /**
   * Evaluate the expression.
   */

  def eval(expr: Expr): Boolean = expr match {
    case Const(v) => v
    case And(a, b) => eval(a) && eval(b)
    case Or(a, b) => eval(a) || eval(b)
    case Not(e) => !eval(e)
  }
  
  /**
   * Normalise the expression, such that:
   *
   * !!a     ==> a
   * !a & !b ==> !(a | b)
   * !a | !b ==> !(a & b)
   *
   * Normalize until you get the simplest case
   *
   * Make sure the pattern match is exhaustive (has a default clause)
   *
   * (Hint: You can and should normalize recursively)
   */

  def normalise(expr: Expr): Expr = expr match {
    case Not(Not(e)) => normalise(e)
    case And(Not(a), Not(b)) => Not(Or(normalise(a), normalise(b)))
    case Or(Not(a), Not(b)) => Not(And(normalise(a), normalise(b)))
    case e => normalise(e)
  }
  
  /**
   * Show, using English lower-case words "and", "or", "not", "true", "false"
   */
  
  def show(expr: Expr): String = expr match {
    case Const(v) => v.toString
    case And(a, b) => s"$a and $b"
    case Or(a, b) => s"$a or $b"
    case Not(a) => s"not $a"
  }
}
