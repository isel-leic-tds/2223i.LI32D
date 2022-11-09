package pt.isel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = rememberWindowState(width = 300.dp, height = 300.dp)
    ) {
        Column {
            Greeting("ISEL")
            Greeting("Super")
            Row {
                Greeting("ISEL")
                Greeting("Super")
            }
            val clickCounter = remember { mutableStateOf(0) }
            ClickCounter(clickCounter) { clickCounter.value++}
        }
    }
}

@Composable fun Greeting(name: String) {
    Text("Hello $name")
}

@Composable fun ClickCounter(nrOfClicks: State<Int>, onClick: () -> Unit) {
    Text("Clicks ${nrOfClicks.value}")
    Button(
        onClick = onClick
    ) {
        Text("Click")
    }
}
