package pt.isel.ttt

import pt.isel.ttt.Player.*
import java.lang.IllegalArgumentException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BoardTest {

    @Test fun `We cannot instantiate a Position with coordinates out of board size`() {
        val ex = assertFailsWith<IllegalArgumentException> {
            Position(-3, 9)
        }
        assertEquals("Illegal coordinates must be between 0 and 2", ex.message)
    }

    @Test fun `When same player play twice it throws an IllegalArgumentException`() {
        val ex = assertFailsWith<IllegalArgumentException> {
            BoardRun()
                .play(Position(0,0), CROSS)
                .play(Position(0,1), CROSS)
        }
        assertEquals("Player CROSS cannot play twice!", ex.message)
    }
    @Test fun `Playing twice on same position throws IllegalArgumentException`() {
        val ex = assertFailsWith<IllegalArgumentException> {
            BoardRun()
                .play(Position(1,2), CROSS)
                .play(Position(1,2), CIRCLE)
        }
        assertEquals("Position (1,2) occupied! Please play on an empty position.", ex.message)
    }
    @Test fun `Cannot make more than 9 moves`() {
        val ex = assertFailsWith<IllegalStateException> {
            BoardRun()
                .play(Position(0,0), CROSS).play(Position(1,0), CIRCLE)
                .play(Position(0,1), CROSS).play(Position(1,1), CIRCLE)
                .play(Position(1,2), CROSS).play(Position(0,2), CIRCLE)
                .play(Position(2,0), CROSS).play(Position(2,1), CIRCLE)
                .play(Position(2,2), CROSS).play(Position(1,2), CIRCLE)

        }
        assertEquals("This game has already finished with a draw.", ex.message)
    }

    @Test fun `We cannot play on a bord that has a winner`() {
        val ex = assertFailsWith<IllegalStateException> {
            BoardWinner(emptyList(), CIRCLE)
                .play(Position(0,0), CROSS)
        }
        assertEquals("The player CIRCLE won this game.", ex.message)
    }
    @Test fun `Check CIRCLE wins the game with a line`() {
        val board = BoardRun()
            .play(Position(1,0), CROSS) .play(Position(0,0), CIRCLE)
            .play(Position(1,2), CROSS) .play(Position(0,1), CIRCLE)
            .play(Position(2,0), CROSS) .play(Position(0,2), CIRCLE)
        assertEquals(CIRCLE, (board as BoardWinner).winner)
        val ex = assertFailsWith<IllegalStateException> {
            board.play(Position(0,0), CIRCLE)
        }
        assertEquals("The player CIRCLE won this game.", ex.message)
    }
    @Test fun `Check CIRCLE wins the game with a backslash`() {
        val board = BoardRun()
            .play(Position(1,0), CROSS) .play(Position(0,0), CIRCLE)
            .play(Position(1,2), CROSS) .play(Position(1,1), CIRCLE)
            .play(Position(2,0), CROSS) .play(Position(2,2), CIRCLE)
        assertEquals(CIRCLE, (board as BoardWinner).winner)
        val ex = assertFailsWith<IllegalStateException> {
            board.play(Position(0,0), CIRCLE)
        }
        assertEquals("The player CIRCLE won this game.", ex.message)
    }
}
