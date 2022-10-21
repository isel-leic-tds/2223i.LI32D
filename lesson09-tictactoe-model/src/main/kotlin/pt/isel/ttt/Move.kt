package pt.isel.ttt

data class Move(val player: Player, val pos: Position) {
    fun serialize() = "${player.symbol}${pos.lin}${pos.col}"
    companion object {
        /**
         * @param input Must have 3 characters corresponding to X|O line and column!
         */
        fun deserialize(input: String) : Move {
            require(input.length == 3) { "The input string must have 3 characters corresponding to X|O line and column!" }
            return Move(
                input[0].toString().toPlayer(),
                Position(input[1].toString().toInt(), input[2].toString().toInt()))
        }
    }
}
