package pt.isel

class IntLiteral(private val nr : Int) : IntExpression {
    override fun eval(): Int {
        return nr
    }
}
