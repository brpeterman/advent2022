package com.github.brpeterman.advent2022

import java.util.*

class Distress(input: String) {
    data class PacketDatum(val value: Int? = null, var subPacket: MutableList<PacketDatum>? = null): Comparable<PacketDatum> {
        override fun compareTo(other: PacketDatum): Int {
            if (value != null && other.value != null) {
                return value.compareTo(other.value)
            }
            if (value != null && other.subPacket != null) {
                return PacketDatum(null, mutableListOf(this)).compareTo(other)
            }
            if (subPacket != null && other.value != null) {
                return this.compareTo(PacketDatum(null, mutableListOf(other)))
            }
            // No bare ints. Compare lists.
            subPacket!!.withIndex()
                .forEach { (index, leftItem) ->
                    val rightItem = other.subPacket!!.getOrNull(index)
                    if (rightItem == null) {
                        return 1
                    }
                    val comparison = leftItem.compareTo(other.subPacket!![index])
                    if (comparison != 0) {
                        return comparison
                    }
                }
            if (subPacket!!.size < other.subPacket!!.size) {
                return -1
            }
            return 0
        }
    }

    val packets = parseInput(input)

    fun countInOrder(): Int {
        return packets.chunked(2)
            .withIndex()
            .filter { (_, pair) -> pair[0].compareTo(pair[1]) < 0 }
            .sumOf { (index) -> index + 1 }
    }

    fun calculateDecoderKey(): Int {
        val dividers = setOf(
            PacketDatum(null, mutableListOf(PacketDatum(2))),
            PacketDatum(null, mutableListOf(PacketDatum(6))))
        val allPackets = packets + dividers
        return allPackets.sorted()
            .withIndex()
            .filter { dividers.contains(it.value) }
            .fold(1) { product, (index) -> product * (index + 1) }
    }

    companion object {
        fun parseInput(input: String): List<PacketDatum> {
            return input.split("\n")
                .filter { it.isNotBlank() }
                .map { parsePacket(it) }
        }

        fun parsePacket(input: String): PacketDatum {
            val rootPacket = PacketDatum(null, mutableListOf())
            val charStack = LinkedList<Char>()
            val datumStack = LinkedList(listOf(rootPacket))
            input.substring(1 until input.length-1)
                .toCharArray()
                .forEach { char ->
                    when (char) {
                        '[' -> datumStack.push(PacketDatum(null, mutableListOf()))
                        ']' -> {
                            handleNumberDelimiter(charStack, datumStack.peek())
                            val completedDatum = datumStack.pop()
                            val parent = datumStack.peek()
                            parent.subPacket!!.add(completedDatum)
                        }
                        ',' -> handleNumberDelimiter(charStack, datumStack.peek())
                        else -> charStack.push(char)
                    }
                }
            // Consume any remaining numbers
            handleNumberDelimiter(charStack, rootPacket)
            return rootPacket
        }

        fun handleNumberDelimiter(charStack: LinkedList<Char>, currentDatum: PacketDatum) {
            if (charStack.isEmpty()) {
                return
            }
            var numStr = ""
            while (charStack.isNotEmpty() && charStack.peek().isDigit()) {
                numStr = charStack.pop().toString() + numStr
            }
            currentDatum.subPacket!!.add(PacketDatum(numStr.toInt()))
        }
    }
}
