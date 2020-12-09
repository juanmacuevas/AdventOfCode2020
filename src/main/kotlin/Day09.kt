import java.lang.Long.min

class Day09 : AdventDay {
    override val dayNumber = this.javaClass.name.takeLast(2).toInt()
//    private val groups = inputLines(dayNumber).splitOn("")

    override fun partOne(): Long {
        val nums = inputLines(dayNumber).map { it.toLong() }
        val window = 25
        (window..nums.size).forEach { i ->
            val set = mutableSetOf<Long>()
            (i - window..i - 1).forEach { a ->
                (i - window..i - 1).forEach { b ->
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
        (0..nums.size - 1).forEach { i ->
            var sum = nums[i]
            var mini = nums[i]
            var maxi = nums[i]
            (i + 1..nums.size - 1).forEach { j ->
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


