package pt.isel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun MainContent(capacity: Int) {
    val (occ, setOcc) = remember { mutableStateOf(Occupancy(capacity = capacity)) }
    Row {
        OccupancyView(occ)
        Column {
            Button(enabled = !occ.isFull, onClick = {setOcc(occ.increment())}) {
                Text("+")
            }
            Button(enabled = !occ.isEmpty, onClick = {setOcc(occ.decrement())}) {
                Text("-")
            }
        }
    }
}

@Composable
fun OccupancyView(occupancy: Occupancy) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(100.dp)
    ) {
        Text("${occupancy.current}", fontSize = 2.em)
        Text("of")
        Text("${occupancy.capacity}", fontSize = 2.em)
    }
}
