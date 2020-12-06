package day4

import java.lang.Exception

fun main(args: Array<String>) {
    val passports = parsePassports(args.first())
    val validCount = passports.count { it.isValid() }

    println("Part1: There are $validCount valid passports")

    val validCountPart2 = passports.count { it.isValidPart2() }
    println("Part2: There are $validCountPart2 valid passports")
}

fun parsePassports(input: String): List<Passport> {
    val passportsStrings = input.split("\n\n")
    return passportsStrings.map { passport ->

        val fields = passport.split("""\s""".toRegex())
        val fieldMap = fields.map {
            val (key, value) = it.split(":")
            key to value
        }.toMap()
        Passport(
                fieldMap["byr"],
                fieldMap["iyr"],
                fieldMap["eyr"],
                fieldMap["hgt"],
                fieldMap["hcl"],
                fieldMap["ecl"],
                fieldMap["pid"],
                fieldMap["cid"],
        )
    }
}

data class Passport(val byr: String? = null,
                    val iyr: String? = null,
                    val eyr: String? = null,
                    val hgt: String? = null,
                    val hcl: String? = null,
                    val ecl: String? = null,
                    val pid: String? = null,
                    val cid: String? = null) {
    fun isValid() = byr != null &&
            iyr != null &&
            eyr != null &&
            hgt != null &&
            hcl != null &&
            ecl != null &&
            pid != null

    fun isValidPart2(): Boolean = isValid() &&
            byr!!.isbyrValid() &&
            iyr!!.isiyrValid() &&
            eyr!!.iseyrValid() &&
            hgt!!.ishgtValid() &&
            hcl!!.ishclValid() &&
            ecl!!.iseclValid() &&
            pid!!.ispidValid()

}

fun String.isbyrValid() = falseOnException { this.toInt() in 1920..2002 }
fun String.isiyrValid() = falseOnException { this.toInt() in 2010..2020 }
fun String.iseyrValid() = falseOnException { this.toInt() in 2020..2030 }
fun String.ishgtValid() = falseOnException {
    val match = """(\d+)(cm|in)""".toRegex().matchEntire(this)
    if (match != null) {
        when (match.groupValues[2]) {
            "cm" -> match.groupValues[1].toInt() in 150..193
            "in" -> match.groupValues[1].toInt() in 59..76
            else -> false
        }
    } else false
}
fun String.ishclValid() = """#[a-f0-9]{6}""".toRegex().matchEntire(this) != null
fun String.iseclValid() = this in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth",)
fun String.ispidValid() = this.length == 9 && falseOnException { this.toInt() < Int.MAX_VALUE }

fun falseOnException(block: () -> Boolean) = try {
    block()
} catch (e: Exception) {
    false
}