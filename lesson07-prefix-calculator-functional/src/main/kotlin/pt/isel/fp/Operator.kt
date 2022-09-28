package pt.isel.fp

import java.lang.IllegalArgumentException

enum class Operator(val opr: Char) {
    /**
     * There are ONLY 4 possible instances of Operator:
     */
    SUM('+'), SUB('-'), DIV('/'), MUL('*');

    companion object {
        fun parse(c: Char): Operator {
            return values()
                .find { operator -> operator.opr == c }
                ?: throw IllegalArgumentException("Invalid operator $c")
        }
    }
}

fun Operator.calc(a: Int, b: Int): Int {
    return when(this) {
        Operator.SUM -> a + b
        Operator.SUB -> a - b
        Operator.DIV -> a / b
        Operator.MUL -> a * b
    }
}
