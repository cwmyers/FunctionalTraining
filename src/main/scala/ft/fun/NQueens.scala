package ft.fun

object Solution {

  type Board = Map[Int, Map[Int, Unit]]
  type Position = (Int, Int)

  def solveNQueens(n: Int): List[List[String]] = {

    val board = createBoard(n)

    val positions = toPos(board)
    val pos: List[List[(Int, Int)]] = positions.tails.flatMap(sol2(board, _, n, Nil)).toList

    pos.map(_.sorted).filterNot(_.length < n).map(render(_, n)).distinct
  }

  def sol2(freeSpaces: Board, remaining: List[Position], n: Int, placed: List[Position]): List[List[Position]] = {
    remaining match {
      case head :: tail =>
        val valid = freeSpaces.get(head._1).flatMap(r => r.get(head._2))
        valid match {
          case Some(_) => // go deeper
            val newPlaced = head :: placed
            val newBoard = removePositions(freeSpaces, getMoves(head, n))
            toPos(newBoard).tails.map(sol2(newBoard, _, n, newPlaced)).toList.flatten
          case None => sol2(freeSpaces, tail, n, placed)
        }

      case Nil => List(placed)
    }
  }

  def toPos(board: Board): List[Position] = {
    board.toList.flatMap(x => x._2.map(y => (x._1,y._1))).sorted
  }

  def createBoard(n: Int): Map[Int, Map[Int, Unit]] = {
    Range.inclusive(1, n).map(x => x -> Range.inclusive(1, n).map(y => y -> ()).toMap).toMap
  }

  def remove(board: Board, position: Position): Board = {
    val xMap: Map[Int, Unit] = board.getOrElse(position._1, Map.empty[Int, Unit])
    board.updated(position._1, xMap - position._2)
  }

  def removePositions(board: Board, positions: List[Position]): Board = {
    positions.foldLeft(board)(remove)
  }

  def render(positions: List[Position], n: Int): List[String] = {
    Range.inclusive(1, n).map(x => Range.inclusive(1, n).map { y =>
      if (positions.contains((x, y)))
        "Q"
      else
        "."
    }.mkString).toList
  }

  def getMoves(position: Position, n: Int): List[Position] = {
    val column: List[(Int, Int)] = Range.inclusive(1, n).map(y => position._1 -> y).toList
    val row: List[(Int, Int)] = Range.inclusive(1, n).map(x => x -> position._2).toList
    val diag = Range.inclusive(-n, n).map(x => position._1 + x -> (position._2 + x)).toList.filter{case (x,y) => x > 0 && x <=n && y>0 && y<=n}
    val diag2 = Range.inclusive(-n, n).map(x => position._1 - x -> (position._2 + x)).toList.filter{case (x,y) => x > 0 && x <=n && y>0 && y<=n}
    column ::: row ::: diag ::: diag2
  }
}
