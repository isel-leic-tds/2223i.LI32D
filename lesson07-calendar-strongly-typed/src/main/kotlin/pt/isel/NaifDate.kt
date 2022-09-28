package pt.isel

data class NaifDate(val day: Int, val month: Int, val year: Int)
{
    init {
        if(day !in 1..31) throw IllegalArgumentException("Invalid day")
        if(month !in 1..12) throw IllegalArgumentException("Invalid month")
        if(year < 0) throw IllegalArgumentException("Invalid year")
    }

    // custom/computed property => WITHOUT a backing FIELD
    val nextMonth
        get() = if (month == 12) 1 else month + 1

    //  WITH a backing FIELD daysInMonth
    // Computation is on <init> function
    val daysInMonth = when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> if (year % 4 == 0) 29 else 28
    }


    fun addDays(days:Int) : NaifDate {
        val daysToEndOfMonth = daysInMonth - day + 1
        return when {
            day + days <= daysInMonth -> NaifDate(day + days, month, year)
            month < 12 -> NaifDate(1, nextMonth, year).addDays(days - daysToEndOfMonth)
            else -> NaifDate(1, 1, year + 1).addDays(days - daysToEndOfMonth)
        }
    }

    override fun toString(): String {
        return "$day/$month/$year"
    }

/*
    override fun equals(other: Any?): Boolean {
        if(other !is NaifDate) return false
        return day == other.day && month == other.month && year == other.year
    }
 */
}
