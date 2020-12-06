package day2

fun main(args: Array<String>) {
    val passwordLines = args.first().lines()

    val valids1 = passwordLines.fold(0) { sum, line ->
        val passLine = parseRules(line)
        sum + passLine.validate()
    }

    println("Part 1: Valid passwords: $valids1")

    val valids2 = passwordLines.fold(0) { sum, line ->
        val passLine = parseRules(line)
        sum + passLine.validate2()
    }

    println("Part 2: Valid passwords: $valids2")
}

private fun parseRules(passwordLine: String): PassLine {
    val match = """(\d+)-(\d+) (\w): (\w+)""".toRegex().find(passwordLine)
    match?.groupValues?.let {
        return PassLine(it[1].toInt() to it[2].toInt(), it[3].first(), it[4])
    }
    error("Could not parse bounds from line $passwordLine")
}


infix fun <A,B,C> Pair<A, B>.and(c: C) = Triple(first, second, c)

data class PassLine(val bounds: Pair<Int, Int>, val char: Char, val password: String) {
    fun validate(): Int {
        val charCount = password.count { it == char }
        return if (charCount in bounds.first..bounds.second) 1 else 0
    }

    fun validate2(): Int = if ((password[bounds.first - 1] == char) xor (password[bounds.second - 1] == char)) 1 else 0
}