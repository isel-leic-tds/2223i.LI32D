package pt.isel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import pt.isel.ttt.*
import java.net.URI
import java.net.URL
import java.net.http.HttpClient.newHttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers
import kotlin.concurrent.thread

class GameState {
    private val JsonValueRegex = """(?<=\"value\":\")[^\"]*""".toRegex()
    private val boardState: MutableState<Board> = mutableStateOf(BoardRun())
    private val messageState: MutableState<String?> = mutableStateOf(null)
    private val stopWatch = StopWatch()
    private val chuckNorrisState = mutableStateOf<String?>(null)

    val board get() = boardState.value
    val message get() = messageState.value
    val formattedTime get() = stopWatch.formattedTime
    val chuckNorris get() = chuckNorrisState.value

    fun startGame() {
        boardState.value = BoardRun()
        stopWatch.reset()
        stopWatch.start()
    }

    fun dismissMessage() {
        messageState.value = null
    }

    fun play(pos: Position) {
        val moves = boardState.value.moves
        val lastPlayer = if(moves.isEmpty()) Player.CIRCLE else moves.last().player
        try {
            requestChuckNorrisNio()
            val newBoard = boardState.value.play(pos, lastPlayer.turn())
            boardState.value = newBoard
            messageState.value = boardMessage(newBoard)
            if(message != null) stopWatch.pause()
        } catch (ex: Exception) {
            messageState.value = ex.message
        }
    }

    fun boardMessage(board: Board): String? {
        return when(board) {
            is BoardWinner -> "Game finished with winner ${board.winner}"
            is BoardDraw -> "Game finished with DRAW!"
            is BoardRun -> null
        }
    }

    /**
     * Do not use Threads for IO !!!!!
     * Threads are for CPU bound work.
     */
    fun requestChuckNorrisParallel() {
        thread {
            chuckNorrisState.value = URL("https://app.requestly.io/delay/1000/https://api.chucknorris.io/jokes/random")
                .readText()
                .let {
                    JsonValueRegex.find(it)?.value
                }
        }
    }

    val httpClient = newHttpClient()

    /**
     * Non-blocking version!
     */
    fun requestChuckNorrisNio() {
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://app.requestly.io/delay/1000/https://api.chucknorris.io/jokes/random"))
            .GET()
            .build()
        httpClient
            .sendAsync(request, BodyHandlers.ofString()) // Promise<HttpResponse>
            .thenApply(HttpResponse<String>::body)       // Promise<String>
            .thenAccept { chuckNorrisState.value = it }
    }
}
