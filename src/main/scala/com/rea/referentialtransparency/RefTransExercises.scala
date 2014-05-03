package com.rea.referentialtransparency

object RefTransExercises {

  // How would you push out the side-effects?
  def printWinner(p1: Int, p2: Int) =
    if (p1 > p2)
      println("player one is the winner!")
    else
      println("player two is the winner")

}
