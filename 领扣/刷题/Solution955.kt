package 领扣.刷题

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-21
 */
class Solution955 {
    fun minDeletionSize(strs: Array<String>): Int {
        if (strs.isEmpty()) return 0
        val sortedIndices: MutableSet<Int> = mutableSetOf()
        val length = strs[0].length
        var cnt = 0
        for (j in 0..<length) {
            val tmpSortedIndices = mutableListOf<Int>()
            for (i in strs.indices) {
                if (sortedIndices.contains(i)) continue
                if (i == 0) continue
                if (strs[i-1][j] > strs[i][j]) {
                    cnt++
                    tmpSortedIndices.clear()
                    break
                } else if (strs[i-1][j] == strs[i][j]) {
                    continue
                } else {
                    tmpSortedIndices.add(i)
                }
            }
            sortedIndices.addAll(tmpSortedIndices)
            if (sortedIndices.size == strs.size) {
                return cnt
            }
        }
        return cnt
    }
}

fun main() {
    print(Solution955().minDeletionSize(arrayOf("zyx","wvu","tsr")))
}