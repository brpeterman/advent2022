package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day8 {
    @Test
    fun `example 1`() {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent()
        val survey = ForestSurvey(input)
        val count = survey.countVisible()

        assertEquals(21, count)
    }

    @Test
    fun `example 2`() {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent()
        val survey = ForestSurvey(input)
        val score = survey.findBestTree()

        assertEquals(8, score)
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day8.txt").readText()
        val survey = ForestSurvey(input)
        val count = survey.countVisible()

        assertEquals(1693, count)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day8.txt").readText()
        val survey = ForestSurvey(input)
        val count = survey.findBestTree()

        assertEquals(422059, count)
    }
}
