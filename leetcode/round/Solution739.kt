package leetcode.round

import java.util.LinkedList

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-26
 */
class Solution739 {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack: LinkedList<Int> = LinkedList()
        val ans = IntArray(temperatures.size) { 0 }
        for (i in temperatures.size - 1 downTo 0) {
            while (stack.isNotEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                stack.pop()
            }
            ans[i] = if (stack.isEmpty()) 0 else stack.peek() - i
            stack.push(i)
        }
        return ans
    }
}

fun main() {
    Solution739().dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)).forEach { println(it) }
}