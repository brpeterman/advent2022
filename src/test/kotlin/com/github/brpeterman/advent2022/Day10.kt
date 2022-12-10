package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10 {
    val TEST_INPUT = """
            addx 15
            addx -11
            addx 6
            addx -3
            addx 5
            addx -1
            addx -8
            addx 13
            addx 4
            noop
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx -35
            addx 1
            addx 24
            addx -19
            addx 1
            addx 16
            addx -11
            noop
            noop
            addx 21
            addx -15
            noop
            noop
            addx -3
            addx 9
            addx 1
            addx -3
            addx 8
            addx 1
            addx 5
            noop
            noop
            noop
            noop
            noop
            addx -36
            noop
            addx 1
            addx 7
            noop
            noop
            noop
            addx 2
            addx 6
            noop
            noop
            noop
            noop
            noop
            addx 1
            noop
            noop
            addx 7
            addx 1
            noop
            addx -13
            addx 13
            addx 7
            noop
            addx 1
            addx -33
            noop
            noop
            noop
            addx 2
            noop
            noop
            noop
            addx 8
            noop
            addx -1
            addx 2
            addx 1
            noop
            addx 17
            addx -9
            addx 1
            addx 1
            addx -3
            addx 11
            noop
            noop
            addx 1
            noop
            addx 1
            noop
            noop
            addx -13
            addx -19
            addx 1
            addx 3
            addx 26
            addx -30
            addx 12
            addx -1
            addx 3
            addx 1
            noop
            noop
            noop
            addx -9
            addx 18
            addx 1
            addx 2
            noop
            noop
            addx 9
            noop
            noop
            noop
            addx -1
            addx 2
            addx -37
            addx 1
            addx 3
            noop
            addx 15
            addx -21
            addx 22
            addx -6
            addx 1
            noop
            addx 2
            addx 1
            noop
            addx -10
            noop
            noop
            addx 20
            addx 1
            addx 2
            addx 2
            addx -6
            addx -11
            noop
            noop
            noop
        """.trimIndent()

    @Test
    fun `example 1`() {
        val cpu = Cpu()
        val instructions = Cpu.parseInput(TEST_INPUT)
        val plugin = Cpu.SignalSum()
        cpu.execute(instructions, plugin)

        assertEquals(13140, plugin.totalSignal)
    }

    @Test
    fun `example 2`() {
        val cpu = Cpu()
        val instructions = Cpu.parseInput(TEST_INPUT)
        val plugin = Cpu.Crt()
        cpu.execute(instructions, plugin)

        plugin.pixels.chunked(40).forEach { println(String(it.toCharArray())) }
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day10.txt").readText()
        val cpu = Cpu()
        val instructions = Cpu.parseInput(input)
        val plugin = Cpu.SignalSum()
        cpu.execute(instructions, plugin)

        assertEquals(11820, plugin.totalSignal)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day10.txt").readText()
        val cpu = Cpu()
        val instructions = Cpu.parseInput(input)
        val plugin = Cpu.Crt()
        cpu.execute(instructions, plugin)

        plugin.pixels.chunked(40).forEach { println(String(it.toCharArray())) }
    }
}
