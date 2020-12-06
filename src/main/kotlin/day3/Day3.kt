package day3

import kotlin.math.ceil

typealias TobogganMap = List<List<Boolean>>

fun main(args: Array<String>) {
    val map = parseInput(args.first())
    val treesCount = countTreesPart1(map)
    println("Part 1: No of trees: $treesCount")

    val slopes = listOf(
            1 to 1,
            3 to 1,
            5 to 1,
            7 to 1,
            1 to 2
    )

    val count = countTreesWIthSlope(map, slopes)
    val product = count.fold(1) { sum, count -> sum * count }
    println("Part 2: Product of slope tree counts: $product")
}

internal fun parseInput(input: String): TobogganMap {
    var map = input.lineSequence()
            .map { line ->
                line.map {
                    it == '#'
                }
            }
            .toList()
    val origMap = map.toCollection(mutableListOf())

    val neededWidth = map.height * 7
    val timesToExtend = ceil((neededWidth.toFloat() / map.width)).toInt() - 1

    repeat(timesToExtend) {
        map = map.mapIndexed { i, line ->
            line + origMap[i]
//            line + line
//            line.toMutableList().also { line ->
//
//            }
//            TODO()
        }
    }
    return map
}

fun countTreesPart1(map: TobogganMap): Int {
    var x = 0
    var y = 0
    var trees = 0
    while (x < map.width && y < map.height) {
        if (map[y][x]) {
            trees++
        }

        x += 3
        y++
    }
    return trees
}

fun countTreesWIthSlope(map: TobogganMap, slopes: List<Pair<Int, Int>>, printSteps: Boolean = false): List<Int> {

    return slopes.map { slope ->
        var x = 0
        var y = 0
        var trees = 0
        while (x < map.width && y < map.height) {
            if (printSteps) map.print(x to y)

            if (map[y][x]) {
                trees++
            }

            x += slope.first
            y += slope.second
        }
        trees
    }
}

val TobogganMap.height
    get() = this.size

val TobogganMap.width
    get() = this.first().size

fun TobogganMap.print() {
    this.forEach { line ->
        println(line.map {
            if (it) '#' else '.'
        })
    }
}

fun TobogganMap.print(position: Pair<Int, Int>) {
    val (x, y) = position
    println("=== MAP ===")
    this.forEachIndexed { mapY, line ->
        line.forEachIndexed { mapX, tree ->
            val c = if (mapX == x && mapY == y) {
                if (tree) 'X' else 'O'
            } else {
                if (tree) '#' else "."
            }
            print(c)
        }
        print('\n')
    }
}