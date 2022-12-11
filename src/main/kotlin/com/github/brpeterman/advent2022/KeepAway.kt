package com.github.brpeterman.advent2022

import java.util.LinkedList
import java.util.SortedMap

typealias ArithmeticOperation = (Long, Long) -> Long
typealias MonkeyRule = (Long) -> KeepAway.ItemPass

class KeepAway(input: String, worryDecay: Int) {
    data class Monkey(val holding: LinkedList<Long>, val rule: MonkeyRule, val divisor: Int, var inspectionCount: Int = 0)
    data class ItemPass(val to: Int, val worryLevel: Long)
    enum class Operation(val function: ArithmeticOperation) {
        ADD({ a, b ->  a + b }),
        MULTIPLY({ a, b -> a * b })
    }

    val monkeys = parseInput(input, worryDecay)
    val commonMultiple = calculateCommonMultiple(monkeys.values)

    fun calculateMonkeyBusiness(): Long {
        return monkeys.values.map { it.inspectionCount.toLong() }
            .sorted()
            .reversed()
            .subList(0, 2)
            .reduce { product, count -> product * count }
    }

    fun simulate(rounds: Int) {
        (0 until rounds).forEach {
            monkeys.values
                .forEach { monkey -> takeTurn(monkey) }
        }
    }

    fun takeTurn(monkey: Monkey) {
        while (monkey.holding.isNotEmpty()) {
            val item = monkey.holding.pop()
            val result = monkey.rule.invoke(item)
            monkeys[result.to]!!.holding.add(result.worryLevel)
            monkey.inspectionCount++
        }
    }

    fun parseInput(input: String, worryDecay: Int): SortedMap<Int, Monkey> {
        return input.split("\n\n")
            .withIndex()
            .map { (index, monkeyDef) ->
                val lines = monkeyDef.split("\n")
                val startingItems = parseStartingItems(lines[1])
                val (divisorStr) = """divisible by (\d+)""".toRegex()
                        .find(lines[3])!!
                        .destructured
                val divisor = divisorStr.toInt()
                val rule = parseRule(divisor, lines[2], lines[4], lines[5], worryDecay)
                index to Monkey(startingItems, rule, divisor)
            }
            .toMap()
            .toSortedMap()
    }

    fun parseStartingItems(line: String): LinkedList<Long> {
        return LinkedList(""" (\d+),?""".toRegex().findAll(line)
            .map {
                val (num) = it.destructured
                num.toLong()
            }
            .toList())
    }

    fun calculateCommonMultiple(monkeys: Collection<Monkey>): Long {
        return monkeys.fold(1, { product, m -> product * m.divisor.toLong() })
    }

    fun parseRule(divisor: Int, operationLine: String, trueLine: String, falseLine: String, worryDecay: Int): MonkeyRule {
        val (operator, operandStr) = """new = old ([+*]) (\d+|old)""".toRegex()
            .find(operationLine)!!
            .destructured
        val operatorFunction = when (operator) {
            "+" -> Operation.ADD
            "*" -> Operation.MULTIPLY
            else -> throw IllegalStateException("Unexpected operator: ${operator}")
        }
        val (trueMonkeyStr) = """monkey (\d+)""".toRegex()
            .find(trueLine)!!
            .destructured
        val (falseMonkeyStr) = """monkey (\d+)""".toRegex()
            .find(falseLine)!!
            .destructured

        val trueMonkey = trueMonkeyStr.toInt()
        val falseMonkey = falseMonkeyStr.toInt()
        return constructRuleFunction(operatorFunction, operandStr, divisor, trueMonkey, falseMonkey, worryDecay)
    }

    fun constructRuleFunction(operatorFunction: Operation, operandStr: String, divisor: Int, trueMonkey: Int, falseMonkey: Int, worryDecay: Int): MonkeyRule {
        return { old ->
            val operand = if (operandStr == "old") {
                old
            } else {
                operandStr.toLong()
            }
            var new = operatorFunction.function.invoke(old, operand)
            new = if (worryDecay > 1) {
                new / worryDecay
            } else {
                new % commonMultiple
            }
            if (new % divisor == 0L) {
                ItemPass(trueMonkey, new)
            } else {
                ItemPass(falseMonkey, new)
            }
        }
    }
}
