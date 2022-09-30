package pt.isel.ttt

import java.lang.IllegalArgumentException

const val BOARD_SIZE = 3

class Board(
    val lastPlayer: Player = Player.CIRCLE,
    val moves: List<Move> = emptyList(),
    val winner: Player? = null
) {
    fun play(pos: Position, player: Player) : Board {
        if(winner != null) throw IllegalStateException("The player $winner won this game.")
        if(moves.size == BOARD_SIZE * BOARD_SIZE) throw IllegalStateException("This game has already finished with a draw.")
        if(lastPlayer == player) throw IllegalArgumentException("Player $player cannot play twice!")
        if(moves.any { m -> m.pos == pos }) throw IllegalArgumentException("Position $pos occupied! Please play on an empty position.")
        val m = Move(player, pos)
        val winner = if(checkWinner(m)) player else null
        return Board(player, moves + m, winner)
    }

    private fun checkWinner(m: Move): Boolean {
        val ourMoves = moves.filter { it.player == m.player } + m
        return ourMoves.count { it.pos.lin == m.pos.lin } == BOARD_SIZE
                || ourMoves.count { it.pos.col == m.pos.col } == BOARD_SIZE
                || ourMoves.count { it.pos.backslash } == BOARD_SIZE
                || ourMoves.count { it.pos.slash } == BOARD_SIZE
    }
}

