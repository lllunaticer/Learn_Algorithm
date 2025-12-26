package leetcode.round

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-25
 */
class Solution3075 {
    fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
        happiness.sortDescending()
        var res = 0L
        for (i in 0..<k) {
            val leftHappy = happiness[i] - i
            if (leftHappy <= 0) {
                return res
            }
            res += leftHappy
        }
        return res
    }
}