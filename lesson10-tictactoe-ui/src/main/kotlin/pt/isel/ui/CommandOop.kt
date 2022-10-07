package pt.isel.ui

import pt.isel.ttt.Board

interface CommandOop {
    val syntax: String
    fun show(board: Board) : Unit
    fun action(board: Board?, args: List<String>): Board?
}
