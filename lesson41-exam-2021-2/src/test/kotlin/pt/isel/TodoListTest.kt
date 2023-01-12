package pt.isel

import java.lang.IllegalArgumentException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TodoListTest {

    @Test fun `Add valid task`(){
        val todoList = TodoList()
            .add(Task("Swim for one hour"))
            .add(Task("Run 10 Kms"))
        assertEquals("Swim for one hour", todoList.tasks[0].name)
        assertEquals("Run 10 Kms", todoList.tasks[1].name)
    }

    @Test fun `Try add duplicated task`(){
        val todoList = TodoList()
            .add(Task("Swim for one hour"))
            .add(Task("Run 10 Kms"))
            .add(Task("Swim for one hour"))
        assertEquals(2, todoList.tasks.size)
    }
    @Test fun `Try create invalid task`(){
        assertFailsWith<IllegalArgumentException> {
            Task("   Task name beginning with invalid spaces!")
        }
    }
}