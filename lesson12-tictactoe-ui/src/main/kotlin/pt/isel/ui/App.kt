package pt.isel.ui

import pt.isel.ttt.BOARD_SIZE
import pt.isel.ttt.Board
import pt.isel.ttt.BoardWinner
import pt.isel.ttt.Position

fun main() {
    readCommandsOop(mapOf(
        "QUIT" to QuitCommandOop,
        "START" to StartCommandOop,
        "PLAY" to PlayCommandOop
    ))
}

val sepLine = "\n"+"---+".repeat(BOARD_SIZE -1)+"---"

fun printBoard(board: Board) {
    Position.values.forEach { pos ->
        print(" ${board.get(pos)?.player?.symbol ?: " "} ")
        if (pos.col == BOARD_SIZE -1)
            if (pos.lin < BOARD_SIZE -1) println(sepLine)
            else println()
        else
            print("|")
    }
    if(board is BoardWinner)
        board.winner?.apply{ println("Player ${board.winner} wins.") }
}
/*
 O |   |
---+---+---
 X | O |
---+---+---
 O |   | X
 */

