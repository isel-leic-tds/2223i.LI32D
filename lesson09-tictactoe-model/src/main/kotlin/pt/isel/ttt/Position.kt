package pt.isel.ttt

const val LAST_COORD = BOARD_SIZE - 1

data class Position(val lin: Int, val col: Int) {

    val backslash get() = lin == col
    val slash get() = lin == BOARD_SIZE - col - 1

    override fun toString(): String {
        return "($lin,$col)"
    }
}
