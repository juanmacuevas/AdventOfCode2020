class Day02 : AdventDay {
    override val dayNumber = this.javaClass.name.substring(3, 5).toInt()
    val input = inputLines(dayNumber)
    override fun partOne(): Int {
        return input.count { isValidCriteriaOne(it) }
    }

    override fun partTwo(): Int {
        return input.count { isValidCriteriaTwo(it) }
    }

    fun isValidCriteriaOne(fullLine: String): Boolean {
        val line = fullLine.split(" ")
        val min = line[0].split("-")[0].toInt() - 1
        val max = line[0].split("-")[1].toInt() - 1
        val character = line[1][0].toChar()
        val pass = line[2]
        val count = pass.count { it == character }
        return (min..max).contains(count)
    }

    data class PassEntry(val min: String, val max: String, val character: String, val pass: String)

    fun isValidCriteriaTwo(fullLine: String): Boolean {
        val line = fullLine.split(" ")
        val pos0 = line[0].split("-")[0].toInt() - 1
        val pos1 = line[0].split("-")[1].toInt() - 1
        val character = line[1][0]
        val pass = line[2]
        return listOf(pass[pos0], pass[pos1]).count { it == character } == 1
    }
}
