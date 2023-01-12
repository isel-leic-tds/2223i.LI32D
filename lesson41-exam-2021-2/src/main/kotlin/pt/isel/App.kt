package pt.isel

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main(args: Array<String>) = application {
    val state = WindowState(width= 450.dp, height= 200.dp)
    val todo = remember { mutableStateOf(TodoList(listOf(
        Task("Run 10 Kms"),
        Task("Swim for 1 hour")
    ))) }

    Window(onCloseRequest= ::exitApplication, state= state, title= "Todo List") {
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            TaskNameEditor(
                onAdd = { t: Task -> todo.value = todo.value.add(t) },
                onRemove = { t: Task -> todo.value = todo.value.remove(t) })
            StringListView(title = "Tasks:", values = todo.value.tasks.map { it.name })
        }
    }
}

@Composable
fun StringListView(title: String, values: List<String>) {
    Column {
        Text(title, fontSize = 2.em)
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .border(3.dp, Color.Gray)
                .padding(10.dp)
                .size(width = 150.dp, height = 200.dp)
        ) {
            values.forEach {
                Text(it)
            }
        }

    }
}

@Composable
fun TaskNameEditor(onAdd: (Task) -> Unit, onRemove: (Task) -> Unit) {
    val (name, setName) = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            name,
            modifier = Modifier.width(250.dp),
            label = { Text("Task: ") },
            onValueChange =
        {
            setName(it)
        })
        Button(enabled = name.isTaskCompatible(), onClick = { onAdd(Task(name))} ) {
            Text("Add")
        }
        Button(enabled = name.isTaskCompatible(), onClick = { onRemove(Task(name))} ) {
            Text("Remove")
        }
    }

}
