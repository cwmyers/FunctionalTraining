package com.rea.adt

import org.specs2.mutable.Specification
import com.rea.adt.Expr._

class ExpressionProblemExercisesSpec extends Specification {

  "eval" in {
    "Const(true) = True" in {
      eval(Const(true)) === true
    }

    "Const(false) = False" in {
      eval(Const(false)) === false
    }

    "True And True = True" in {
      eval(And(Const(true), Const(true))) === true
    }

    "True And False = False" in {
      eval(And(Const(true), Const(false))) === false
    }

    "False And False = False" in {
      eval(And(Const(false), Const(false))) === false
    }

    "True Or True = True" in {
      eval(Or(Const(true), Const(true))) === true
    }

    "True Or False = True" in {
      eval(Or(Const(true), Const(false))) === true
    }

    "False Or False = False" in {
      eval(Or(Const(false), Const(false))) === false
    }

    "Not True = False" in {
      eval(Not(Const(true))) === false
    }

    "Not False = True" in {
      eval(Not(Const(false))) === true
    }

    "Not Not True = True" in {
      eval(Not(Not(Const(true)))) === true
    }

    "Not Not False = False" in {
      eval(Not(Not(Const(false)))) === false
    }
  }

  "normalise" in {
    "Not Not Not Not True = True" in {
      normalise(Not(Not(Not(Not(Const(true)))))) === Const(true)
    }

    "Not Not True And Not Not False = Not (Not True Or Not false)" in {
      normalise(And(Not(Not(Const(true))), Not(Not(Const(false))))) === Not(Or(Not(Const(true)), Not(Const(false))))
    }

    "Not Not True Or Not Not False = Not (Not True And Not false)" in {
      normalise(Or(Not(Not(Const(true))), Not(Not(Const(false))))) === Not(And(Not(Const(true)), Not(Const(false))))
    }

    "Not Not ( Not ( (Not Not Not True) And (Not Not Not False) ) Or Not ( (Not Not Not True) Or (Not Not Not False) ) ) = " +
      "not ( not (true or false) and not (true and false) )" in {
      normalise(
        Not(Not(
          Or(
            Not(
              And(Not(Not(Not(Const(true)))), Not(Not(Not(Const(false)))))
            ),
            Not(
              Or(Not(Not(Not(Const(true)))), Not(Not(Not(Const(false)))))
            )
          )
        ))
      ) === Not(And(Not(Or(Const(true),Const(false))),Not(And(Const(true),Const(false)))))
    }
  }

  "show" in {
    "Show True = true" in {
      show(Const(true)).contains("true")
    }

    "Show False = false" in {
      show(Const(false)).contains("false")
    }

    "Show True And False = true and false" in {
      List("true", "and", "false").forall(show(And(Const(true), Const(false))).contains(_))
    }

    "Show True Or False = true or false" in {
      List("true", "or", "false").forall(show(Or(Const(true), Const(false))).contains(_))
    }

    "Show Not True = not true" in {
      List("not", "true").forall(show(Not(Const(true))).contains(_))
    }
  }
}
