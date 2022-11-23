package pt.isel

import pt.isel.ttt.Board
import pt.isel.ttt.Player
import pt.isel.ttt.Player.CIRCLE
import pt.isel.ttt.Player.CROSS
import pt.isel.ttt.Position

data class GameAsync(
    val name: String,
    val board: Board,
    val player: Player = CROSS
)

suspend fun startGame(name: String, storage: StorageAsync<String, Board>) : GameAsync {
    val board = storage.load(name) ?: return GameAsync(name, storage.new(name), CROSS)
    if(board.moves.size <= 1) return GameAsync(name, board, CIRCLE)
    // storage.delete(name)
    return GameAsync(name, storage.new(name), CROSS)
}

suspend fun GameAsync.play(storage: StorageAsync<String, Board>, pos: Position) : GameAsync {
    val board = this.board.play(pos, this.player)
    storage.save(this.name, board)
    return this.copy(board = board)
}
