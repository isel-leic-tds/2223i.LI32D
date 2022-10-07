package pt.isel.ui

class CommandFp<T>(
    val syntax: String,
    val show: (T) -> Unit,
    val action: (T?, List<String>) -> T?
)
