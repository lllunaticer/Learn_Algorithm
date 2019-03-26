/*
* 题目描述
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
例如
a b c e
s f c s
a d e e
这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，
路径不能再次进入该格子。*/
public class 第十六题矩阵中的路径 {
     static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
//        特殊情况
        if (matrix.length == 1)
            return matrix[0] == str[0];
//        暴力枚举
//        char[][] matrix_ = new char[rows][cols];
//        int k = 0;
//       将一维转为二维
//        for (int i = 0; i < rows; i++)
//            for (int j = 0; j < cols; j++)
//                matrix_[i][j] = matrix[k++];

//      枚举所有起点
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
//                dfs是在该起点的基础上枚举所有方向
                if (dfs(matrix, str, 0, i, j, rows, cols))
                    return true;
        return false;
    }

    //    dfs的作用是枚举所有方向
    static boolean dfs(char[] matrix, char[] str, int u, int x, int y, int rows, int cols) {
//        如果当前u的长度达到了要匹配的字符串的末尾，则返回true
        if (u == str.length) return true;
//        如果当前字符和str上要找u处的字符不同，返回false
        if (matrix[x*cols+y] != str[u]) return false;
//        枚举方向的常用方法
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
//        为了保证不走重复路线（不往回走），需要将走过的位置标记，但是在退出该条枚举路线之前需要还原现场
        char t = matrix[x*cols+y];
        matrix[x*cols+y] = '*';
//        枚举四个方向
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            if (a >= 0 && a < rows && b >= 0 && b < cols) {
//                如果后面递归的结果是true,那么到了本层应该把true传递到下一层
                if (dfs(matrix, str, u + 1, a, b, rows, cols))
                    return true;
            }
        }
        matrix[x*cols+y] = t;
        return false;
    }

    public static void main(String[] args) {
        char[] a = {'A'};
        char[] b = {'A'};
        System.out.println(hasPath(a, 1, 2, b));
    }
}
//下面是通过的题解2,不用转为二维矩阵
/*
*  static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix.length == 1)
            return matrix[0] == str[0];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(matrix, 0, i, j, str, rows, cols))
                    return true;
            }
        }
        return false;
    }

    static boolean dfs(char[] matrix, int u, int x, int y, char[] str, int rows, int cols) {
        if (u == str.length) return true;
        if (matrix[x * cols + y] != str[u]) return false;
        char tmp = matrix[x * cols + y];
        matrix[x * cols + y] = '*';
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
//
            if (a >= 0 && a < rows && b >= 0 && b < cols) {
                if (dfs(matrix, u + 1, a, b, str, rows, cols))
                    return true;
            }
        }
        matrix[x * cols + y] = tmp;
        return false;
    }
* */
//下面是通过的题解1,要先转为二维矩阵
/*public class Solution {
 public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if(matrix.length==1)
            return matrix[0] == str[0];
        char[][] matrix_ = new char[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix_[i][j] = matrix[k++];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (dfs(matrix_, str, 0, i, j))
                    return true;
        return false;
    }

    boolean dfs(char[][] matrix, char[] str, int u, int x, int y) {
        if (u == str.length) return true;
        if (matrix[x][y] != str[u]) return false;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        char t = matrix[x][y];
        matrix[x][y] = '*';
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            if (a >= 0 && a < matrix.length && b >= 0 && b < matrix[a].length) {
                if (dfs(matrix, str, u + 1, a, b))
                    return true;
            }
        }
        matrix[x][y] = t;
        return false;
    }
}*/