package leetcode.round

import java.util.LinkedList

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-26
 */
class Solution496 {
    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        val stack: LinkedList<Int> = LinkedList()
        val res: MutableMap<Int, Int> = HashMap()
        stack.push(Integer.MAX_VALUE)
        for (i in nums2.size - 1 downTo 0) {
            while (stack.peek() < nums2[i]) {
                stack.pop()
            }
            res[nums2[i]] = if (stack.peek() == Int.MAX_VALUE) -1 else stack.peek()
            stack.push(nums2[i])
        }

        val ans = IntArray(nums1.size)
        for (i in 0..<nums1.size) {
            ans[i] = res[nums1[i]]!!
        }
        return ans
    }
}

fun main() {
    Solution496().nextGreaterElement(intArrayOf(4, 1, 2), intArrayOf(1, 3, 4, 2))
        .forEach { println(it) }
}