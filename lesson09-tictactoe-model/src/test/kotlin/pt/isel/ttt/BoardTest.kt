package pt.isel.ttt

import pt.isel.ttt.Player.*
import java.lang.IllegalArgumentException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BoardTest {

    @Test fun `When same player play twice it throws an IllegalArgumentException`() {
        val ex = assertFailsWith<IllegalArgumentException> {
            Board()
                .play(Position(0,0), CROSS)
                .play(Position(0,1), CROSS)
        }
        assertEquals("Player CROSS cannot play twice!", ex.message)
    }
    @Test fun `Playing twice on same position throws IllegalArgumentException`() {
        val ex = assertFailsWith<IllegalArgumentException> {
            Board()
                .play(Position(1,2), CROSS)
                .play(Position(1,2), CIRCLE)
        }
        assertEquals("Position (1,2) occupied! Please play on an empty position.", ex.message)
    }
    @Test fun `Cannot make more than 9 moves`() {
        val ex = assertFailsWith<IllegalStateException> {
            Board()
                .play(Position(0,0), CROSS).play(Position(1,0), CIRCLE)
                .play(Position(0,1), CROSS).play(Position(1,1), CIRCLE)
                .play(Position(0,2), CROSS).play(Position(1,2), CIRCLE)
                .play(Position(2,0), CROSS).play(Position(2,1), CIRCLE)
                .play(Position(2,2), CROSS).play(Position(1,2), CIRCLE)

        }
        assertEquals("This game has already finished with a draw.", ex.message)
    }

    @Test fun `We cannot play on a bord that has a winner`() {
        val ex = assertFailsWith<IllegalStateException> {
            Board(winner = CIRCLE)
                .play(Position(0,0), CROSS)

        }
        assertEquals("The player CIRCLE won this game.", ex.message)
    }
}
