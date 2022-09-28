package pt.isel.oop

import java.lang.IllegalArgumentException

enum class Operator(val opr: Char) {
    /**
     * There are ONLY 4 possible instances of Operator:
     */
    SUM('+'),
    SUB('-'),
    DIV('/'),
    MUL('*');

    companion object {
        fun parse(c: Char): Operator {
            return values()
                .find { operator -> operator.opr == c }
                ?: throw IllegalArgumentException("Invalid operator $c")
        }
    }
}
