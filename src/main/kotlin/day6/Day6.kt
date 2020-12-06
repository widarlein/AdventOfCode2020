package day6

fun main(args: Array<String>) {

    val groups = parseGroups(args.first())

    val yesesPerGroup = groups.map { group ->
        group.lines().flatMap { it.toList() }.toSet().size
    }

    println("Part1: Sum of yes'd questions per group: ${yesesPerGroup.sum()}")

    val exclusiveYessedPergroup = groups.map { group ->
        group.lines().fold(group.lines().first().toSet()) { questions, person ->
            questions.intersect(person.toSet())
        }.size
    }

    println("Part2: Sum of exclusive yes'd questions per group: ${exclusiveYessedPergroup.sum()}")
}

fun parseGroups(input: String): List<String> {
    val groups = input.split("\n\n")
    return groups
}