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

        return Board(player, moves + Move(player, pos))
    }
}

