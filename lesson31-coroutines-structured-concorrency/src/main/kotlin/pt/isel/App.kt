package pt.isel

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*


val urls = listOf( "https://github.com/", "https://stackoverflow.com/", "http://dzone.com/", "https://www.infoq.com/")

fun main() = runBlocking {
    // getAllAndSumSequential(urls)
    getAllAndSumConcurrent(urls)
}

val client = HttpClient()

suspend fun CoroutineScope.getAllAndSumConcurrent(uls: List<String>) {
    var sum = urls
        .map { async { fetchAndGetBodySize(it) }} // List<Promise<Int>>
        .sumOf { it.await() }
    println("TOTAL bodies size = $sum")
}

suspend fun CoroutineScope.getAllAndSumSequential(uls: List<String>) {
    var sum = 0
    for(url in urls) {
        val job: Deferred<Int> = async { // <=> Promise<Int>
            fetchAndGetBodySize(url)
        }
        sum += job.await()
    }
    println("TOTAL bodies size = $sum")
}

suspend fun fetchAndGetBodySize(url: String): Int {
    println("Requesting $url")
    val body = client.request(url).bodyAsText()
    println("=========> size = ${body.length}")
    return body.length
}
