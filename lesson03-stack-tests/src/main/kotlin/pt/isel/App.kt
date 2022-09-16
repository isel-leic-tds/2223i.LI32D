package pt.isel

fun main() {
    val stk = makeStack<String>()
    stk.push("ISEL")
    stk.push("TDS")
    stk.push("LAE")
    println(stk.peek()) // TDS
    while( !stk.isEmpty() ) {
        println( stk.peek() ) // TDS ISEL
        stk.pop()
    }
    stk.peek() // throws NoSuchElementException
}

fun <T> makeStack(): Stack<T> {
    // Implement Stack without using Arrays or any existing collection
    return MutableStack<T>()
}
