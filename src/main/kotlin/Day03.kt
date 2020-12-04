class Day03 : AdventDay {
    override val dayNumber = this.javaClass.name.substring(3, 5).toInt()

    override fun partOne(): Int {
        val forest = inputLines(dayNumber)
        return traverse(forest, 3, 1).toInt()
    }

    override fun partTwo(): Long {
        val forest = inputLines(dayNumber)
        val paths = listOf(
            traverse(forest, 1, 1),
            traverse(forest, 3, 1),
            traverse(forest, 5, 1),
            traverse(forest, 7, 1),
            traverse(forest, 1, 2)
        )
        return paths.reduce { acc, i -> acc * i }
    }

    private fun traverse(forest: List<String>, right: Int, down: Int): Long {
        val width = forest[0].length
        var posRight = 0
        var posDown = 0
        var trees = 0L
        while (posDown < forest.size) {
            val row = forest[posDown]

            if (row[posRight].equals('#')) {
                trees++
            }
            posRight = (posRight + right) % width
            posDown += down

        }
        return trees
    }

}
