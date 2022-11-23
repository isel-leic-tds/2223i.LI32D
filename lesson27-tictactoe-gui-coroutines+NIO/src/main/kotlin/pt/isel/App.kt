package pt.isel

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import pt.isel.ttt.Board
import pt.isel.ttt.BoardRun

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = WindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize.Unspecified
        )
    ) {
        val scope = rememberCoroutineScope()
        val game = remember { GameState(
            scope,
            FileStorageAsync<String, Board>("out", { BoardRun() }, BoardSerializer)
        )}
        /*
         * Build GUI
         */
        TicTacToeMenu(game::startGame)
        Column {
            StopWatchView(game.formattedTime)
            BoardView(game.board?.board, game::play)
            DialogMessage(game.message, game::dismissMessage)
            Text(game.chuckNorris ?: "")
        }
    }
}

object BoardSerializer: StringSerializer<Board> {
    override fun write(obj: Board) = obj.serialize()
    override fun parse(input: String) =  Board.deserialize(input)
}
