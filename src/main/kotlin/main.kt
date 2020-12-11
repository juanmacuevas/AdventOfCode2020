import kotlin.system.measureTimeMillis

fun main() {

    InputFilesDownloader().downloadAll()

    InputFilesDownloader().rangeOfDaysPublished()
        .map { "Day%02d".format(it) }
        .map { Class.forName(it)?.newInstance() }
        .forEach {
            it as AdventDay
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

fun <E> List<E>.splitOn(separator: String): List<List<E>> {
    var all = mutableListOf<MutableList<E>>().apply { add(mutableListOf()) }
    return this.fold(all, { acc, item ->
        if ((item == separator) && acc.last().isNotEmpty()) {
            acc.add(mutableListOf())
        }
        acc.apply { if (item != separator) last().add(item) }
    })
}