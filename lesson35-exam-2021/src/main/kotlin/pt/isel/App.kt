package pt.isel

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

const val DEFAULT_CAPACITY: Int = 7

fun main(args: Array<String>) = application {
    MaterialTheme {
        val state = WindowState(width= 250.dp, height= Dp.Unspecified)
        Window(onCloseRequest= ::exitApplication, state= state, title= "Occupancy") {
            MainContent(args.firstOrNull()?.toIntOrNull() ?: DEFAULT_CAPACITY)
        }
    }
}
