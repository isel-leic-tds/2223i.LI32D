package pt.isel.oop

import java.lang.IllegalArgumentException

enum class Operator(val opr: Char) {
    /**
     * There are ONLY 4 possible instances of Operator:
     */
    SUM('+') { override fun calc(a: Int, b: Int) = a + b },
    SUB('-') { override fun calc(a: Int, b: Int) = a - b },
    DIV('/') { override fun calc(a: Int, b: Int) = a / b },
    MUL('*') { override fun calc(a: Int, b: Int) = a * b },;

    abstract fun calc(a: Int, b: Int) : Int;

    companion object {
        fun parse(c: Char): Operator {
            return values()
                .find { operator -> operator.opr == c }
                ?: throw IllegalArgumentException("Invalid operator $c")
        }
    }
}
