package pt.isel

import pt.isel.Month.DEC
import pt.isel.Month.JAN

data class NaifDate(val day: Int, val month: Month, val year: Int)
{
    init {
        if(day !in 1..31) throw IllegalArgumentException("Invalid day")
        if(year < 0) throw IllegalArgumentException("Invalid year")
    }

    // custom/computed property => WITHOUT a backing FIELD
    val nextMonth
        get() = month.nextMonth()

    fun addDays(days:Int) : NaifDate {
        val daysToEndOfMonth = month.lastDay(year) - day + 1
        return when {
            day + days <= month.lastDay(year) -> NaifDate(day + days, month, year)
            month != DEC -> NaifDate(1, nextMonth, year).addDays(days - daysToEndOfMonth)
            else -> NaifDate(1, JAN, year + 1).addDays(days - daysToEndOfMonth)
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
