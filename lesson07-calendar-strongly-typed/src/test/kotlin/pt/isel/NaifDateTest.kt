package pt.isel

import pt.isel.Month.*
import kotlin.test.Test
import kotlin.test.assertEquals

class NaifDateTest {

    @Test fun `check if nextMonth works with a normal case`(){
        val date = NaifDate(28, OCT, 2022).nextMonth
        assertEquals(NOV,date)
    }

    @Test fun `check if nextMonth works with last month`(){
        val date = NaifDate(12, DEC, 2022).nextMonth
        assertEquals(JAN,date)
    }

    @Test fun `check if addDays works with random numbers of days, but doesnt change the month`(){
        val date = NaifDate(3, MAY, 2022).addDays(11)
        /*
        assertEquals(14, date.day)
        assertEquals(5, date.month)
        assertEquals(2022, date.year)
         */
        assertEquals(NaifDate(14,MAY,2022), date)
    }

    @Test fun `check if addDays works with random numbers of days, and changes the month`(){
        val date = NaifDate(28, SEP, 2022).addDays(73)
        assertEquals(NaifDate(10,DEC,2022), date)
    }

    @Test fun `check if addDays changes year correctly`(){
        val date = NaifDate(29, DEC, 2022).addDays(45)
        assertEquals(NaifDate(12,FEB,2023), date)
    }

    @Test fun `check if addDays works with february`(){
        val date = NaifDate(12, FEB, 2023).addDays(21)
        assertEquals(NaifDate(5,MAR,2023), date)
    }
}
