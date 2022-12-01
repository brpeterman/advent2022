package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day1 {
    @Test
    fun `example 1`() {
        val input = """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
        """.trimIndent()
        val solver = MealPlanner()
        val mostCalories = solver.findMaxCalories(MealPlanner.parseInput(input), 1)[0]

        assertEquals(24000, mostCalories)
    }

    @Test
    fun `example 2`() {
        val input = """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
        """.trimIndent()
        val solver = MealPlanner()
        val topThree = solver.findMaxCalories(MealPlanner.parseInput(input), 3)

        assertEquals(45000, topThree.sum())
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day1.txt").readText()
        val solver = MealPlanner()
        val mostCalories = solver.findMaxCalories(MealPlanner.parseInput(input), 1)[0]

        assertEquals(72602, mostCalories)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day1.txt").readText()
        val solver = MealPlanner()
        val topThree = solver.findMaxCalories(MealPlanner.parseInput(input), 3)

        assertEquals(207410, topThree.sum())
    }
}
