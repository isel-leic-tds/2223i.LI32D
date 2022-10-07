package pt.isel.ttt

const val BOARD_SIZE = 3
const val MAX_MOVES = BOARD_SIZE * BOARD_SIZE

sealed class Board(val moves: List<Move>) {
    abstract fun play(pos: Position, player: Player) : Board
    fun get(pos: Position): Move? {
        return moves.find { it.pos == pos }
    }
}
class BoardDraw(moves: List<Move>) : Board(moves) {
    override fun play(pos: Position, player: Player): Board {
        throw IllegalStateException("This game has already finished with a draw.")
    }
}
class BoardWinner(moves: List<Move>, val winner: Player) : Board(moves) {
    override fun play(pos: Position, player: Player): Board {
        throw IllegalStateException("The player $winner won this game.")
    }
}

class BoardRun (moves: List<Move> = emptyList(), val lastPlayer: Player = Player.CIRCLE) : Board(moves) {
    override fun play(pos: Position, player: Player) : Board {
        require(lastPlayer != player) {"Player $player cannot play twice!" }
        require(!moves.any { m -> m.pos == pos }) {"Position $pos occupied! Please play on an empty position."}
        val m = Move(player, pos)
        return when {
            checkWinner(m) -> BoardWinner(moves + m, player)
            moves.size == MAX_MOVES - 1 -> BoardDraw(moves + m)
            else -> BoardRun(moves + m, player)
        }
    }

    private fun checkWinner(m: Move): Boolean {
        val ourMoves = moves.filter { it.player == m.player } + m
        return ourMoves.count { it.pos.lin == m.pos.lin } == BOARD_SIZE
                || ourMoves.count { it.pos.col == m.pos.col } == BOARD_SIZE
                || ourMoves.count { it.pos.backslash } == BOARD_SIZE
                || ourMoves.count { it.pos.slash } == BOARD_SIZE
    }
}

