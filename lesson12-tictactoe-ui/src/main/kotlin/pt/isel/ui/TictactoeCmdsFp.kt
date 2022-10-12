package pt.isel.ui

import pt.isel.ttt.Board
import pt.isel.ttt.BoardRun
import pt.isel.ttt.Position
import pt.isel.ttt.toPlayer

val quitCommandFp = CommandFp<Board>(
    syntax = "quit",
    show = {},
    action = { _, _ -> null }
)

val startCommandFp = CommandFp<Board>(
    syntax = "start",
    show = { board -> printBoard(board) },
    action = { _, _ -> BoardRun() }
)

val playCommandFp = CommandFp<Board>(
    syntax = "play <X|O> <lin> <col>",
    show = { board -> printBoard(board) },
    action = ::tictactoePlay
)
