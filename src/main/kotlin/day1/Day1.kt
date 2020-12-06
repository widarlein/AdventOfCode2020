package day1

fun main(arrrrrghs: Array<String>) {
    val entries = arrrrrghs.first().lineSequence().map { it.toInt() }.toList()
    entries.forEach { outer ->
        (entries - outer).forEach { inner ->
            if (outer + inner == 2020) {
                println("$outer * $inner = ${outer * inner}")
            }
        }
    }

    entries.forEach { outer ->
        (entries - outer).forEach { inner ->
            (entries - outer - inner).forEach {
                if (outer + inner + it == 2020) {
                    println("$outer * $inner * $it = ${outer * inner * it}")
                }
            }
        }
    }
}