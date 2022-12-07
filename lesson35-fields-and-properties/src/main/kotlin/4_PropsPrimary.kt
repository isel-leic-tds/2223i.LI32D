
private class Student4(val number: Int=0, val name:String="") {
    init { print("Init -> ") }

    fun println() = println("$number: $name")
}

fun main() {
    Student4().println()
    Student4(876245,"Barbosa Adolfo").println()
    Student4(132345).println()
    Student4(name = "Ana Paula").println()
}
