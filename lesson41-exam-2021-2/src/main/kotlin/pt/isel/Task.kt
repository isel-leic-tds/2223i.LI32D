package pt.isel

data class Task(val name: String) {
    init {
        require(!name.startsWith(" ")) { "The name of the task cannot start with empty space" }
        require(name.length in 4..40) { "The name of the task cannot start with empty space" }
    }
}

fun String.isTaskCompatible(): Boolean = !this.startsWith(" ") && this.length in 4..40