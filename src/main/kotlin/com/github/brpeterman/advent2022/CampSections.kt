package com.github.brpeterman.advent2022

class CampSections {
    fun countOverlaps(assignments: List<Pair<IntRange, IntRange>>): Int {
        return assignments.fold(0, { total, assignment ->
            if ((assignment.second.contains(assignment.first.first)) ||
                (assignment.second.contains(assignment.first.endInclusive))) {
                total + 1
            } else {
                total
            }
        })
    }

    fun countRedundancies(assignments: List<Pair<IntRange, IntRange>>): Int {
        return assignments.fold(0, { total, assignment ->
            if ((assignment.second.contains(assignment.first.start)) &&
                (assignment.second.contains(assignment.first.endInclusive))) {
                total + 1
            } else {
                total
            }
        })
    }

    companion object {
        fun parseInput(input: String): List<Pair<IntRange, IntRange>> {
            return input.split("\n")
                .filter { it.isNotBlank() }
                .map { line ->
                    val ranges = line.split(",")
                        .map { section ->
                            val (start, end) = section.split("-")
                            start.toInt()..end.toInt()
                        }
                        .sortedBy { it.endInclusive - it.start }
                    Pair(ranges[0], ranges[1])
                }
        }
    }
}
