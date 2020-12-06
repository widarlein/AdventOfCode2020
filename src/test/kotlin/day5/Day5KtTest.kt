package day5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5KtTest {

    @Test
    fun part1() {
        val row = "BFFFBBFRRR".toRow()
        val column = "BFFFBBFRRR".toColumn()
        assertEquals(70, row)
        assertEquals(7, column)
    }
}