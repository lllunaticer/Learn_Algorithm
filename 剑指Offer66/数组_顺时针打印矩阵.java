/*
*题目描述
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
例如，如果输入如下4 X 4矩阵：
 1  2  3  4
 5  6  7  8
 9 10 11 12
13 14 15 16
则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
* */

import java.util.ArrayList;

/*
*题解:
* 算法
(模拟) O(n2)
我们顺时针定义四个方向：上右下左。
从左上角开始遍历，先往右走，走到不能走为止，然后更改到下个方向，再走到不能走为止，依次类推，遍历 n2 个格子后停止。

时间复杂度
矩阵中每个格子遍历一次，所以总时间复杂度是 O(n^2)。
* */
public class 数组_顺时针打印矩阵 {
    public ArrayList<Integer> printMatrix(int [][] matrix) {

        ArrayList<Integer> res = new ArrayList<>();

        if(matrix == null || matrix[0] == null)
            return res;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] visited = new int[m][n];//记录是否访问过

        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        int x = 0;
        int y = 0;
        int d = 1;

        for(int i = 0; i < m; i++){
            for(int j = 0; j<n; j++){
                res.add(matrix[x][y]);
                visited[x][y] =  1;
                int a = x+dx[d];
                int b = y+dy[d];
                if(a<0||a>=m||b<0||b>=n || visited[a][b]==1){
                    d = (d+1)%4;
                    a = x+dx[d];
                    b = y+dy[d];
                }
                x = a;
                y = b;
            }
        }
        return res;
    }
}
