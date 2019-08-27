package 回溯;

public class totalQueens {
    static int count = 0;

    public int totalNQueens(int n) {
        int nQueens = n;
        int[] result = new int[nQueens];

        calQueens(result, nQueens, 0);

        return count;
    }

    //    递归回溯
    private void calQueens(int[] result, int nQueens, int row) {
        if (row == nQueens) {//如果已经放完了最后一行，这层递归，将结果输出
            count++;
            return;
        }

        for (int column = 0; column < nQueens; column++) {
            if (isOk(result, row, column)) {
                result[row] = column;
                calQueens(result, nQueens, row + 1);
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

    public static void main(String[] args) {
        totalQueens nq = new totalQueens();
        nq.totalNQueens(1);
        System.out.println(count);
    }
}
