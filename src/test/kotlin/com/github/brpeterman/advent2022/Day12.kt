package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12 {
    val TEST_INPUT = """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
    """.trimIndent()

    @Test
    fun `example 1`() {
        val solver = HillClimber(TEST_INPUT)
        val count = solver.climb()

        assertEquals(31, count)
    }

    @Test
    fun `example 2`() {
        val solver = HillClimber(TEST_INPUT)
        val count = solver.findBestTrail()

        assertEquals(29, count)
    }

    @Test
    fun `part 1`() {
        val solver = HillClimber(File("inputs/day12.txt").readText())
        val count = solver.climb()

        assertEquals(394, count)
    }

    @Test
    fun `part 2`() {
        val solver = HillClimber(File("inputs/day12.txt").readText())
        val count = solver.findBestTrail()

        assertEquals(388, count)
    }
}
