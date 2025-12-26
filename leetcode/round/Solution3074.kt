package leetcode.round

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-24
 */
class Solution3074 {
    fun minimumBoxes(apple: IntArray, capacity: IntArray): Int {
        var cnt = 0
        capacity.sortDescending()
        var appleCnt = apple.sum()
        for (cap in capacity) {
            appleCnt -= cap
            cnt++
            if (appleCnt <= 0) {
                return cnt
            }
        }
        return cnt
    }
}