package pt.isel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class StopWatch() {

    var formattedTime by mutableStateOf("00:00:000")
        private set

    private val scope = CoroutineScope(Dispatchers.Unconfined)
    private var isActive = false
    private var timeMillis = 0L
    private var lastTimestamp = 0L

    fun start() {
        if(isActive) return

        scope.launch {
            println("Thread ${Thread.currentThread().id} + ${Thread.currentThread().name}")
            lastTimestamp = System.currentTimeMillis()
            this@StopWatch.isActive = true
            while(this@StopWatch.isActive) {
                delay(100L)
                timeMillis += System.currentTimeMillis() - lastTimestamp
                lastTimestamp = System.currentTimeMillis()
                formattedTime = formatTime(timeMillis)
            }
        }
    }

    fun pause() {
        isActive = false
    }

    fun reset() {
        timeMillis = 0L
        lastTimestamp = 0L
        formattedTime = "00:00:000"
        isActive = false
    }

    private fun formatTime(timeMillis: Long): String {
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timeMillis),
            ZoneId.systemDefault()
        )
        val formatter = DateTimeFormatter.ofPattern(
            "mm:ss:SSS",
            Locale.getDefault()
        )
        return localDateTime.format(formatter)
    }
}
