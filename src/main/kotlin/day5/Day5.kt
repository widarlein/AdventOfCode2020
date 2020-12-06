package day5

fun main(args: Array<String>) {
    val seatIds = args.first().lines().map { it.seatId() }
    val highestSeatId = seatIds.maxOrNull()
    println("Part1: highest seat ID: $highestSeatId")

    val seating = Array<BooleanArray>(128) {
        BooleanArray(8)
    }

    args.first().lines().map { it.toRow() to it.toColumn() }.forEach {
        val (row, column) = it
        seating[row][column] = true
    }

    val emptySeats = mutableListOf<Pair<Int, Int>>()
    seating.forEachIndexed { row, columns ->
        columns.forEachIndexed { column, seat ->
            if (!seat) {
                emptySeats.add(row to column)
            }
        }
    }

    val lonelySeats = emptySeats.filter {
        val (row, column) = it
        val seatId = row * 8 + column
        seatIds.contains(seatId - 1) && seatIds.contains(seatId + 1)
    }.map {
        it.first * 8 + it.second
    }

    println("Part2: List of lonely seats: $lonelySeats")
}

fun String.toRow(): Int {
    return this.take(7).fold(0 to 127) { rows, char ->
        val (low, high) = rows
        val half = (high - low) / 2
        when (char) {
            'F' -> low to low + half
            'B' -> low + half + 1 to high
            else -> error("$char is not F or B")
        }
    }.first
}

fun String.toColumn(): Int {
    return this.takeLast(3).fold(0 to 7) { columns, char ->
        val (low, high) = columns
        val half = (high - low) / 2
        when (char) {
            'L' -> low to low + half
            'R' -> low + half + 1 to high
            else -> error("$char is not F or B")
        }
    }.first
}

fun String.seatId() = toRow() * 8 + toColumn()