package leetcode.round

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-20
 */
class Solution27 {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var p = 0
        for (i in nums.indices) {
            if (nums[i] != `val`) {
                if (p != i)
                    nums[p] = nums[i]
                p++
            }
        }
        return p
    }
}

fun main() {
    print(Solution27().removeElement(intArrayOf(1, 2, 3, 4, 5), 3))
}