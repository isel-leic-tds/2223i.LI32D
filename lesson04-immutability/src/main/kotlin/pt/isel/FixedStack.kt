package pt.isel

/**
 * Using an immutable approach
 */
class FixedStack<T>(private val head : Node<T>? = null)  : Stack<T> {
    /*
    private val head : Node<T>?
    constructor() { head = null }
    constructor(node: Node<T>?) { head = node }
    */

    override fun push(item: T): Stack<T> {
        val new = Node(item, head)
        return FixedStack(new)
    }

    override fun peek(): T = head?.item ?: throw NoSuchElementException("Empty stack.")

    override fun isEmpty() = head == null

    override fun pop(): Stack<T> {
        val toRemove = head ?: throw NoSuchElementException("Empty stack.")
        return FixedStack(toRemove.next)
    }
}
