package com.github.brpeterman.advent2022

import java.util.*

class CrateCrane {
    data class CrateState(val stacks: MutableMap<Int, LinkedList<Char>>, val moves: List<CrateMove>)
    data class CrateMove(val count: Int, val from: Int, val to: Int)

    fun reportStacks(stacks: List<LinkedList<Char>>): String {
        return stacks.fold("") { output, stack -> output + stack.peek().toString() }
    }

    enum class CraneMode {
        DEFAULT,
        HOLD_MULTIPLE
    }

    fun simulate(state: CrateState, mode: CraneMode = CraneMode.DEFAULT): List<LinkedList<Char>> {
        val stacks = state.stacks
        val moves = state.moves
        moves.forEach { move ->
            val from = stacks[move.from]!!
            val to = stacks[move.to]!!
            val holding = mutableListOf<Char>()
            (1..move.count).forEach {
                holding.add(from.pop())
            }
            if (mode == CraneMode.HOLD_MULTIPLE) {
                holding.reverse()
            }
            holding.forEach { crate ->
                to.push(crate)
            }
        }
        return stacks.entries
            .sortedBy { it.key }
            .map { it.value }
    }

    companion object {
        fun parseInput(input: String): CrateState {
            val (stateInput, movesInput) = input.split("\n\n")
            val stacks = stateInput.split("\n")
                .filter { it.matches(""" *\[.*""".toRegex()) }
                .fold(mutableMapOf<Int, LinkedList<Char>>().withDefault { LinkedList() }) { stacks, line ->
                    line.chunked(4)
                        .withIndex()
                        .forEach { (index, crate) ->
                            val matches = """\[([A-Z])] ?""".toRegex().matchEntire(crate)
                            if (matches != null) {
                                val stack = stacks.getValue(index + 1)
                                val (crateName) = matches.destructured
                                stack.add(crateName[0])
                                stacks[index + 1] = stack
                            }
                        }
                    stacks
                }
            val moves = movesInput.split("\n")
                .filter { it.isNotBlank() }
                .map { line ->
                    val matches = """move (\d+) from (\d+) to (\d+)""".toRegex().matchEntire(line)
                    val (count, from, to) = matches!!.destructured
                    CrateMove(count.toInt(), from.toInt(), to.toInt())
                }
            return CrateState(stacks, moves)
        }
    }
}
