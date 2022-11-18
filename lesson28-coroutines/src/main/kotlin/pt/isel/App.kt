package pt.isel

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep
import kotlin.concurrent.thread

fun main() {
    // runBlocking {  coroutines() }
    // threads()
    runBlocking { manyCoroutines(100_000) }
    // manyThreads(100_000)
}
suspend fun manyCoroutines (total: Int) = coroutineScope {
    repeat(total) { // launch a lot of coroutines
        launch {
            delay(5000L)
            print(".")
        }
    }
}
fun manyThreads (total: Int) {
    repeat(total) { // launch a lot of coroutines
        thread {
            sleep(5000L)
            print(".")
        }
    }
}

suspend fun coroutines() = coroutineScope { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}
fun threads() {
    thread { // launch a new thread and continue
        sleep(1000L) // blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    thread { // launch a new thread and continue
        sleep(1000L) // blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }

    println("Hello") // main coroutine continues while a previous one is delayed
}

