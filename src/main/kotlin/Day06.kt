class Day06 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()

    private val groups = inputLines(dayNumber).splitOn("")

    override fun partOne(): Int {
        return groups.map { group ->
            group.map { line ->
                line.toSet()
            }.flatten().toSet().size
        }.sum()
    }

    override fun partTwo(): Int {
        return groups.map { group ->
            group.map { line ->
                line.toSet()
            }.reduce { eachLine, otherLine ->
                eachLine.intersect(otherLine)
            }.size
        }.sum()
    }

}

fun <E> List<E>.splitOn(separator: String): List<List<E>> {
    var acc = mutableListOf<MutableList<E>>().apply { add(mutableListOf()) }
    return this.fold(acc, { acc, item ->
        if ((item == separator) && acc.last().isNotEmpty()) {
            acc.add(mutableListOf())
        }
        acc.apply { if (item != separator) last().add(item) }
    })
}


