import java.io.File
import java.io.FileNotFoundException

interface AdventDay {
    abstract val dayNumber: Int

    fun partOne(): Number
    fun partTwo(): Number

    fun inputLines(day: Int): List<String> =

        try {
            File("day%02d.txt".format(day)).readLines().map { it }
        } catch (e: FileNotFoundException) {
            emptyList<String>()
        }

    fun inputString(day: Int): String =
        try {
            File("day%02d.txt".format(day)).readText()
        } catch (e: FileNotFoundException) {
            ""
        }

}
