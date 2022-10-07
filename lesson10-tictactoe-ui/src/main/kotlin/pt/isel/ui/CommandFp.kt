package pt.isel.ui

import pt.isel.ttt.Board

class CommandFp(
    val syntax: String,
    val show: (Board) -> Unit,
    val action: (Board?, List<String>) -> Board?
)
