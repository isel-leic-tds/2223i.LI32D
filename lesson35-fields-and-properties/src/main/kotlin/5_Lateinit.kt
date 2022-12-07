private class Z {
    lateinit var later : String
    fun check() = this::later.isInitialized
}

fun main() {
    val z = Z()
    //println(z.later) // UninitializedPropertyAccessException !!!!
    println(z.check())
    z.later = "Ok"
    println(z.later)
    println(z.check())
}
