package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day4 {
    @Test
    fun `example 1`() {
        val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent()
        val solver = CampSections()
        val count = solver.countRedundancies(CampSections.parseInput(input))

        assertEquals(2, count)
    }

    @Test
    fun `example 2`() {
        val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent()
        val solver = CampSections()
        val count = solver.countOverlaps(CampSections.parseInput(input))

        assertEquals(4, count)
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day4.txt").readText()
        val solver = CampSections()
        val count = solver.countRedundancies(CampSections.parseInput(input))

        assertEquals(534, count)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day4.txt").readText()
        val solver = CampSections()
        val count = solver.countOverlaps(CampSections.parseInput(input))

        assertEquals(841, count)
    }
}
