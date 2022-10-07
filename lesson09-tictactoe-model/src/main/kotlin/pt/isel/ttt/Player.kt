package pt.isel.ttt

import java.lang.IllegalArgumentException

enum class Player(val symbol: Char) {
    CROSS('X'), CIRCLE('O');
    fun turn() = if(this == CIRCLE) CROSS else CIRCLE
}

/**
 * It works regardless the letter case.
 * E.g. "x".toPlayer() => Player.CROSS
 * E.g. "X".toPlayer() => Player.CROSS
 */
fun String.toPlayer(): Player {
    require(this.length == 1) {"Illegal symbol with more than a single char."}
    return Player
        .values()
        .find { it.symbol == this.uppercase()[0] }
        ?: throw IllegalArgumentException("There is no player for symbol $this")
}
