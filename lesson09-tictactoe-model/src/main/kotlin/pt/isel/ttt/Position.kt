package pt.isel.ttt

import java.lang.IllegalArgumentException

const val LAST_COORD = BOARD_SIZE - 1

data class Position private constructor(val lin: Int, val col: Int) {
    val backslash get() = lin == col
    val slash get() = lin == BOARD_SIZE - col - 1

    companion object {
        val values = (0 until MAX_MOVES).map { index -> Position(index / BOARD_SIZE, index % BOARD_SIZE ) }
        operator fun invoke(l: Int, c: Int) : Position {
            require(l in 0 until BOARD_SIZE) { "Illegal coordinates must be between 0 and $LAST_COORD" }
            require(c in 0 until BOARD_SIZE) { "Illegal coordinates must be between 0 and $LAST_COORD" }
            val index = l * BOARD_SIZE + c
            return values[index]
        }
    }

    override fun toString(): String {
        return "($lin,$col)"
    }
}
