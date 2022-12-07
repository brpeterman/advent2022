package com.github.brpeterman.advent2022

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day7 {
    @Test
    fun `example 1`() {
        val input = """
            ${'$'} cd /
            ${'$'} ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            ${'$'} cd a
            ${'$'} ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            ${'$'} cd e
            ${'$'} ls
            584 i
            ${'$'} cd ..
            ${'$'} cd ..
            ${'$'} cd d
            ${'$'} ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
            
        """.trimIndent()
        val solver = DirTree(input)
        val count = solver.sumSmallDirs()

        assertEquals(95437, count)
    }

    @Test
    fun `example 2`() {
        val input = """
            ${'$'} cd /
            ${'$'} ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            ${'$'} cd a
            ${'$'} ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            ${'$'} cd e
            ${'$'} ls
            584 i
            ${'$'} cd ..
            ${'$'} cd ..
            ${'$'} cd d
            ${'$'} ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
            
        """.trimIndent()
        val solver = DirTree(input)
        val count = solver.findThresholdDir()

        assertEquals(24933642, count)
    }

    @Test
    fun `part 1`() {
        val input = File("inputs/day7.txt").readText()
        val solver = DirTree(input)
        val count = solver.sumSmallDirs()

        assertEquals(1390824, count)
    }

    @Test
    fun `part 2`() {
        val input = File("inputs/day7.txt").readText()
        val solver = DirTree(input)
        val count = solver.findThresholdDir()

        assertEquals(7490863, count)
    }
}
