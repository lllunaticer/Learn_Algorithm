package leetcode.round

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-28
 */
class Solution1351 {
    // 看 03xf的题解吧
    //https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix/solutions/3861108/tu-jie-zuo-fa-tong-240-ti-yi-tu-miao-don-vovs/?envType=daily-question&envId=2025-12-28

    // 这题的类似题: S240 搜索二维矩阵
    fun countNegatives(grid: Array<IntArray>): Int {
        var count = 0
        val rowLength = grid.size
        val columnLength = grid[0].size

        var rowIdx = 0
        var columnIdx = columnLength - 1
        while (rowIdx < rowLength && columnIdx >= 0) {
            if (grid[rowIdx][columnIdx] < 0) {
                count = count + rowLength - rowIdx
                columnIdx--
            } else {
                rowIdx++
            }
        }
        return count
    }
}

fun main() {
    println(
        Solution1351().countNegatives(
            arrayOf(
                intArrayOf(4, 3, 2, -1),
                intArrayOf(-1, -1, -1, -1),
                intArrayOf(-1, -1, -1, -2),
                intArrayOf(-1, -1, -2, -3)
            )
        )
    )
}