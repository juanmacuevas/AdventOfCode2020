import kotlin.system.measureTimeMillis

class Day15 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()
    private val inputList = inputLines(dayNumber)[0].split(",").map { it.toInt() }

    override fun partOne(): Number {
        return turnList(2020)
    }

    override fun partTwo(): Number {
        return turnMap(30000000)
    }

    private fun turnList(turn: Int): Number {
        val list = inputList.toMutableList()
        var i = list.size
        var current: Int
        while (i < turn) {
            val prev = list.removeLast()
            val lastIndexPrev = list.lastIndexOf(prev)
            current = if (lastIndexPrev == -1) {
                0
            } else {
                i - 1 - lastIndexPrev
            }
            list.add(prev)
            list.add(current)
            i++
        }
        return list.last()
    }

    private fun turnMap(turn: Int): Long {
        val lastIndex = mutableMapOf<Long, Long>().apply {
            putAll(inputList.mapIndexed { i, v -> v.toLong() to i.toLong() })
        }
        var i = lastIndex.size + 1L
        var current = 0L
        var prev = 0L
        while (i < turn) {
            current = if (prev !in lastIndex) {
                0
            } else {
                i - 1L - lastIndex[prev]!!
            }
            lastIndex[prev] = i - 1L
            prev = current
            i++
        }
        return current
    }

}