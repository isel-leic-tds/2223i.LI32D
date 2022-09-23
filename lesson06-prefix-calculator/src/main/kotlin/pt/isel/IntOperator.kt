package pt.isel

import pt.isel.Operator.*
import java.lang.IllegalArgumentException

class IntOperator(
    private val operator: Operator,
    private val left: IntExpression,
    private val right: IntExpression
) : IntExpression
{
    override fun eval() = when(operator) {
        SUM -> left.eval() + right.eval()
        SUB -> left.eval() - right.eval()
        DIV -> left.eval() / right.eval()
        MUL -> left.eval() * right.eval()
    }
}
