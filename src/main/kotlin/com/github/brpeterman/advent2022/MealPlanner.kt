package com.github.brpeterman.advent2022

class MealPlanner {
    data class MealPack(val items: List<Int>)

    fun findMaxCalories(elves: List<MealPack>, count: Int): List<Int> {
        return elves.map { it.items.sum() }
            .sorted()
            .subList(0, count)
    }

    companion object {
        fun parseInput(input: String): List<MealPack> {
            val elves = mutableListOf<MealPack>()
            var meals = mutableListOf<Int>()
            input.split("\n")
                .forEach { line ->
                    if (line.isNotEmpty()) {
                        meals.add(line.toInt())
                    } else {
                        elves.add(MealPack(meals))
                        meals = mutableListOf()
                    }
                }
            if (meals.isNotEmpty()) {
                elves.add(MealPack(meals))
            }
            return elves
        }
    }
}
