package pt.isel

import pt.isel.fp.CalculatorFp
import pt.isel.oop.CalculatorOop
import pt.isel.oop.IntLiteral
import pt.isel.oop.IntOperator
import pt.isel.oop.Operator
import java.lang.IllegalArgumentException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PrefixCalculatorTest {

    @Test fun `eval complex prefix expression successfully with Oop`() {
        val res = CalculatorOop.parsePrefix("* / 1024 12 - 45 + 2 17")
        assertEquals( 1024 / 12 * (45 - (2 + 17)), res)
        // assertEquals( 2210, res) // 2218
    }
    @Test fun `eval complex prefix expression successfully with FP`() {
        val res = CalculatorFp.parsePrefix("* / 1024 12 - 45 + 2 17")
        assertEquals( 1024 / 12 * (45 - (2 + 17)), res)
        // assertEquals( 2210, res) // 2218
    }

    @Test fun `eval complex prefix expression with missing operands`() {
        assertFailsWith<IllegalStateException> {
            CalculatorOop.parsePrefix("* / 1024 12 - 45 + 2")
        }
    }
    @Test fun `eval Literal expression`() {
        assertEquals(47, IntLiteral(47).eval())
    }
    @Test fun `eval Operator expression`() {
        assertEquals(47, IntOperator(Operator.SUM, IntLiteral(20), IntLiteral(27)).eval())
    }
    @Test fun `check illegal operator`() {
        assertFailsWith<IllegalArgumentException> {
            Operator.parse('#')
        }
    }
}
