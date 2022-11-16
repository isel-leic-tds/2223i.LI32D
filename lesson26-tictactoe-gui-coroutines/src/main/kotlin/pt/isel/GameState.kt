package pt.isel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import pt.isel.ttt.Board
import pt.isel.ttt.BoardRun
import pt.isel.ttt.Player
import pt.isel.ttt.Position

class GameState {

    private val boardState: MutableState<Board> = mutableStateOf(BoardRun())
    private val messageState: MutableState<String?> = mutableStateOf(null)

    val board get() = boardState.value
    val message get() = messageState.value

    fun startGame() {
        boardState.value = BoardRun()
    }

    fun dismissMessage() {
        messageState.value = null
    }

    fun play(pos: Position) {
        val moves = boardState.value.moves
        val lastPlayer = if(moves.isEmpty()) Player.CIRCLE else moves.last().player
        try {
            val newBoard = boardState.value.play(pos, lastPlayer.turn())
            boardState.value = newBoard
        } catch (ex: Exception) {
            messageState.value = ex.message
        }
    }
}
