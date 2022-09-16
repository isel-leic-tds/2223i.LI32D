package pt.isel

interface Stack<T> {
    fun push(item: T) : Stack<T>
    fun peek(): T
    fun isEmpty(): Boolean
    fun pop(): Stack<T>
}
