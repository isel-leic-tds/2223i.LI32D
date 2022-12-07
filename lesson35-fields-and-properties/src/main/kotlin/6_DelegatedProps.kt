import kotlin.reflect.KProperty

class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class DelegateStringNotEmpty {
    var str : String? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = str

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        require(!value.isNullOrEmpty())
        str = value
    }
}


fun main() {
    val ex = Example()
    ex.p = "isel"
    println(ex.p)

    var str by DelegateStringNotEmpty()
    str = "isel"
    println(str)
    str = "" // IllegalArgumentException
}
