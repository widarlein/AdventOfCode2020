package day7

fun main(args: Array<String>) {
    val rules = parseInput(args.first())
    val containers = findContainers("shiny gold", rules)

    println("Part1: Number of possible containers: ${containers.size}")

    val content = countBagsWorth("shiny gold", rules) - 1
    println("Part2: Number of bags inside: $content")
}

fun findContainers(bagType: String, rules: Map<String, List<ContainEntry>> ): Set<String> {
    val containers: Set<String> = rules.filter {
        it.value.any { it.type == bagType }
    }.keys
    return containers + containers.flatMap { findContainers(it, rules) }
}

fun countBagsWorth(bagType: String, rules: Map<String, List<ContainEntry>>): Int {
    val contains = checkNotNull(rules[bagType])
    return contains.sumBy { it.count *  countBagsWorth(it.type, rules)} + 1
}

fun parseInput(input: String): Map<String, List<ContainEntry>> {
    return input.lines().map { line ->
        val match = checkNotNull("""(\w+ \w+) bags contain (.+)""".toRegex().matchEntire(line))
        val bagType = match.groupValues[1]

        val containsText = match.groupValues[2]
        val containEntries = containsText.split(", ").map { contain ->
            val match = """(\d) (\w+ \w+) bags?\.?""".toRegex().matchEntire(contain)
            if (match != null) {
                val count = match.groupValues[1]
                val bagType = match.groupValues[2]
                ContainEntry(bagType, count.toInt())
            } else null
        }.filterNotNull()

        bagType to containEntries
    }.toMap()
}

data class ContainEntry(val type: String, val count: Int)