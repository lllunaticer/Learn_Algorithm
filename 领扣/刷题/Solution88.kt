package 领扣.刷题

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-20
 */
class Solution88 {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var p = m + n - 1
        var pm = m - 1
        var pn = n - 1
        while (p >= 0) {
            if (pm >= 0 && pn >= 0) {
                if (nums1[pm] >= nums2[pn]) {
                    nums1[p] = nums1[pm]
                    pm--
                } else {
                    nums1[p] = nums2[pn]
                    pn--
                }
            }
            else if (pm >= 0) {
                nums1[p] = nums1[pm]
                pm--
            }
            else if (pn >= 0) {
                nums1[p] = nums2[pn]
                pn--
            }
            p--
        }
    }
}

fun main() {
    Solution88().merge(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3)
}