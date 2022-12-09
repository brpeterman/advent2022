package com.github.brpeterman.advent2022

class ForestSurvey(input: String) {
    data class Coords(val row: Int, val column: Int)
    data class TreeMap(val width: Int, val height: Int, val trees: Map<Coords, Int>)
    enum class Offset(val rowOffset: Int, val colOffset: Int) {
        NORTH(-1, 0),
        SOUTH(1, 0),
        WEST(0, -1),
        EAST(0, 1)
    }

    var map: TreeMap

    init {
        map = parseInput(input)
    }

    fun countVisible(): Int {
        return map.trees.keys
            .filter { isVisible(it) }
            .count()
    }

    fun findBestTree(): Int {
        return map.trees.keys
            .filter { it.row != 0 &&
                    it.row != map.height-1 &&
                    it.column != 0 &&
                    it.column != map.width-1 }
            .maxOf { scenicScore(it) }
    }

    fun isVisible(coords: Coords): Boolean {
        val height = map.trees[coords]!!
        return ((0..coords.row-1).all { map.trees[Coords(it, coords.column)]!! < height }) ||
                (coords.row+1..map.height-1).all { map.trees[Coords(it, coords.column)]!! < height } ||
                (0..coords.column-1).all { map.trees[Coords(coords.row, it)]!! < height } ||
                (coords.column+1..map.width-1).all { map.trees[Coords(coords.row, it)]!! < height }
    }

    fun scenicScore(coords: Coords): Int {
        val height = map.trees[coords]!!
        val score = Offset.values()
            .map { offset ->
                var current = Coords(coords.row + offset.rowOffset, coords.column + offset.colOffset)
                var count = 1
                while (map.trees[current] != null && map.trees[current]!! < height) {
                    current = Coords(current.row + offset.rowOffset, current.column + offset.colOffset)
                    if (map.trees[current] != null) {
                        count++
                    }
                }
                count
            }
            .reduce { product, i -> product * i }
        return score
    }

    companion object {
        fun parseInput(input: String): TreeMap {
            val lines = input.split("\n")
                .filter { it.isNotBlank() }
            val height = lines.size
            val width = lines[0].length
            val trees = lines.withIndex()
                .fold(mutableMapOf<Coords, Int>()) { map, (lineIndex, line) ->
                    line.toCharArray()
                        .withIndex()
                        .forEach { (charIndex, char) ->
                            map[Coords(lineIndex, charIndex)] = char.toString().toInt()
                        }
                    map
                }
            return TreeMap(width, height, trees)
        }
    }
}
