package 领扣.刷题

import kotlin.math.max

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-22
 */
class Solution960 {
    fun minDeletionSize(strs: Array<String>): Int {
        if (strs.isEmpty()) return 0
        val length = strs[0].length
        // dp[i]表示以第i列结尾的最大长度
        val dp = IntArray(length) { 1 }
        // 循环计算每个dp[i]的值
        for (i in 0..<length) {
            // 每个dp[i] 由它之前每个列j的值dp[j] 动态计算而得： 如果j列满足字典序，则dp[i] = max(dp[i], dp[j] + 1)
            for (j in 0..<i) {
                // 在所有行上，列j都不大于列i
                if (strs.all { it[j] <= it[i] })
                    dp[i] = max(dp[i], dp[j] + 1)
            }
        }
        return length - dp.max()
    }
}

fun main() {
    print(Solution960().minDeletionSize(arrayOf("babca", "bbazb")))
}