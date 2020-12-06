package day3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day3KtTest {

    @Test
    fun parse() {
        val map = parseInput(input)
        assertEquals(11, map.height)
        map.print()
    }

    @Test
    fun countPart1() {
        val map = parseInput(input)
        val treeCount = countTreesPart1(map)
        assertEquals(7, treeCount)
    }

    /*
    Right 1, down 1.
    Right 3, down 1. (This is the slope you already checked.)
    Right 5, down 1.
    Right 7, down 1.
    Right 1, down 2.
     */
    @Test
    fun countPart2() {
        val slopes = listOf(
            1 to 1,
            3 to 1,
            5 to 1,
            7 to 1,
            1 to 2
        )

//        val slopes = listOf(3 to 1)
        val map = parseInput(input)
        val count = countTreesWIthSlope(map, slopes, true)
        assertEquals(listOf(2, 7, 3, 4, 2), count)
//        val product = count.fold(1) { sum, count -> 1 * count }
//        assertEquals(336, product)
    }
}

val input =
"""..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#"""