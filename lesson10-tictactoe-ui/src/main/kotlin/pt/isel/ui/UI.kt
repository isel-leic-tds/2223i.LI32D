package pt.isel.ui

import pt.isel.ttt.Board

/**
 * cmds is a Map<String, CommandOop>, e.g.:
 *   "QUIT" to QuitCommandOop,
 *   "START" to StartCommandOop,
 */
fun readCommandsOop(cmds: Map<String, CommandOop>) {
    var board: Board? = null
    while(true) {
        /**
         * E.g. words = ["play", "X", "1", "0"]
         */
        print("> ")
        val words = readln().trim().split(" ")
        val cmdName = words[0].uppercase()
        val cmd: CommandOop? = cmds[cmdName]
        if(cmd == null) {
            println("Invalid command $cmdName")
            continue
        }
        val args = words.drop(1) // e.g. ["X", "1", "0"]
        try {
            board = cmd.action(board, args)
            if(board == null) break
            cmd.show(board)
        }
        catch(e:Exception) {
            println(e.message)
        }
    }
}
