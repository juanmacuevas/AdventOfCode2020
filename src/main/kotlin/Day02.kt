class Day02 : AdventDay {
    override val dayNumber = this.javaClass.name.substring(3, 5).toInt()

    override fun partOne(): Int {
        return inputLines(dayNumber).count { isValidCriteriaOne(it) }
    }

    override fun partTwo(): Int {
        return inputLines(dayNumber).count { isValidCriteriaTwo(it) }
    }

    fun isValidCriteriaOne(fullLine: String): Boolean {
        val entry = fullLine.split("-", " ", ": ")
        val (min, max) = entry.subList(0, 2).map { it.toInt() }
        val (character, pass) = entry.subList(2, 4)
        val count = pass.count { it.toString() == character }
        return (min..max).contains(count)
    }

    class Cosa {

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
