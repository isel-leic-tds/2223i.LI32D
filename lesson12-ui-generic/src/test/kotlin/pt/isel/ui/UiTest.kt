package pt.isel.ui

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals

class UiTest {

    object exitCmdOoop : CommandOop<String> {
        override val syntax get() = "exit"
        override fun action(ctx: String?, args: List<String>) = null
        override fun show(ctx: String) { }
    }

    @Test fun `Check command action returning null finishes de readCommands loop`() {
        redirectInOut(listOf("exit")) {
            readCommandsOop<String>(mapOf(
                "EXIT" to exitCmdOoop
            ))
        }
    }

    @Test fun `Check invalid command`() {
        val lines = redirectInOut(listOf("dummy", "exit")) {
            readCommandsOop<String>(mapOf(
                "EXIT" to exitCmdOoop
            ))
        }
        assertEquals("Invalid command DUMMY", lines[0])
        lines.forEach { println(it) }
    }
    fun redirectInOut(stmts: List<String>, block: () -> Unit) : List<String> {
        /**
         * First store the standard input and output and
         * redirect to memory.
         */
        val oldOut = System.out
        val oldIn = System.`in`
        val mem = ByteArrayOutputStream()
        System.setOut(PrintStream(mem))
        System.setIn(ByteArrayInputStream("dummy\nexit\n".toByteArray()))
        /**
         * Execute given block.
         */
        block()
        /**
         * Restore standard input and output and return the resulting output lines.
         */
        System.setOut(oldOut)
        System.setIn(oldIn)
        return mem
            .toString()
            .split(System.lineSeparator())
            .map { if(it.startsWith("> ")) it.drop(2) else it }
    }
}
