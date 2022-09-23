package pt.isel

import kotlin.test.Test
import kotlin.test.assertEquals

class PrefixCalculatorTest {

    @Test fun `eval complex prefix expression successfully`() {
        val res = Calculator.parsePrefix("* / 1024 12 - 45 + 2 17")
        assertEquals( 1024 / 12 * (45 - (2 + 17)), res)
        // assertEquals( 2210, res) // 2218
    }
}
