package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day6 {
    @Test
    fun `example 1`() {
        val input = """
            mjqjpqmgbljsphdztnvjfqwrcgsmlb
        """.trimIndent()
        val solver = CommsDevice()
        val sopMarker = solver.findStart(CommsDevice.parseInput(input), 4)

        assertEquals(7, sopMarker)
    }

    @Test
    fun `example 2`() {
        val input = """
            mjqjpqmgbljsphdztnvjfqwrcgsmlb
        """.trimIndent()
        val solver = CommsDevice()
        val sopMarker = solver.findStart(CommsDevice.parseInput(input), 14)

        assertEquals(19, sopMarker)
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day6.txt").readText()
        val solver = CommsDevice()
        val sopMarker = solver.findStart(CommsDevice.parseInput(input), 4)

        assertEquals(1848, sopMarker)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day6.txt").readText()
        val solver = CommsDevice()
        val sopMarker = solver.findStart(CommsDevice.parseInput(input), 14)

        assertEquals(2308, sopMarker)
    }
}
