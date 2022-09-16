package pt.isel

class MutableStack<T> : Stack<T> {
    private var head : Node<T>? = null

    override fun push(item: T) {
        head = Node(item, head)
    }

    override fun peek() = head?.item ?: throw NoSuchElementException("The stack is empty")

    override fun isEmpty() = head == null

    override fun pop(): T {
        val node = head ?: throw NoSuchElementException("The stack is empty")
        head = node.next // remove the node
        return node.item
    }
}
