package com.github.brpeterman.advent2022

import java.util.LinkedList

class HillClimber(input: String) {
    data class HeightMap(val startPos: Coords, val endPos: Coords, val elevations: Map<Coords, Int>)
    data class Coords(val row: Int, val col: Int) {
        operator fun plus(other: Coords): Coords {
            return Coords(row + other.row, col + other.col)
        }
    }

    val map = parseInput(input)

    fun climb(): Int {
        return climbFrom(map.startPos)!!
    }

    fun findBestTrail(): Int {
        return map.elevations.filter { it.value == 1 }
            .map { climbFrom(it.key) }
            .filter { it != null }
            .map { it!! }
            .min()
    }

    fun climbFrom(start: Coords): Int? {
        var current = start
        val toVisit = LinkedList(getNeighbors(current))
        val bestPathLength = mutableMapOf(current to 0)
        toVisit.forEach { bestPathLength[it] = 1 }
        while (toVisit.isNotEmpty()) {
            current = toVisit.pop()
            val pathLength = bestPathLength[current]!!
            val neighbors = getNeighbors(current)
            neighbors.forEach { neighbor ->
                if (bestPathLength[neighbor] == null || bestPathLength[neighbor]!! > pathLength + 1) {
                    bestPathLength[neighbor] = pathLength + 1
                    toVisit.add(neighbor)
                }
            }
        }
        return bestPathLength[map.endPos]
    }

    fun getNeighbors(location: Coords): List<Coords> {
        return listOf(
            Coords(location.row - 1, location.col),
            Coords(location.row + 1, location.col),
            Coords(location.row, location.col - 1),
            Coords(location.row, location.col + 1))
            .filter { neighbor ->
                map.elevations[neighbor] != null && (map.elevations[neighbor]!! - map.elevations[location]!! <= 1)
            }
    }

    companion object {
        fun parseInput(input: String): HeightMap {
            val map = HashMap<Coords, Int>()
            var startPos = Coords(0, 0)
            var endPos = Coords(0, 0)
            input.split("\n")
                .filter { it.isNotBlank() }
                .withIndex()
                .forEach { (row, line) ->
                    line.toCharArray()
                        .withIndex()
                        .forEach { (column, char) ->
                            val elevation = when (char) {
                                'S' -> {
                                    startPos = Coords(row, column)
                                    1
                                }
                                'E' ->  {
                                    endPos = Coords(row, column)
                                    26
                                }
                                else -> char.code - 96
                            }
                            map[Coords(row, column)] = elevation
                        }
                }
            return HeightMap(startPos, endPos, map)
        }
    }
}
