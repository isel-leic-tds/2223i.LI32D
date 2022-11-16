package pt.isel

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.*
import pt.isel.ttt.BOARD_SIZE
import pt.isel.ttt.Board
import pt.isel.ttt.Position
import kotlin.system.exitProcess


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