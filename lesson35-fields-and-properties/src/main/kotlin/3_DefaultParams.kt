
private class Student3(n: Int=0, id:String="") {
    val number: Int = n
    val name: String = id
    init { print("Init -> ") }
    fun println() = println("$number: $name")
}

fun main() {
    Student3().println()
    Student3(876245,"Barbosa Adolfo").println()
    Student3(132345).println()
    Student3(id = "Ana Paula").println()
}
