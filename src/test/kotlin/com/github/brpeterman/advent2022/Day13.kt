package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13 {
    val TEST_INPUT = """
        [1,1,3,1,1]
        [1,1,5,1,1]

        [[1],[2,3,4]]
        [[1],4]

        [9]
        [[8,7,6]]

        [[4,4],4,4]
        [[4,4],4,4,4]

        [7,7,7,7]
        [7,7,7]

        []
        [3]

        [[[]]]
        [[]]

        [1,[2,[3,[4,[5,6,7]]]],8,9]
        [1,[2,[3,[4,[5,6,0]]]],8,9]
    """.trimIndent()

    @Test
    fun `example 1`() {
        val solver = Distress(TEST_INPUT)
        val sum = solver.countInOrder()

        assertEquals(13, sum)
    }

    @Test
    fun `example 2`() {
        val solver = Distress(TEST_INPUT)
        val key = solver.calculateDecoderKey()

        assertEquals(140, key)
    }

    @Test
    fun `part 1`() {
        val solver = Distress(File("inputs/day13.txt").readText())
        val sum = solver.countInOrder()

        assertEquals(5198, sum)
    }

    @Test
    fun `part 2`() {
        val solver = Distress(File("inputs/day13.txt").readText())
        val key = solver.calculateDecoderKey()

        assertEquals(22344, key)
    }
}
