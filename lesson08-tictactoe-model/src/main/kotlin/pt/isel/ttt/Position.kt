package pt.isel.ttt

data class Position(val lin: Int, val col: Int) {
    override fun toString(): String {
        return "($lin,$col)"
    }
}
