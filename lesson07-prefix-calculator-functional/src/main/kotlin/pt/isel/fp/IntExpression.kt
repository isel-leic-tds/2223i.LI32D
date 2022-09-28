package pt.isel.fp

sealed class IntExpression
class IntLiteral(val nr : Int) : IntExpression()
class IntOperator(
    val operator: Operator,
    val left: IntExpression,
    val right: IntExpression
) : IntExpression()

fun IntExpression.eval() : Int {
    return when(this) {
        is IntLiteral -> this.nr
        is IntOperator -> operator.calc(this.left.eval(), this.right.eval())
    }
}
