package pt.isel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pt.isel.ttt.*
import java.net.URI
import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers
import kotlin.concurrent.thread

class GameState(val scope: CoroutineScope, val storage: StorageAsync<String, Board>) {
    private val JsonValueRegex = """(?<=\"value\":\")[^\"]*""".toRegex()
    private val gameState: MutableState<GameAsync?> = mutableStateOf(null)
    private val messageState: MutableState<String?> = mutableStateOf(null)
    private val stopWatch = StopWatch()
    private val chuckNorrisState = mutableStateOf<String?>(null)

    val board get() = gameState.value
    val message get() = messageState.value
    val formattedTime get() = stopWatch.formattedTime
    val chuckNorris get() = chuckNorrisState.value

    fun startGame(name: String) = scope.launch{
        gameState.value = startGame(name, storage)
        stopWatch.reset()
        stopWatch.start()
    }

    fun dismissMessage() {
        messageState.value = null
    }

    fun play(pos: Position) {
        val (game, setGame) = gameState
        if(game == null) {
            messageState.value = "You should start a new Game before playing!"
            return
        }
        val moves = game.board.moves
        val lastPlayer = if(moves.isEmpty()) Player.CIRCLE else moves.last().player
        scope.launch { requestChuckNorrisNioCoroutine() }
        scope.launch {
            try {
                val newGame = game.play(storage, pos)
                setGame(newGame)
                messageState.value = boardMessage(newGame.board)
                if(message != null) stopWatch.pause()
            } catch (ex: Exception) {
                messageState.value = ex.message
                val newBoard = storage.load(game.name)
                if(newBoard != null && newBoard.moves != game.board.moves)
                    setGame(game.copy(board = newBoard))
            }
        }
    }
    /**
     * Do not use Threads for IO !!!!!
     * Threads are for CPU bound work.
     */
    fun requestChuckNorrisParallel()  = thread {
        chuckNorrisState.value = URL("https://app.requestly.io/delay/1000/https://api.chucknorris.io/jokes/random")
            .readText()
            .let {
                JsonValueRegex.find(it)?.value
            }
    }

    private val httpClient = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .build()

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
            .thenApply { JsonValueRegex.find(it)?.value }
            .thenAccept { chuckNorrisState.value = it }
    }

    suspend fun requestChuckNorrisNioCoroutine() {
        val body = io.ktor.client.HttpClient()
            .request("https://app.requestly.io/delay/1000/https://api.chucknorris.io/jokes/random")
            .bodyAsText()
        val joke = JsonValueRegex.find(body)?.value
        chuckNorrisState.value = joke
    }

    fun boardMessage(board: Board): String? {
        return when(board) {
            is BoardWinner -> "Game finished with winner ${board.winner}"
            is BoardDraw -> "Game finished with DRAW!"
            is BoardRun -> null
        }
    }
}
