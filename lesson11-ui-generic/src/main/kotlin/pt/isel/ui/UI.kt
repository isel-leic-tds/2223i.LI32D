package pt.isel.ui

/**
 * Homework 5 and test it in the App of lesson11-tictactoe-ui
 */
fun <T> readCommandsFp(cmds: Map<String, CommandFp<T>>) {
    TODO()
}

/**
 * cmds is a Map<String, CommandOop>, e.g.:
 *   "QUIT" to QuitCommandOop,
 *   "START" to StartCommandOop,
 */
fun <T> readCommandsOop(cmds: Map<String, CommandOop<T>>) {
    var context: T? = null
    while(true) {
        /**
         * E.g. words = ["play", "X", "1", "0"]
         */
        print("> ")
        val words = readln().trim().split(" ")
        val cmdName = words[0].uppercase()
        val cmd: CommandOop<T>? = cmds[cmdName]
        if(cmd == null) {
            println("Invalid command $cmdName")
            continue
        }
        val args = words.drop(1) // e.g. ["X", "1", "0"]
        try {
            context = cmd.action(context, args)
            if(context == null) break
            cmd.show(context)
        }
        catch(e:Exception) {
            println(e.message)
        }
    }
}
