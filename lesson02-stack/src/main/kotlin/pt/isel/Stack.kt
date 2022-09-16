package pt.isel

interface Stack<T> {
    fun push(item: T)
    fun peek(): T
    fun isEmpty(): Boolean
    fun pop(): T
}
