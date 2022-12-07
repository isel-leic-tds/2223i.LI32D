
private class Student2(n: Int, id:String) {
    val number: Int
    val name: String
    init {
        print("Init -> ")
        number = n
        name = id
    }
    constructor() : this(0,"") {
        print("C() -> ")
    }
    fun println() = println("$number: $name")
}

fun main() {
    Student2().println()
    Student2(9834598,"Maria Papoila").println()
}