class Day06 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()

    private val groups = inputLines(dayNumber).splitOn("")

    override fun partOne(): Int {
        return groups.map {
            it.map { answers -> answers.toSet() }
                .flatten() //puts all answers in group together
                .toSet() //removes duplicates
                .size
        }.sum()
    }

    override fun partTwo(): Int {
        return groups.map {
            it.map { answers -> answers.toSet() }
                .reduce { firstAnswers, otherAnswers ->
                    firstAnswers.intersect(otherAnswers) }
                .size
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


