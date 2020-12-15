import kotlin.math.abs

class Day12 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()
    override fun partOne(): Number {
        return inputLines(dayNumber)
            .fold(NavigatorOne(), { nav, action -> nav.update(action) })
            .manhattan()
    }

    override fun partTwo(): Number {
        return inputLines(dayNumber)
            .fold(NavigatorTwo(), { nav, action -> nav.update(action) })
            .manhattan()
    }

    abstract class Navigator {
        var east = 0
        var north = 0
        abstract fun update(action: String): Navigator
        fun manhattan() = abs(east) + abs(north)
    }

    class NavigatorOne : Navigator() {
        var course = 0 //,1,2,3
        var dir = mapOf("E" to 0, "N" to 1, "W" to 2, "S" to 3)

        override fun update(action: String): NavigatorOne {
            val value = action.drop(1).toInt()
            when (action.take(1)) {
                "L" -> rotate(value)
                "R" -> rotate(-value)
                "F" -> move(course, value)
                else -> move(dir[action.take(1)]!!, value)
            }
            return this
        }

        private fun move(course: Int, value: Int) {
            when (course) {
                0 -> east += value
                1 -> north += value
                2 -> east -= value
                3 -> north -= value
            }
        }

        private fun rotate(value: Int) {
            val angle = 360 + value / 90
            course = (course + angle) % 4
        }
    }

    class NavigatorTwo() : Navigator() {
        private var wX = 10
        private var wY = 1
        override fun update(action: String): NavigatorTwo {
            val value = action.drop(1).toInt()
            when (action.take(1)) {
                "N" -> wY += value
                "S" -> wY -= value
                "E" -> wX += value
                "W" -> wX -= value
                "L" -> rotate(value)
                "R" -> rotate((360 - value))
                "F" -> move(value)
            }
            return this
        }

        private fun move(value: Int) {
            east += value * wX
            north += value * wY
        }

        private fun rotate(value: Int) {
            repeat(value / 90) {
                val tmp = wX
                wX = -wY
                wY = tmp
            }
        }
    }
}
