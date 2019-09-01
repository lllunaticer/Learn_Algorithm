/*51 N-Queens
* The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
* */
package 回溯;

import java.util.*;

public class NQueens {

    static int count = 0;

    public List<List<String>> solveNQueens(int n) {
        int nQueens = n;
        int[] result = new int[nQueens];
        List<List<String>> final_result = new LinkedList<>();

        calQueens(result, final_result, nQueens, 0);

        return final_result;
    }

    //    递归回溯
    private void calQueens(int[] result, List<List<String>> final_result, int nQueens, int row) {
        if (row == nQueens) {//如果已经放完了最后一行，这层递归，将结果输出
            final_result.add(convert2List(result));
            count++;
            return;
        }

        for (int column = 0; column < nQueens; column++) {
            if (isOk(result, row, column)) {
                result[row] = column;
                calQueens(result, final_result, nQueens, row + 1);
            }
        }
    }

    //  检查在当前位置放置棋子是否和已有的棋子冲突
    private boolean isOk(int[] result, int row, int column) {
        int leftup = column - 1, rightup = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == column)//检查row行column列有无元素
                return false;
            if (leftup >= 0) {//检查左对角线上是否有元素
                if (result[i] == leftup)
                    return false;
            }
            if (rightup < result.length) {//检查右对角线上是否有元素
                if (result[i] == rightup)
                    return false;
            }
            leftup--;
            rightup++;
        }
        return true;
    }

    //    将数组转换为list列表返回
    private List<String> convert2List(int[] result) {
        int length = result.length;
        List<String> l = new LinkedList<>();
        for (int row = 0; row < length; row++) {
            StringBuilder s = new StringBuilder();
            for (int column = 0; column < length; column++) {
                if (result[row] == column)
                    s.append('Q');
                else
                    s.append('.');
            }
            l.add(s.toString());
        }
        return l;
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens();
        System.out.println(nq.solveNQueens(1));
        System.out.println(count);
    }
}
