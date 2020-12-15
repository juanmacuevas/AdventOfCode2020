class Day14 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()
    val mem = mutableMapOf<Long, Long>()
    var maskOne = mutableListOf<Long>()
    var maskTwo: String = ""

    override fun partOne(): Number {
        mem.clear()
        inputLines(dayNumber)
            .forEach {
                when (it[1]) {
                    'e' -> storeDecimalValueInMemory(it) //mem
                    'a' -> updateMaskOne(it)
                }
            }
        return mem.values.sum()
    }

    private fun storeDecimalValueInMemory(it: String) {
        val (a, b, c) = it.split("[", "] = ")
        val memAddress = b.toLong()
        var value = c.toLong()
        value = value.and(maskOne[0]).or(maskOne[1])
        mem[memAddress.toLong()] = value
    }

    private fun updateMaskOne(it: String) {
        val m = it.split(" = ")[1]
        maskOne.clear()
        maskOne.add(m.map { if (it == 'X') '1' else it }
            .joinToString("")
            .toLong(2))
        maskOne.add(m.map { if (it == 'X') '0' else it }
            .joinToString("")
            .toLong(2))
    }

    override fun partTwo(): Long {
        mem.clear()
        val input = inputLines(dayNumber)
        input.forEach {
            when (it[1]) {
                'e' -> saveMemTwo(it)
                'a' -> updateMaskTwo(it)
            }
        }
        return mem.values.sum()
    }

    private fun saveMemTwo(inputString: String) {
        val (a, b, c) = inputString.split("[", "] = ")
        val memAddress = b.toLong()
        val addrString = "%36s".format(java.lang.Long.toBinaryString(memAddress)).replace(' ', '0')
        val maskedAddress = addrString.mapIndexed { i, v ->
            when (maskTwo[i]) {
                '1' -> '1'
                'X' -> 'X'
                else -> v
            }
        }.joinToString("")
        computeFinalAddresses(maskedAddress).forEach { address ->
            mem[address] = c.toLong()
        }

    }

    private fun computeFinalAddresses(maskedAddress: String): List<Long> {
        val candidates = mutableListOf(maskedAddress)
        val finalAddress = mutableListOf<Long>()
        while (candidates.isNotEmpty()) {
            val item = candidates.removeFirst()
            if (item.contains('X')) {
                candidates.add(item.replaceFirst('X', '0'))
                candidates.add(item.replaceFirst('X', '1'))
            } else {
                finalAddress.add(item.toLong(2))
            }
        }
        return finalAddress
    }

    private fun updateMaskTwo(it: String) {
        maskTwo = it.split(" = ")[1]
    }


}