package pt.isel.fp

object CalculatorFp {
    fun parsePrefix(input: String): Int {
        val tokens = input.split(" ").iterator()
        val root = parseTokens(tokens)
        return root.eval()
    }

    private fun parseTokens(tokens: Iterator<String>): IntExpression {
        if(!tokens.hasNext())
            throw IllegalStateException("Missing operands in expression")
        val token = tokens.next()
        val nr = token.toIntOrNull()
        if( nr != null)
            return IntLiteral(nr)
        val left = parseTokens(tokens)
        val right = parseTokens(tokens)
        return IntOperator(Operator.parse(token[0]), left, right)
    }
}
