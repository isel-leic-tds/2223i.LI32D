package pt.isel.ui

/**
 * Using return with or without label in lambdas
 */
fun foo(ints: List<Int>): Boolean {
    ints.filter { it % 2 != 0 }
    ints.filter {
        val isOdd = it % 2 != 0
        // <=> isOdd // implicit return
        return@filter isOdd
        // return isOdd // !!!! Mismatch returning from foo and NOT lambda
    }
    return false
}

/**
 * n - formal parameter
 * T - type parameter
 */
fun <T> foo(n: Int) {
}

fun <T, R> bar(a: T, b: R): R {
    return b
}

/**
 * msg - formal parameter
 * T - type parameter
 */
class A<T>(msg: String)

class Mix<T>(v: T) {
    fun <T, R> zas(a: T, b: R) {
    }
}

fun main() {
    Mix<String>("isel").zas(76, true)


    /**
     * Calling foo requires 2 arguments: type argument and actual parameter
     * E.g. type argument String and actual parameter 75
     */
    foo<String>(75)
    /**
     * Calling foo requires 2 arguments: type argument and actual parameter
     * E.g. type argument Long and actual parameter "Ola"
     */
    A<Long>("Ola")
    /**
     * Argument types may be INFERRED from actual parameters.
     */
    bar("ole", true)
}
