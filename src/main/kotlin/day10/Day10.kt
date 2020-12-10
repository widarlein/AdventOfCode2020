package day10

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.pow

fun main(args: Array<String>) {
    val input = args.first().lines().map { it.toInt() }.sorted()

    val (diffs1, diffs3) = findDifferences(input)
    println("Part1: 1 and 3 jolt diffs multiplied: ${diffs1 * diffs3}")


    val arrangements = findArrangements(0, input)
//    Thread.sleep(60000*30)
    println("Part2: number of arrangements: $arrangements")
}

fun findArrs(adapters: List<Int>): Int {
    var sum = 1
    var i = 0
    while (i < adapters.size) {
        val diffWithoutAdapter = when (i) {
            0 -> adapters[i + 1]
            adapters.lastIndex -> 666
            else -> adapters[i + 1] - adapters[i - 1]
        }
        if (diffWithoutAdapter <= 3) {
            var diffsum = diffWithoutAdapter
            var iOffset = 0
            while (diffsum <= 3) {
                iOffset++
                diffsum = when (i+iOffset) {
//                    0 -> adapters[i + 1]
                    adapters.lastIndex -> 666
                    else -> adapters[i + iOffset + 1] - if ( i == 0) 0 else adapters[i - 1]
                }
            }
//            sum *= iOffset.toDouble().pow(2).toInt()
            sum *= 2.0.pow(iOffset).toInt()
            i += iOffset
        } else i++
    }
    return sum

//    for ((index, adapter) in adapters.withIndex()) {
//        val diffWithoutAdapter = when (index) {
//            0 -> adapters[index + 1] - start
//            adapters.lastIndex -> end - adapters[index - 1]
//            else -> adapters[index + 1] - adapters[index - 1]
//        }
//    }
}

val arrangementCount = AtomicLong()
val memory = mutableMapOf<Pair<Int, Int>, Int>()
fun findArrangements(start: Int = 0, adapters: List<Int>, end: Int = adapters.last() + 3): Int {

    for ((index, adapter) in adapters.withIndex()) {
        val diffWithoutAdapter = when (index) {
            0 -> if (adapters.size == 1) end - start else adapters[index + 1] - start
            adapters.lastIndex -> end - adapters[index - 1]
            else -> adapters[index + 1] - adapters[index - 1]
        }
        if (diffWithoutAdapter <= 3) { // current is removable, branching into 2 alternate realities
            val alt1 = memory[index to index + 1] ?: findArrangements(
                start = adapter,
                adapters = adapters.slice(index + 1..adapters.lastIndex),
                end = end).also { memory[index to index + 1] = it }

            val alt2 = memory[index - 1 to index + 1] ?: findArrangements(
                start = adapter,
                adapters = adapters.slice(index + 1..adapters.lastIndex),
                end = end).also { memory[index - 1 to index + 1] = it }
            return alt1 + alt2
//            return findArrangements(
//                start = adapter,
//                adapters = adapters.slice(index + 1..adapters.lastIndex),
//                end = end) +
//                    findArrangements(
//                        start = if (index == 0) start else adapters[index - 1],
//                        adapters = adapters.slice(index + 1..adapters.lastIndex),
//                        end = end
//                    )

        }
    }
    return 1
}

fun findDifferences(input: List<Int>): Pair<Int, Int> {
    val diffs = IntArray(4)

    diffs[input.first()]++
    input.forEachIndexed { i, adapter ->
        if (i == input.lastIndex) {
            diffs[3]++
        } else {
            val diff = input[i + 1] - adapter
            diffs[diff]++
        }
    }

    return diffs[1] to diffs[3]
}