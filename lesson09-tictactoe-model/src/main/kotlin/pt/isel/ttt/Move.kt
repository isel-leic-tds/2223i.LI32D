package pt.isel.ttt

class Move(val player: Player, val pos: Position) {
    fun serialize() = "${player.symbol};${pos.lin};${pos.col}"
    companion object {
        fun deserialize(input: String) : Move {
            val words = input.split(";")
            return Move(
                words[0].toPlayer(),
                Position(words[1].toInt(), words[2].toInt()))
        }
    }
}
