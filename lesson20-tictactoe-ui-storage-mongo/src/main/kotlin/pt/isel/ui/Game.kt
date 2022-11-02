package pt.isel.ui

import pt.isel.Storage
import pt.isel.ttt.Board
import pt.isel.ttt.Player
import pt.isel.ttt.Player.CIRCLE
import pt.isel.ttt.Player.CROSS
import pt.isel.ttt.Position

data class Game(
    val name: String,
    val board: Board,
    val player: Player = CROSS
)

fun startGame(name: String, storage: Storage<String, Board>) : Game {
    val board = storage.load(name) ?: return Game(name, storage.new(name), CROSS)
    if(board.moves.size <= 1) return Game(name, board, CIRCLE)
    storage.delete(name)
    return Game(name, storage.new(name), CROSS)
}

fun Game.play(storage: Storage<String, Board>, lin: Int, col: Int) : Game {
    val board = this.board.play(Position(lin, col), this.player)
    storage.save(this.name, board)
    return this.copy(board = board)
}
