package 领扣.刷题

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-20
 */
class Solution238 {

    // 前后缀
    fun productExceptSelf(nums: IntArray): IntArray {
        val res = IntArray(nums.size)
        for (i in nums.indices) {
            res[i] = if (i == 0) nums[i] else res[i - 1] * nums[i]
        }

        var right = 0

        for (i in nums.size - 1 downTo 0) {
            res[i] = when (i) {
                nums.lastIndex -> {
                    right = nums[i]
                    res[i - 1]
                }
                0 -> {
                    right
                }
                else -> {
                    val tmp = res[i - 1] * right
                    right *= nums[i]
                    tmp
                }
            }
        }
        return res
    }
}

fun main() {
    Solution238().productExceptSelf(intArrayOf(1, 2, 3, 4)).forEach { println(it) }
}