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


