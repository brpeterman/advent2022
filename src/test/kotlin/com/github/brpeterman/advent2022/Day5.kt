package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5 {
    @Test
    fun `example 1`() {
        val input = """
                [D]    
            [N] [C]    
            [Z] [M] [P]
             1   2   3 
            
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
        """.trimIndent()
        val solver = CrateCrane()
        val stacks = solver.simulate(CrateCrane.parseInput(input))
        val output = solver.reportStacks(stacks)

        assertEquals("CMZ", output)
    }

    @Test
    fun `example 2`() {
        val input = """
                [D]    
            [N] [C]    
            [Z] [M] [P]
             1   2   3 
            
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
        """.trimIndent()
        val solver = CrateCrane()
        val stacks = solver.simulate(CrateCrane.parseInput(input), CrateCrane.CraneMode.HOLD_MULTIPLE)
        val output = solver.reportStacks(stacks)

        assertEquals("MCD", output)
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day5.txt").readText()
        val solver = CrateCrane()
        val stacks = solver.simulate(CrateCrane.parseInput(input))
        val output = solver.reportStacks(stacks)

        assertEquals("WHTLRMZRC", output)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day5.txt").readText()
        val solver = CrateCrane()
        val stacks = solver.simulate(CrateCrane.parseInput(input), CrateCrane.CraneMode.HOLD_MULTIPLE)
        val output = solver.reportStacks(stacks)

        assertEquals("GMPMLWNMG", output)
    }
}
