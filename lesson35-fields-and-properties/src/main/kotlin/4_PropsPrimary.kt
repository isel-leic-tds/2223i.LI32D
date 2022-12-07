
private class Student5(val number: Int=0, val name:String="") {
    init { print("Init -> ") }

    fun println() = println("$number: $name")
}

fun main() {
    Student5().println()
    Student5(876245,"Barbosa Adolfo").println()
    Student5(132345).println()
    Student5(name = "Ana Paula").println()
}