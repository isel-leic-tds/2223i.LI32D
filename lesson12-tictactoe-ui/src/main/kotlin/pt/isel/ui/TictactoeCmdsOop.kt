package pt.isel.ui

import pt.isel.ttt.Board
import pt.isel.ttt.BoardRun
import pt.isel.ttt.Position
import pt.isel.ttt.toPlayer

object QuitCommandOop: CommandOop<Board> {
    override val syntax get() = "quit"
    override fun show(board: Board) { }
    override fun action(board: Board?, args: List<String>) = null
}

object StartCommandOop : CommandOop<Board> {
    override val syntax get() = "start"
    override fun show(board: Board) = printBoard(board)
    override fun action(board: Board?, args: List<String>) = BoardRun()
}

/**
 * Execute the play() with given Board and parameters from args.
 *   args contains the parameters e.g. "X 1 2"
 */
object PlayCommandOop : CommandOop<Board> {
    override val syntax get() = "play <X|O> <lin> <col>"
    override fun show(board: Board) = printBoard(board)
    override fun action(board: Board?, args: List<String>): Board? {
        require(args.size == 3) { "Provide 3 arguments corresponding to player, line and column." }
        val player = args[0].toPlayer()
        val pos = Position(args[1].toInt(), args[2].toInt())
        return board?.play(pos, player)
    }
}
