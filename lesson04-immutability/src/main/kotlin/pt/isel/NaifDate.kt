package pt.isel

class NaifDate(
    private val day: Int,
    private val month: Int,
    private val year: Int)
{
    init {
        if(day !in 1..31) throw IllegalArgumentException("Invalid day")
        if(month !in 1..12) throw IllegalArgumentException("Invalid month")
        if(year < 0) throw IllegalArgumentException("Invalid year")
    }

    fun nextMonth(m: Int) = if (m == 12) 1 else m + 1

    fun addDays(days:Int) :NaifDate{
        var days = days
        var d = day
        var m = month
        var y = year
        while(days > 0){
            if(days + d <= daysInMonth(m, y)){
                d += days
                days = 0
            }else{
                val daysLeft = daysInMonth(m, y) - d
                m = nextMonth(m)
                if(m == 1) y++
                days -= daysLeft + d
                d = 1
            }
        }
        return NaifDate(d, m, y)
    }


    private fun daysInMonth(m: Int, y: Int): Int {
        when (m) {
            1, 3, 5, 7, 8, 10, 12 -> {
                return 31
            }
            4, 6, 9, 11 -> return 30
            else -> return if (y % 4 == 0) 29 else 28
        }

    }

    override fun toString(): String {
        return "$day-$month-$year"
    }
}
