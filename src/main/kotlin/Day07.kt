class Day07 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()
    private val input = inputLines(dayNumber)

    override fun partOne(): Int {
                val mapAll = mutableMapOf<String,MutableSet<String>>()
        val a = input.map { toAirportRule(it) }
        a.forEach { entry ->
            entry.forEach {
                val set = mapAll.getOrDefault(it.key, mutableSetOf())
                set.add(it.value)
                mapAll[it.key] = set
            }
        }

        val processNext = mutableListOf<String>().apply { add("shiny gold") }
        var canCarry = mutableSetOf<String>()
        while (processNext.isNotEmpty()) {
            val item = processNext.removeFirst()
            mapAll[item]?.let {
                processNext.addAll(it)
                canCarry.addAll(it.toList())
            }
        }
        return canCarry.size
    }

    private fun listOfParents(
        acc: MutableMap<String, MutableList<String>>,
        entry: Pair<String, String>,
    ): MutableMap<String, MutableList<String>> {
        entry.second.forEach {
            val list = acc.getOrDefault(entry.first, mutableListOf())
            list.add(entry.second)

        }
        return acc

    }

    private fun parentsOf(inside: Pair<String, List<String>>): List<Pair<String, String>> {
        return inside.second.map { it to inside.first }

    }

    private val re = Regex("\\d (\\D+ \\D+) bag")
    fun ruleToMap(rule: String): Pair<String, List<String>> {
        val (parent, childs) = rule.split(" bags contain ")
        return if (childs.contains("no other"))
            (parent to emptyList())
        else
            (parent to re.findAll(childs).toList().map { it.groupValues[1] })
    }

    override fun partTwo(): Int {
        val mapAll = mutableMapOf<String, List<Bag>>()
        val a = input.map { toAirportRule2(it) }

        a.forEach { entry ->
            mapAll.putAll(entry)
        }

        val process = mutableListOf(mapAll["shiny gold"]!!.toList())
        var count = 0
        while (process.isNotEmpty()) {
            val bags = process.removeFirst()
            bags.forEach { bag ->
                count += bag.amount
                val newList = mutableListOf<Bag>()
                mapAll[bag.name]!!
                    .map {
                        Bag(it.name, it.amount * bag.amount)
                    }
                    ?.forEach {
                        newList.add(it)
                    }
                process.add(newList)
            }

        }
        return count
    }

    private fun toAirportRule(rule: String): MutableMap<String, String> {
        val list = rule.split(" bags contain ", " bag, ", " bags, ", "bags.", "bag.")
        val dict = mutableMapOf<String, String>()
        for (i in 1..list.size - 2) {
            if (!list[i].contains("no other"))
                dict[list[i].drop(2).trim()] = list[0].trim()
        }
        return dict

    }

    class Bag(val name: String, var amount: Int) {
        override fun toString(): String {
            return name + "-" + amount.toString()
        }
    }

    private fun toAirportRule2(rule: String): Map<String, List<Bag>> {
        val list = rule.split(" bags contain ", " bag, ", " bags, ", "bags.", "bag.")
        val dict = mutableMapOf<String, Bag>()
        val r = (1..list.size - 2).map { list[it] }.filter { !it.contains("no other") }
            .map {
                Bag(it.drop(2).trim(), it.take(2).trim().toInt())

            }
        return mapOf(list[0] to r)

    }


}


