package leetcode.round

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-26
 */
class Solution2483 {
    fun bestClosingTime2(customers: String): Int {
        var benefitMost = 0
        var benefitNow = 0
        var closeDay = 0
        for (i in customers.indices) {
            if (customers[i] == 'Y') {
                benefitNow += 1
            } else {
                benefitNow -= 1
            }

            if (benefitNow > benefitMost) {
                benefitMost = benefitNow
                closeDay = i + 1
            }
        }
        return closeDay
    }

    // 三次遍历， 左一遍，右一遍，再一遍
    fun bestClosingTime1(customers: String): Int {
        val length = customers.length
        val N = IntArray(length + 1) { 0 }
        for (i in 0..length) {
            if (i == 0) {
                N[i] = 0
            } else {
                if (customers[i - 1] == 'N') {
                    N[i] = 1 + N[i - 1]
                } else {
                    N[i] = N[i - 1]
                }
            }
        }

        val Y = IntArray(length + 1) { 0 }
        for (i in length downTo 0) {
            if (i == length) {
                Y[i] = 0
            } else {
                if (customers[i] == 'Y') {
                    Y[i] = 1 + Y[i + 1]
                } else {
                    Y[i] = Y[i + 1]
                }
            }
        }

        var cost = Int.MAX_VALUE
        var idx = 0
        for (i in 0..length) {
            if (N[i] + Y[i] < cost) {
                cost = N[i] + Y[i]
                idx = i
            }
        }
        return idx
    }
}

fun main() {
    print(Solution2483().bestClosingTime2("NNNN"))
}