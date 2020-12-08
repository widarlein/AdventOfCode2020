package day8

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val program = args.first().lines()

    val jmpIndicies = program.mapIndexed { i, inst ->
        if (inst.startsWith("jmp")) i else -1
    }.filter { it >= 0 }

    jmpIndicies.forEach { index ->
        val inst = program[index]
        val nopInst = inst.replace("jmp", "nop")
        val newProgram = program.toMutableList().also { it[index] = nopInst }

        try {
            runProgram(newProgram)
            exitProcess(0) // A program was allowed to terminate without exception so we're done
        } catch (e: IllegalStateException) {

        }
    }
}

fun runProgram(program: List<String>) {
    runInstructions = mutableListOf()
    accumulator = 0

    var pointer = 0
    while (true) {
        val pointerOffset = runInstruction(pointer, program)
        pointer += pointerOffset
        if (pointer == program.lastIndex + 1) {
            println("Program terminating. Acc is $accumulator")
            break
        } else if (pointer > program.lastIndex + 1) {
            error("Something's iffy")
        }
    }
}

private var runInstructions = mutableListOf<Int>()
private var accumulator = 0

fun runInstruction(index: Int, program: List<String>): Int {
    if (runInstructions.contains(index)) {
        println("Part1: Instruction $index is about to execute again. Acc is $accumulator")
        println("Last operation executed: ${program[runInstructions.last()]} at index " +
                "${runInstructions.last()}")
        error("Excecuting Loop")
    }
    runInstructions.add(index)

    val instructionLine = program[index]
    val (instruction, value) = instructionLine.split(" ")
    when (instruction) {
        "acc" -> accumulator += value.toInt()
        "jmp" -> return value.toInt()
        "nop" -> {}
        else -> error("No supported instruction $instruction")
    }
    return 1
}