package pt.isel

class TodoList(val tasks: List<Task> = emptyList())

fun TodoList.add(t: Task): TodoList {
    return if (tasks.contains(t)) this
    else TodoList(tasks + t)
}

fun TodoList.remove(t: Task): TodoList {
    return if (!tasks.contains(t)) this
    else TodoList(tasks.filter { it != t })
}
