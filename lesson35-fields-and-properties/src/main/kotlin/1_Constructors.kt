
private class Student1 {
    val number: Int
    val name: String

    init { print("Init -> ") }
    constructor(n: Int, id:String) {
        print("C(n,id) -> ")
        number = n
        name = id
    }
    constructor(n: Int) {
        print("C(n) -> ")
        number = n
        name = ""
    }
    constructor() {
        print("C() -> ")
        number = 0
        name =""
    }
    fun println() = println("$number: $name")
}

fun main() {
    Student1().println()
    Student1(823486,"Ze Manel").println()
    Student1(343242)
}
