class Day09 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()

    override fun partOne(): Long {
        val nums = inputLines(dayNumber).map { it.toLong() }
        val window = 25
        (window..nums.size).forEach { i ->
            val set = mutableSetOf<Long>()
            (i - window until i).forEach { a ->
                (i - window until i).forEach { b ->
                    if (nums[a] != nums[b])
                        set.add(nums[a] + nums[b])
                }
            }
            if (nums[i] !in set) {
                return nums[i]
            }
        }
        return -1
    }

    override fun partTwo(): Long {
        val total = partOne()
        val nums = inputLines(dayNumber).map { it.toLong() }
        (nums.indices).forEach { i ->
            var sum = nums[i]
            var mini = nums[i]
            var maxi = nums[i]
            (i + 1 until nums.size).forEach { j ->
                sum += nums[j]
                mini = if (nums[j] < mini) nums[j] else mini
                maxi = if (nums[j] > maxi) nums[j] else maxi
                if (sum == total) {
                    return mini + maxi
                }
                if (sum > total) {
                    return@forEach
                }
            }
        }
        return -1
    }

}


