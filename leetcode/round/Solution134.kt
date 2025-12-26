package leetcode.round

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-20
 */
class Solution134 {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var i = 0
        while (i < gas.size) {
            var gasLeft = gas[i]
            for (j in i..<i + gas.size) {
                gasLeft -= cost[j % gas.size]
                if (gasLeft < 0) {
                    i = if (i == j) i + 1 else {
                        val next = j % gas.size
                        if (next < i) return -1
                        else next
                    }
                    break
                }
                gasLeft += gas[(j + 1) % gas.size]
            }

            if (gasLeft >= 0) {
                return i
            }
        }
        return -1
    }
}

fun main() {
    print(Solution134().canCompleteCircuit(intArrayOf(4, 5, 2, 6, 5, 3), intArrayOf(3, 2, 7, 3, 2, 9)))
}