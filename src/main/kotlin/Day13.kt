import java.lang.Math.abs

class Day13 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()
    override fun partOne(): Number {
        val input = inputLines(dayNumber)
        val zero = input[0].toInt()
        var mini = 999999999
        val lines = input[1].split(",").filter { it != "x" }.map { it.toInt() }
        lines
            .mapIndexed { i, it -> it - (zero % it) to lines[i] }.forEach {
                if (it.first < mini) {
                    mini = it.first
                }
            }

        return -1
    }

    override fun partTwo(): Long {
        val input = inputLines(dayNumber)[1].split(",")
        val ids = input
            .filter { it != "x" }
            .map { it.toInt() }
        val offsets = input
            .withIndex()
            .filter { it.value != "x" }
            .map { it.index }
        val n = ids.map { it.toLong() }.toLongArray()
        val a = n.zip(offsets).map { it.first - it.second }.toLongArray()
        return chineseRemainder(n, a)
    }

    // Chinese remainder https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
    private fun chineseRemainder(n: LongArray, a: LongArray): Long {
        require(n.size == a.size)
        val prod = n.reduce { acc, l -> acc * l }
        var sum = 0L
        for (i in n.indices) {
            val p = prod / n[i]
            sum += a[i] * multInv(p, n[i]) * p
        }
        return sum % prod
    }

    /* returns x where (a * x) % b == 1 */
    private fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0) x1 += b
        return x1
    }

}