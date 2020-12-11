class Day11 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()

    val FLOOR = 3
    val FREE = 0
    val OCUPPIED = 1

    override fun partOne() = updateUntilStable(makeListOf(inputLines(dayNumber))) { seats, x, y ->
        when (seats[x][y]) {
            FREE -> occupiedAround(seats, x, y) == 0
            OCUPPIED -> occupiedAround(seats, x, y) >= 4
            else -> false
        }
    }
        .map { row -> row.count { it == OCUPPIED } }
        .sum()


    override fun partTwo() = updateUntilStable(makeListOf(inputLines(dayNumber))) { seats, x, y ->
        when (seats[x][y]) {
            FREE -> occupiedVisible(seats, x, y) == 0
            OCUPPIED -> occupiedVisible(seats, x, y) >= 5
            else -> false
        }
    }
        .map { row -> row.count { it == OCUPPIED } }
        .sum()


    private fun updateUntilStable(
        seats: MutableList<MutableList<Int>>,
        changeCriteria: (List<List<Int>>, Int, Int) -> Boolean,
    ): MutableList<MutableList<Int>> {

        var seatsToChange = listOfSeatsToChange(seats, changeCriteria)
        while (seatsToChange.isNotEmpty()) {
            seatsToChange.forEach {
                val (x, y) = it
                seats[x][y] = when (seats[x][y]) {
                    OCUPPIED -> FREE
                    FREE -> OCUPPIED
                    else -> seats[x][y]
                }
            }
            seatsToChange = listOfSeatsToChange(seats, changeCriteria)
        }
        return seats
    }

    private fun listOfSeatsToChange(
        grid: MutableList<MutableList<Int>>,
        criteria: (List<List<Int>>, Int, Int) -> Boolean,
    ): List<List<Int>> {

        val changes = mutableListOf<List<Int>>()
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, v ->
                if (criteria(grid, i, j))
                    changes.add(listOf(i, j))
            }
        }
        return changes
    }

    private fun occupiedAround(grid: List<List<Int>>, x: Int, y: Int): Int {
        var count = if (grid[x][y] == OCUPPIED) -1 else 0
        for (i in x - 1..x + 1) {
            for (j in y - 1..y + 1) {
                if (inside(grid, i, j) && (grid[i][j]) == OCUPPIED)
                    count++
            }
        }
        return count
    }

    private fun makeListOf(lines: List<String>): MutableList<MutableList<Int>> {
        var seats = lines.map {
            it.map { ch ->
                when (ch) {
                    '.' -> FLOOR
                    'L' -> 0
                    else -> FLOOR
                }

            }.toMutableList()
        }.toMutableList()
        return seats
    }

    private fun occupiedVisible(grid: List<List<Int>>, x: Int, y: Int): Int {
        return listOf(
            visibleInDirection(grid, x, y, -1, -1),
            visibleInDirection(grid, x, y, 0, -1),
            visibleInDirection(grid, x, y, 1, -1),
            visibleInDirection(grid, x, y, -1, 0),
            visibleInDirection(grid, x, y, 1, 0),
            visibleInDirection(grid, x, y, -1, 1),
            visibleInDirection(grid, x, y, 0, 1),
            visibleInDirection(grid, x, y, 1, 1)
        ).count { it == OCUPPIED }
    }

    private fun visibleInDirection(grid: List<List<Int>>, x: Int, y: Int, di: Int, dj: Int): Int {
        var i = x + di
        var j = y + dj
        while (inside(grid, i, j) && grid[i][j] == FLOOR) {
            i += di
            j += dj
        }
        return if (inside(grid, i, j)) grid[i][j] else FLOOR
    }

    private fun inside(grid: List<List<Int>>, i: Int, j: Int) = i in grid.indices && j in grid[0].indices
}




