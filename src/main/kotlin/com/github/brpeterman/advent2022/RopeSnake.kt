package com.github.brpeterman.advent2022

class RopeSnake {
    data class Coords(val row: Int, val column: Int) {
        operator fun plus(other: Coords): Coords {
            return Coords(row + other.row, column + other.column)
        }
    }
    enum class Direction(val offset: Coords) {
        UP(Coords(-1, 0)),
        DOWN(Coords(1, 0)),
        LEFT(Coords(0, -1)),
        RIGHT(Coords(0, 1))
    }

    fun simulateAndCount(steps: List<Pair<Direction, Int>>, tailCount: Int = 1): Int {
        val visited = mutableSetOf(Coords(0, 0))
        var head = Coords(0, 0)
        val tails = (1..tailCount).fold(ArrayList<Coords>()) { list, _ ->
            list.add(Coords(0, 0))
            list
        }
        steps.forEach { (direction, count) ->
            (1..count).forEach {
                head = head + direction.offset
                tails.withIndex().forEach { (index, tail) ->
                    val leader = if (index == 0) {
                        head
                    } else {
                        tails[index - 1]
                    }
                    if (!isAdjacent(tail, leader)) {
                        val newTail = moveTo(tail, leader)
                        tails[index] = newTail
                        if (index == tailCount - 1) {
                            visited.add(newTail)
                        }
                    }
                }
            }
        }
        return visited.size
    }

    fun isAdjacent(tail: Coords, head: Coords): Boolean {
        return listOf(Coords(0, 0),
            Direction.UP.offset, Direction.DOWN.offset,
            Direction.LEFT.offset, Direction.RIGHT.offset,
            Direction.UP.offset + Direction.LEFT.offset,
            Direction.UP.offset + Direction.RIGHT.offset,
            Direction.DOWN.offset + Direction.LEFT.offset,
            Direction.DOWN.offset + Direction.RIGHT.offset)
            .any { head + it == tail }
    }

    fun moveTo(tail: Coords, head: Coords): Coords {
        val rowOffset = if (tail.row == head.row) {
            0
        } else {
            (head.row - tail.row) / Math.abs(head.row - tail.row)
        }
        val colOffset = if (tail.column == head.column) {
            0
        } else {
            (head.column - tail.column) / Math.abs(head.column - tail.column)
        }
        return Coords(tail.row + rowOffset, tail.column + colOffset)
    }

    companion object {
        fun parseInput(input: String): List<Pair<Direction, Int>> {
            return input.split("\n")
                .filter { it.isNotBlank() }
                .map { line ->
                    val (dir, count) = line.split(" ")
                    val direction = when (dir) {
                        "U" -> Direction.UP
                        "D" -> Direction.DOWN
                        "L" -> Direction.LEFT
                        "R" -> Direction.RIGHT
                        else -> throw IllegalStateException("Unexpected direction: ${dir}")
                    }
                    Pair(direction, count.toInt())
                }
        }
    }
}
