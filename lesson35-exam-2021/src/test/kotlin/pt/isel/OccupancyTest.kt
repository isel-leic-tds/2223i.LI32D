package pt.isel

import org.junit.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class OccupancyTest {
    @Test fun `Exceed capacity throws Exception`() {
        val occ = Occupancy(20, 20)
        assertFailsWith<IllegalStateException> { occ.increment() }
    }
    @Test fun `Increment to the capacity stays full`() {
        val occ = Occupancy(19, 20).let { it.increment() }
        assertTrue { occ.isFull }
    }

}
