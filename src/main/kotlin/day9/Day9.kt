package day9

const val PREAMBLE_SIZE = 25

fun main(args: Array<String>) {
    val input = args.first().lines().map { it.toLong()}

    val invalid = findInvalidNumber(input)

    val addends = findContiguousAddends(invalid, input)

    println("Part2: The sum of the smallest and larges addend is ${addends.minOrNull()!! + addends
        .maxOrNull()!!}")

}

fun findInvalidNumber(input: List<Long>): Long {
    input.drop(PREAMBLE_SIZE).forEachIndexed { i, n ->
        if (!isSumOf(n, input.slice(i.realIndex() - PREAMBLE_SIZE until i.realIndex()))) {
            println("Part1: The number $n is not a sum of the previous $PREAMBLE_SIZE numbers")
            return n
        }
    }
    error("No invalid number found!")
}

fun findContiguousAddends(invalid: Long, input: List<Long>): List<Long> {
    for ((index, l) in input.withIndex()) {
        var sum = input[index]
        var addendIndex = index + 1
        while (sum < invalid) {
            sum += input[addendIndex]
            if (sum == invalid) {
                return input.slice(index..addendIndex)
            }
            addendIndex++
        }
    }
    error("No addends were found")
}

fun isSumOf(number: Long, potentialAddends: List<Long>): Boolean {
    potentialAddends.forEachIndexed { i, outer ->
        potentialAddends.forEachIndexed { j, inner ->
            if (i != j && outer + inner == number) {
                return true
            }
        }
    }
    return false
}

fun Int.realIndex() = this + PREAMBLE_SIZE