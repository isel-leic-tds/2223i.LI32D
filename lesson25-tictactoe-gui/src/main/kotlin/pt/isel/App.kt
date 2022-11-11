package pt.isel

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.*
import pt.isel.ttt.*
import kotlin.system.exitProcess

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = WindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize.Unspecified
        )
    ) {
        val board: MutableState<Board> = remember { mutableStateOf(BoardRun()) }
        val dialogMessage: MutableState<String?> = remember { mutableStateOf(null) }
        /*
         * Build Menu Bar
         */
        this.TicTacToeMenu() { board.value = BoardRun() }
        /*
         * Build window content
         */
        BoardView(board.value) { pos ->
            val moves = board.value.moves
            val lastPlayer = if(moves.isEmpty()) Player.CIRCLE else moves.last().player
            try {
                val newBoard = board.value.play(pos, lastPlayer.turn())
                board.value = newBoard
            } catch (ex: Exception) {
                dialogMessage.value = ex.message
            }
        }
        DialogMessage(dialogMessage.value) { dialogMessage.value = null }
    }
}

@Composable
fun FrameWindowScope.TicTacToeMenu(onNew: () -> Unit) {
    MenuBar {
        Menu("TicTacToe", mnemonic = 'T') {
            Item("New", onClick = onNew)
            Item("Exit", onClick = { exitProcess(0) })
        }
    }
}

@Composable
fun DialogMessage(msg: String?, onClose: () -> Unit) {
    if(msg != null)
        Dialog(
            onCloseRequest = onClose,
            state = rememberDialogState(
                position = WindowPosition(Alignment.Center),
                size = DpSize.Unspecified
            )
        ) {
            Text(msg)
        }
}

@Composable
fun BoardView(board: Board, onCellClick: (Position) -> Unit) {
    Column {
        repeat(BOARD_SIZE) { line ->
            Row {
                repeat(BOARD_SIZE) { col ->
                    val move = board.moves.find { it.pos == Position(line, col) }
                    val symbol = move?.player?.symbol ?: ' '
                    Cell(symbol.toString(), Position(line, col), onCellClick)
                }
            }
        }
    }
}

@Composable
fun Cell(symbol: String, position: Position, onCellClick: (Position) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(100.dp)
            .border(2.dp, Color.Gray)
            .clickable(onClick = { onCellClick(position) })
    ) {
        Text(symbol, fontSize = 6.em)
    }
}
