package pt.isel

import kotlin.test.*

class MutableStackTest {
    @Test fun `check that new instance of MutableStack is empty`(){
        val stk = MutableStack<String>()
        assertTrue (stk.isEmpty())
    }

    @Test fun `check MutableStack is not empty after push`(){
        val stk = MutableStack<String>()
        stk.push("ole")
        assertFalse(stk.isEmpty())
    }

    @Test fun `check that peek returns last pushed item`(){
        val stk = MutableStack<String>()
        stk.push("ole")
        assertEquals("ole", stk.peek())
    }

    @Test fun `check that stack is empty after pop last item`(){
        val stk = MutableStack<String>()
        stk.push("ole")
        stk.pop()
        assertTrue (stk.isEmpty())
    }

    @Test fun `check that empty stack throws NoSuchElementException when tries to pop and is empty`(){
        val stk = MutableStack<String>()
        assertFailsWith<NoSuchElementException> { stk.pop() }
    }

}
