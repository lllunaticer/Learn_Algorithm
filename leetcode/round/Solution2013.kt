package leetcode.round

/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-20
 */
class DetectSquares() {
    val board: Array<Array<Int>> = Array(1000) { Array(1000) { 0 } }

    fun add(point: IntArray) {
        board[point[0]][point[1]] += 1
    }

    fun count(point: IntArray): Int {
        val y = point[1]
        val x = point[0]
        var cnt = 0
        // 水平搜索
        for (j in 0 until board[x].size) {
            if (y == j) continue
            if (board[x][j] <= 0) {
                continue
            }
            // 往下
            val distance = j - y
            if (distance + x in 0..<board[x].size) {
                cnt += 1 * board[x][j] * board[x + distance][y] * board[x + distance][j]
            }
            // 往上
            if (x - distance in 0..<board[x].size) {
                cnt += 1 * board[x][j] * board[x - distance][y] * board[x - distance][j]
            }
        }
        return cnt
    }
}

fun main() {
    val detectSquares = DetectSquares()
    detectSquares.add(intArrayOf(419, 351))
    detectSquares.add(intArrayOf(798, 351))
    detectSquares.add(intArrayOf(798, 730))
    println(detectSquares.count(intArrayOf(419, 730)))
    detectSquares.add(intArrayOf(998, 1))
    detectSquares.add(intArrayOf(0, 999))
    detectSquares.add(intArrayOf(998, 999))
    print(detectSquares.count(intArrayOf(0, 1)))
}