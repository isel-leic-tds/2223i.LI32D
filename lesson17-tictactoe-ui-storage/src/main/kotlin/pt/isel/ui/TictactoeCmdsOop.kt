package pt.isel.ui

import pt.isel.Storage
import pt.isel.ttt.Board
import pt.isel.ttt.Position

object QuitCommandOop: CommandOop<Game> {
    override val syntax get() = "quit"
    override fun show(game: Game) { }
    override fun action(game: Game?, args: List<String>) = null
}

class StartCommandOop(val storage: Storage<String, Board>) : CommandOop<Game> {
    override val syntax get() = "start name"
    override fun show(game: Game) = printBoard(game.board)
    override fun action(game: Game?, args: List<String>): Game {
        require(args.size == 1) { "You should provide a unique name for the new game!" }
        return startGame(args[0], storage)
    }
}
class RefreshCommandOop(val storage: Storage<String, Board>) : CommandOop<Game> {
    override val syntax get() = "refresh"
    override fun show(game: Game) = printBoard(game.board)
    override fun action(game: Game?, args: List<String>): Game {
        require(game != null) { "You should start a new game before playing" }
        val board = storage.load(game.name)
        check(board != null) { "Cannot refresh without known game!" }
        return game.copy(board = board)
    }
}

/**
 * Execute the play() with given Game and parameters from args.
 *   args contains the parameters e.g. "1 2"
 */
class PlayCommandOop(val storage: Storage<String, Board>) : CommandOop<Game> {
    override val syntax get() = "play <lin> <col>"
    override fun show(game: Game) = printBoard(game.board)
    override fun action(game: Game?, args: List<String>) = tictactoePlay(storage, game, args)
}


fun tictactoePlay(storage: Storage<String, Board>, game: Game?, args: List<String>): Game {
    require(args.size == 2) { "Provide 2 arguments corresponding to line and column." }
    require(game != null) { "You should start a new game before playing" }
    val pos = Position(args[0].toInt(), args[1].toInt())
    val board = game.board.play(pos, game.player)
    storage.save(game.name, board)
    return game.copy(board = board)
}
