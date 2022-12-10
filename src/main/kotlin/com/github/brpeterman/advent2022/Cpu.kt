package com.github.brpeterman.advent2022

import java.util.LinkedList
import kotlin.math.absoluteValue

class Cpu {
    enum class Operation(val duration: Int) {
        ADDX(2),
        NOOP(1)
    }
    data class Instruction(val op: Operation, val arg: Int? = null)

    var registerX = 1

    fun execute(instructions: LinkedList<Instruction>, plugin: CpuPlugin) {
        var cycle = 1
        var workRemaining = 0
        var executing = Instruction(Operation.NOOP)
        while (instructions.isNotEmpty()) {
            if (workRemaining > 0) {
                workRemaining--
            } else {
                when (executing.op) {
                    Operation.ADDX -> registerX += executing.arg!!
                    else -> {}
                }
                executing = instructions.pop()
                workRemaining = executing.op.duration - 1
            }
            plugin.evalCycle(this, cycle)
            cycle++
        }
    }

    interface CpuPlugin {
        fun evalCycle(cpu: Cpu, cycle: Int)
    }

    class SignalSum : CpuPlugin {
        val keyCycles = setOf(20, 60, 100, 140, 180, 220)
        var totalSignal = 0

        override fun evalCycle(cpu: Cpu, cycle: Int) {
            if (keyCycles.contains(cycle)) {
                totalSignal += cpu.registerX * cycle
            }
        }
    }

    class Crt : CpuPlugin {
        val pixels = mutableListOf<Char>()

        override fun evalCycle(cpu: Cpu, cycle: Int) {
            if ((cpu.registerX - ((cycle - 1) % 40)).absoluteValue <= 1) {
                pixels.add('#')
            } else {
                pixels.add('.')
            }
        }
    }

    companion object {
        fun parseInput(input: String): LinkedList<Instruction> {
            return LinkedList(input.split("\n")
                .filter { it.isNotBlank() }
                .map { line ->
                    val tokens = line.split(" ")
                    when (tokens[0]) {
                        "addx" -> Instruction(Operation.ADDX, tokens[1].toInt())
                        "noop" -> Instruction(Operation.NOOP)
                        else -> throw IllegalStateException("Unexpected operation: ${tokens[0]}")
                    }
                })
        }
    }
}
