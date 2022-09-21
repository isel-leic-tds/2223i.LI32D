package pt.isel

import kotlin.test.*

class FixedStackTest {
    @Test fun `check that new instance of FixedStack is empty`(){
        val stk = FixedStack<String>()
        assertTrue (stk.isEmpty())
    }

    @Test fun `check FixedStack is not empty after push`(){
        val stk = FixedStack<String>().push("ole")
        assertFalse(stk.isEmpty())
    }

    @Test fun `check that peek returns last pushed item`(){
        val stk = FixedStack<String>().push("ole")
        assertEquals("ole", stk.peek())
    }

    @Test fun `check that stack is empty after pop last item`(){
        val stk = FixedStack<String>()
            .push("ole")
            .pop()
        assertTrue (stk.isEmpty())
    }

    @Test fun `check that empty stack throws NoSuchElementException when tries to pop and is empty`(){
        val stk = FixedStack<String>()
        assertFailsWith<NoSuchElementException> { stk.pop() }
    }

}
