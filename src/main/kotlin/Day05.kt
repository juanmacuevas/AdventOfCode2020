class Day05 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()
    private val binaryOfChar = mapOf('F' to '0', 'B' to '1', 'L' to '0', 'R' to '1')
    override fun partOne(): Int {
        return seatIDs()
            .maxOf { it }
    }

    override fun partTwo(): Int {
        val seats = seatIDs().sorted()
        val min: Int = seats[0]
        val maxi: Int = seats[seats.size - 1]
        return (min..maxi).filter { it !in seats }[0]
    }

    private fun seatIDs() = inputLines(dayNumber)
        .map { line ->
            line.map { char ->
                binaryOfChar[char]
            }.joinToString("")
                .toInt(2)
        }

}
