package com.github.brpeterman.advent2022

class RpsSim {
    fun simulateAndScore(plays: List<Pair<String, String>>): Int {
        return plays.fold(0, { total, play ->
            total + scorePlay(play.first, toPlay(play.second))
        })
    }

    fun strategizeAndScore(plays: List<Pair<String, String>>): Int {
        return plays.fold(0, {total, play ->
            total + scorePlay(play.first, choosePlay(play.first, play.second))
        })
    }

    fun toPlay(play: String): String {
        return when (play) {
            ME_ROCK -> ROCK
            ME_PAPER -> PAPER
            ME_SCISSORS -> SCISSORS
            else -> throw IllegalStateException("Unexpected play: ${play}")
        }
    }

    fun choosePlay(opponent: String, outcome: String): String {
        if (outcome == WIN) {
            return when (opponent) {
                ROCK -> PAPER
                PAPER -> SCISSORS
                SCISSORS -> ROCK
                else -> throw IllegalStateException("Unexpected move: ${opponent}")
            }
        }
        if (outcome == LOSE) {
            return when (opponent) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                SCISSORS -> PAPER
                else -> throw IllegalStateException("Unexpected move: ${opponent}")
            }
        }
        return opponent
    }

    fun scorePlay(opponent: String, me: String): Int {
        var score = SCORES[me]!!
        if (opponent == me) {
            score += 3
        } else if ((opponent == ROCK && me == PAPER) ||
            (opponent == PAPER && me == SCISSORS) ||
            (opponent == SCISSORS && me == ROCK)) {
            score += 6
        }
        return score
    }

    companion object {
        // I had these in nice typesafe enums and then part 2 showed up 😐
        val ROCK = "A"
        val PAPER = "B"
        val SCISSORS = "C"
        val ME_ROCK = "X"
        val ME_PAPER = "Y"
        val ME_SCISSORS = "Z"
        val LOSE = "X"
        val WIN = "Z"
        val SCORES = mapOf(
            Pair(ROCK, 1),
            Pair(PAPER, 2),
            Pair(SCISSORS, 3))

        fun parseInput(input: String): List<Pair<String, String>> {
            return input.split("\n")
                .filter { it.isNotBlank() }
                .map { line ->
                    val (left, right) = line.split(" ")
                    Pair(left, right)
                }
        }
    }
}
