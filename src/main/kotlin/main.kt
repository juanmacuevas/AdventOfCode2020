import kotlin.system.measureTimeMillis

fun main() {
    InputFilesDownloader().downloadAll()

    val adventDays = listOf<AdventDay>(
        Day01(),
        Day02(),
        Day03(),
        Day04(),
        Day05()
    )
    adventDays.forEach {
        println("# Day %02d".format(it.dayNumber))
        var result: Number
        var time: Long
        time = measureTimeMillis { result = it.partOne() }
        println("\t1. %d (%dms)".format(result, time))
        time = measureTimeMillis { result = it.partTwo() }
        println("\t2. %d (%dms)".format(result, time))
    }

}

fun Number?.notInRange(range: IntRange?): Boolean {
    return range?.contains(this) != true
}

fun <T> List<T>.toPair(): Pair<T, T>? {
    if (size < 2) return null
    return Pair(this[0], this[1])
}
