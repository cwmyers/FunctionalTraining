FunctionalTraining
==================

Introducing functional programming to interested and enlightened people.

## Running and compiling

In the root directory there is a shell script called `sbt` it runs the sbt console.

	$ ./sbt

To run the exercises simply type `test` in the `sbt` console.

    > test

To run a single exercise type `testOnly com.rea.<package>.<classnameSpec>`

    > testOnly com.rea.higherorder.ComposingExercisesSpec

## Completing the exercises

I've kept this quite simple.

There are four packages, covering the areas we will be covering in this course.

Remember:
- The types are the gateway to the solution.
- Avoid mutability at all costs.

Have fun.

## Recommended order

- ComposingExercises
- RecursionExercises
- OptionalExercises
- FoldingExercises
- Box
- ValidationExercises
- ExpressionProblemExercises
- IOExercises
- RefTransExercises