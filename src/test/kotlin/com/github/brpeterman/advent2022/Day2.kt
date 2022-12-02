package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day2 {
    @Test
    fun `example 1`() {
        val input = """
            A Y
            B X
            C Z
        """.trimIndent()
        val solver = RpsSim()
        val score = solver.simulateAndScore(RpsSim.parseInput(input))

        assertEquals(15, score)
    }

    @Test
    fun `example 2`() {
        val input = """
            A Y
            B X
            C Z
        """.trimIndent()
        val solver = RpsSim()
        val score = solver.strategizeAndScore(RpsSim.parseInput(input))

        assertEquals(12, score)
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day2.txt").readText()
        val solver = RpsSim()
        val score = solver.simulateAndScore(RpsSim.parseInput(input))

        assertEquals(9177, score)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day2.txt").readText()
        val solver = RpsSim()
        val score = solver.strategizeAndScore(RpsSim.parseInput(input))

        assertEquals(12111, score)
    }
}
