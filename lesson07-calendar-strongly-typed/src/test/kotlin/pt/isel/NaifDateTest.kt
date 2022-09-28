package pt.isel

import kotlin.test.Test
import kotlin.test.assertEquals

class NaifDateTest {

    @Test fun `check if nextMonth works with a normal case`(){
        val date = NaifDate(28, 10, 2022).nextMonth
        assertEquals(11,date)
    }

    @Test fun `check if nextMonth works with last month`(){
        val date = NaifDate(12, 12, 2022).nextMonth
        assertEquals(1,date)
    }

    @Test fun `check if addDays works with random numbers of days, but doesnt change the month`(){
        val date = NaifDate(3, 5, 2022).addDays(11)
        /*
        assertEquals(14, date.day)
        assertEquals(5, date.month)
        assertEquals(2022, date.year)
         */
        assertEquals(NaifDate(14,5,2022), date)
    }

    @Test fun `check if addDays works with random numbers of days, and changes the month`(){
        val date = NaifDate(28, 9, 2022).addDays(73)
        assertEquals(NaifDate(10,12,2022), date)
    }

    @Test fun `check if addDays changes year correctly`(){
        val date = NaifDate(29, 12, 2022).addDays(45)
        assertEquals(NaifDate(12,2,2023), date)
    }

    @Test fun `check if addDays works with february`(){
        val date = NaifDate(12, 2, 2023).addDays(21)
        assertEquals(NaifDate(5,3,2023), date)
    }
}
