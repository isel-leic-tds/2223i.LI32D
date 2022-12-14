package pt.isel.oop

import pt.isel.oop.Operator.*
import java.lang.IllegalArgumentException

class IntOperator(
    private val operator: Operator,
    private val left: IntExpression,
    private val right: IntExpression
) : IntExpression
{
    override fun eval() = operator.calc(left.eval(), right.eval())
}
