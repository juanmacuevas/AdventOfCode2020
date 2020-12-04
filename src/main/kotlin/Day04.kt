class Day04 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()

    override fun partOne(): Int {
        val listPassports =  mutableListOf<MutableMap<String, String>>().apply { add(mutableMapOf()) }
        inputLines(dayNumber).fold(listPassports, ::linesToIDs)
        return listPassports.count { validID1(it) }
    }

    override fun partTwo(): Int {
        val listPassports =  mutableListOf<MutableMap<String, String>>().apply { add(mutableMapOf()) }
        inputLines(dayNumber).fold(listPassports, ::linesToIDs)
        return listPassports.count { validID2(it) }
    }

    private fun linesToIDs(
        all: MutableList<MutableMap<String, String>>,
        line: String
    ): MutableList<MutableMap<String, String>> {
        if (line.isNullOrBlank()) {
            all.add(mutableMapOf())
        } else {
            val entries: List<Pair<String, String>> = line
                .split(" ", ":")
                .chunked(2)
                .map { it.toPair() }
                .filterNotNull()
            all.last().putAll(entries)
        }
        return all
    }

    private fun validID1(passport: Map<String, String>): Boolean {
        return passport.keys.size == 8 || (passport.keys.size == 7 && !passport.keys.contains("cid"))
    }

    private val heightRanges = mapOf("cm" to 150..193, "in" to 59..76)
    private val eyeColors = "amb blu brn gry grn hzl oth".split(" ")
    private val colorRe = Regex("#[0-9a-f]{6}")
    private val passportRe = Regex("\\d{9}")

    private fun validID2(idCard: Map<String, String>): Boolean {
        if (idCard["byr"]?.toInt().notInRange(1920..2002)) return false
        if (idCard["iyr"]?.toInt().notInRange(2010..2020)) return false
        if (idCard["eyr"]?.toInt().notInRange(2020..2030)) return false

        val height = idCard["hgt"]?.dropLast(2)?.toInt()
        val units = idCard["hgt"]?.takeLast(2)
        if (height.notInRange(heightRanges[units])) return false

        if (!colorRe.matches(idCard.getOrDefault("hcl", ""))) return false
        if (idCard["ecl"] !in eyeColors) return false
        if (!passportRe.matches(idCard.getOrDefault("pid", ""))) return false
        return true
    }
}



