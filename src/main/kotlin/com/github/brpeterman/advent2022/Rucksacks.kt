package com.github.brpeterman.advent2022

class Rucksacks {
    fun scoreMisfits(rucksacks: List<Pair<Set<String>, Set<String>>>): Int {
        return rucksacks.fold(0) { total, sack ->
            val misfit = sack.first.intersect(sack.second).first()
            total + score(misfit)
        }
    }

    fun scoreBadges(rucksacks: List<Pair<Set<String>, Set<String>>>): Int {
        return rucksacks.chunked(3)
            .fold(0) { total, group ->
                val badge = unify(group[0])
                    .intersect(unify(group[1]))
                    .intersect(unify(group[2]))
                    .first()
                total + score(badge)
            }
    }

    fun unify(rucksack: Pair<Set<String>, Set<String>>): Set<String> {
        return rucksack.first + rucksack.second
    }

    fun score(item: String): Int {
        if (item.matches("[a-z]".toRegex())) {
            return item[0].code - 96
        } else if (item.matches("[A-Z]".toRegex())) {
            return item[0].code - 38
        }
        return 0
    }

    companion object {
        fun parseInput(input: String): List<Pair<Set<String>, Set<String>>> {
            return input.split("\n")
                .filter { it.isNotBlank() }
                .map { line ->
                    val left = line.substring(0, (line.length/2))
                    val right = line.substring(line.length/2)
                    Pair(
                        left.split("").filter {it.isNotBlank()}.toSet(),
                        right.split("").filter {it.isNotBlank()}.toSet())
                }
        }
    }
}
