package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3 {
    @Test
    fun `example 1`() {
        val input = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()
        val solver = Rucksacks()
        val score = solver.scoreMisfits(Rucksacks.parseInput(input))

        assertEquals(157, score)
    }

    @Test
    fun `example 2`() {
        val input = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()
        val solver = Rucksacks()
        val score = solver.scoreBadges(Rucksacks.parseInput(input))

        assertEquals(70, score)
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day3.txt").readText()
        val solver = Rucksacks()
        val score = solver.scoreMisfits(Rucksacks.parseInput(input))

        assertEquals(7908, score)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day3.txt").readText()
        val solver = Rucksacks()
        val score = solver.scoreBadges(Rucksacks.parseInput(input))

        assertEquals(2838, score)
    }
}
