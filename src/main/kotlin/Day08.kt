class Day08 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()

//    private val groups = inputLines(dayNumber).splitOn("")

    override fun partOne(): Int {
        val program = inputLines(dayNumber).mapIndexed { i, it ->
            val (inst, value) = it.split(" ")
            Action(i, inst, value.toInt())
        }
        var current = program[0]
        val visited = mutableSetOf<Int>()
        var acc = 0
        while (!visited.contains(current.index)) {
            visited.add(current.index)
            var next = program[current.index + 1]
            when (current.input) {
                "acc" -> {
                    acc += current.value
                }
                "jmp" -> next = program[current.index + current.value]
            }
            current = next
        }
        return acc
    }

    data class Action(val index: Int, var input: String, val value: Int)

    override fun partTwo(): Int {

        val program = inputLines(dayNumber).mapIndexed { i, it ->
            val (inst, value) = it.split(" ")
            Action(i, inst, value.toInt())
        }
        var toSwap = indNextOp(program, -1)

        while (toSwap < program.size && !runProgramNoLoop(program, toSwap)) {
            toSwap = indNextOp(program, toSwap)
        }

        return runProgram(program)
    }

    private fun runProgram(program: List<Action>): Int {
        var current: Action? = program[0]
        var acc = 0

        while (current != null) {
            var nextIndex = current.index + 1
            when (current.input) {
                "acc" -> {
                    acc += current.value
                }
                "jmp" -> nextIndex = current.index + current.value
            }

            current = program.getOrNull(nextIndex)
        }
        return acc
    }

    private fun indNextOp(program: List<Action>, pos: Int): Int {
        (pos+1..program.size-1).forEach { i -> if (program[i].input != "acc") return i }
        return program.size
    }

    fun runProgramNoLoop(program: List<Day08.Action>, toSwap: Int): Boolean {
        switchOp(program, toSwap)
        var current = program[0]
        val visited = mutableSetOf<Int>()
        var acc = 0
        while (!visited.contains(current.index)) {
            visited.add(current.index)
            if (current.index == program.size-1) return true
            var next = program[current.index + 1]
            when (current.input) {
                "acc" -> {
                    acc += current.value
                }
                "jmp" -> next = program[current.index + current.value]
            }
            current = next
        }
        switchOp(program, toSwap)
        return false
    }

    private fun switchOp(program: List<Day08.Action>, toSwap: Int) {
        program[toSwap].input = when (program[toSwap].input) {
            "jmp" -> "nop"
            "nop" -> "jmp"
            else -> program[toSwap].input
        }
    }
}


