package pt.isel

data class Occupancy(val current: Int = 0, val capacity: Int) {
    init {
        require(capacity >= 0 && current in 0 .. capacity)
    }
    val isEmpty get() = current == 0
    val isFull get() = current == capacity
}

fun Occupancy.increment(): Occupancy {
    check(!isFull)
    return copy(current=current+1)
}
fun Occupancy.decrement(): Occupancy {
    check(!isEmpty)
    return copy(current=current-1)
}
