package pt.isel

enum class Month {
    JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;

    fun nextMonth() :Month {
        return if(this == DEC) JAN else values()[this.ordinal + 1]
    }

    fun lastDay(year: Int) = when (this.ordinal + 1) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> if (year % 4 == 0) 29 else 28
    }
}
