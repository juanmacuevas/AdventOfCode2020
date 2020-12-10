import java.lang.Math.abs

class Day10 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()

    override fun partOne(): Int {
        val jolts = inputLines(dayNumber).map { it.toInt() }.sorted()
        val diffs = mutableMapOf<Int, Int>()
        (1 until jolts.size).forEach { i ->
            val d = abs(jolts[i] - jolts[i - 1])
            diffs[d] = diffs.getOrDefault(d, 0) + 1
        }
        return (diffs[1]!! + 1) * (1 + diffs[3]!!)
    }

    override fun partTwo(): Long {
        val listAdjacent = mutableListOf(0)
        inputLines(dayNumber)
            .map { it.toInt() }
            .sorted()
            .toMutableList()
            .apply {
                add(0, 0)
            }
            .zipWithNext()
            .map { it.second - it.first }
            .fold(listAdjacent, { acc, i ->
                acc.apply {
                    add(if (i == 3) 0 else removeLast() + 1)
                }
            })
        return listAdjacent
            .map { n -> n * (n - 1) / 2 + 1L }
            .reduce { acc, i -> (acc * i) }
    }
}


