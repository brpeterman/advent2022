package com.github.brpeterman.advent2022

class CommsDevice {
    fun findStart(charStream: List<String>, windowSize: Int): Int {
        return charStream.windowed(windowSize)
            .indexOfFirst { window ->
                HashSet(window).size == windowSize
            } + windowSize
    }

    companion object {
        fun parseInput(input: String): List<String> {
            return input.split("")
                .filter { it.isNotEmpty() }
        }
    }
}
