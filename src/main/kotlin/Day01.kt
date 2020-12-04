class Day01 : AdventDay {

    override val dayNumber = this.javaClass.name.substring(3, 5).toInt()
    override fun partOne(): Int {
        val expenses: List<Int> = inputLines(dayNumber).map { it.toInt() }
        return solveSumTwo(expenses)
    }

    override fun partTwo(): Int {
        val expenses: List<Int> = inputLines(dayNumber).map { it.toInt() }
        return solveSumThree(expenses)
    }

    fun solveSumTwo(allExpenses: List<Int>): Int {
        allExpenses.forEach { i ->
            allExpenses.forEach { j ->
                if (i != j
                    && i + j == 2020
                ) {
                    return i * j
                }
            }
        }
        return -1
    }

    private fun solveSumThree(allExpenses: List<Int>): Int {
        allExpenses.forEach { i ->
            allExpenses.forEach { j ->
                allExpenses.forEach { k ->
                    if (i != j && j != k && i != k
                        && i + j + k == 2020
                    ) {
                        return i * j * k
                    }
                }
            }
        }
        return -1
    }

    fun solveSumTwoWithSet(expensesList: List<Int>): Int {
        val expenses = expensesList.toSet()
        return expensesList.filter { expenses.contains(2020 - it) }.reduce { a, b -> a * b }
    }

}
