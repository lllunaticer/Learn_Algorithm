package leetcode.round

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-28
 */
class Solution240 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val rowLength = matrix.size
        val columnLength = matrix[0].size

        var rowIdx = 0
        var columnIdx = columnLength - 1
        while (rowIdx < rowLength && columnIdx >= 0) {
            if (matrix[rowIdx][columnIdx] == target) {
                return true
            } else if (matrix[rowIdx][columnIdx] > target) {
                columnIdx--
            } else {
                rowIdx++
            }
        }
        return false
    }
}