package com.github.brpeterman.advent2022

class DirTree(input: String, val root: Folder = Folder("")) {
    init {
        constructTree(parseInput(input))
    }

    data class Folder(val name: String, val parent: Folder? = null, val children: MutableMap<String, Folder> = mutableMapOf(), val files: MutableMap<String, Int> = mutableMapOf())

    fun sumSmallDirs(threshold: Int = 100000): Int {
        val index = mutableMapOf<String, Int>()
        dirSize(root, index)
        return index.values
            .filter { it <= threshold }
            .sum()
    }

    fun findThresholdDir(totalSpace: Int = 70000000, requiredSpace: Int = 30000000): Int {
        val index = mutableMapOf<String, Int>()
        dirSize(root, index)
        val usedSpace = index[""]!!
        return index.values.sorted()
            .first { totalSpace - usedSpace + it >= requiredSpace }
    }

    fun dirSize(dir: Folder, index: MutableMap<String, Int> = mutableMapOf()): Int {
        val count = dir.files.values.sum() +
                dir.children.values.map { dirSize(it, index) }.sum()
        index[dir.name] = count
        return count
    }

    fun constructTree(lines: List<String>) {
        var workingDir = root
        var index = 0
        while (index < lines.size - 1) {
            val line = lines[index]
            val tokens = line.split(" ")
            val cmd = tokens[1]
            when (cmd) {
                "cd" -> {
                    val dir = tokens[2]
                    workingDir = when (dir) {
                        "/" -> root
                        ".." -> workingDir.parent!!
                        else -> workingDir.children[dir]!!
                    }
                    index++
                }
                "ls" -> {
                    var outputIndex = index + 1
                    while (lines[outputIndex].isNotBlank() && !lines[outputIndex].startsWith('$')) {
                        val outputLine = lines[outputIndex]
                        val outputTokens = outputLine.split(" ")
                        when (outputTokens[0]) {
                            "dir" -> workingDir.children.put(outputTokens[1], Folder("${workingDir.name}/${outputTokens[1]}", workingDir))
                            else -> workingDir.files.put(outputTokens[1], outputTokens[0].toInt())
                        }
                        outputIndex++
                    }
                    index = outputIndex
                }
            }
        }
    }

    companion object {
        fun parseInput(input: String): List<String> {
            return input.split("\n")
        }
    }
}
