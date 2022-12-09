package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day9 {
    @Test
    fun `example 1`() {
        val input = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent()
        val solver = RopeSnake()
        val count = solver.simulateAndCount(RopeSnake.parseInput(input))

        assertEquals(13, count)
    }

    @Test
    fun `example 2`() {
        val input = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent()
        val solver = RopeSnake()
        val count = solver.simulateAndCount(RopeSnake.parseInput(input), 9)

        assertEquals(1, count)
    }

    @Test
    fun `example 3`() {
        val input = """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
        """.trimIndent()
        val solver = RopeSnake()
        val count = solver.simulateAndCount(RopeSnake.parseInput(input), 9)

        assertEquals(36, count)
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day9.txt").readText()
        val solver = RopeSnake()
        val count = solver.simulateAndCount(RopeSnake.parseInput(input))

        assertEquals(6081, count)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day9.txt").readText()
        val solver = RopeSnake()
        val count = solver.simulateAndCount(RopeSnake.parseInput(input), 9)

        assertEquals(2487, count)
    }
}
