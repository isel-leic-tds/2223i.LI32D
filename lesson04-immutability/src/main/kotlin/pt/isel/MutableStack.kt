package pt.isel

class MutableStack<T> : Stack<T> {
    private var head : Node<T>? = null

    override fun push(item: T): Stack<T> {
        head = Node(item, head)
        return this
    }

    override fun peek() = head?.item ?: throw NoSuchElementException("The stack is empty")

    override fun isEmpty() = head == null

    override fun pop(): Stack<T> {
        val node = head ?: throw NoSuchElementException("The stack is empty")
        head = node.next // remove the node
        return this
    }
}
