package pt.isel.ui

import pt.isel.FileStorage
import pt.isel.StringSerializer
import pt.isel.ttt.*

fun main() {
    val tttFolder = "out"
    val serializer = object : StringSerializer<Board> {
        override fun write(obj: Board) = obj.serialize()
        override fun parse(input: String) =  Board.deserialize(input)
    }
    val fs = FileStorage<String, Board>(tttFolder, { BoardRun() }, serializer)

    readCommandsOop(mapOf(
        "QUIT" to QuitCommandOop,
        "START" to StartCommandOop(fs),
        "PLAY" to PlayCommandOop(fs),
        "REFRESH" to RefreshCommandOop(fs),
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

