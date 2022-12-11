package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11 {
    val TEST_INPUT = """
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3
        
        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0
        
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3
        
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
    """.trimIndent()

    @Test
    fun `example 1`() {
        val solver = KeepAway(TEST_INPUT, 3)
        solver.simulate(20)
        val level = solver.calculateMonkeyBusiness()

        assertEquals(10605, level)
    }

    @Test
    fun `example 2`() {
        val solver = KeepAway(TEST_INPUT, 1)
        solver.simulate(10000)
        val level = solver.calculateMonkeyBusiness()

        assertEquals(2713310158, level)
    }

    @Test
    fun `part 1`() {
        val solver = KeepAway(File("inputs/day11.txt").readText(), 3)
        solver.simulate(20)
        val level = solver.calculateMonkeyBusiness()

        assertEquals(113232, level)
    }

    @Test
    fun `part 2`() {
        val solver = KeepAway(File("inputs/day11.txt").readText(), 1)
        solver.simulate(10000)
        val level = solver.calculateMonkeyBusiness()

        assertEquals(29703395016, level)
    }
}
