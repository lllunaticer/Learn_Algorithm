package leetcode.round

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-20
 */
class Solution644 {
    fun minDeletionSize(strs: Array<String>): Int {
        val size = strs.size
        if (size <= 0) return 0
        val length = strs[0].length
        var cnt = 0
        for (i in 0 until length) {
            var last = '0'
            for (j in 0 until size) {
                if (strs[j][i] <= last) {
                    cnt++
                    break
                }
                last = strs[j][i]
            }
        }
        return cnt
    }
}

fun main() {
    print(Solution644().minDeletionSize(arrayOf("rrjk","furt","guzm")))
}