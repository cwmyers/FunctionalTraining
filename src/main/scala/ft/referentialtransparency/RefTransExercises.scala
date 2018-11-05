package ft.referentialtransparency

object RefTransExercises {

  // How would you push out the side-effects?  How would you test the logic
  // without performing the side-effect?
  def printWinner(p1: Int, p2: Int) =
    if (p1 > p2)
      println("player one is the winner!")
    else
      println("player two is the winner!")

}
