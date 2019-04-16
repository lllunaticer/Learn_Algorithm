package 动态规划;
/*
* 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
 

思路：
状态方程为：

S[i][j]=A[i][j] + min(S[i-1][j], if i>0 ; S[i][j-1], if j>0)
注意不要让矩阵的系数为负，还有二维的vector的初始化
https://blog.csdn.net/m0_37561165/article/details/81110413
* */

/*
* 思路：

使用动态规划，定义 dp[M][N] , M ,N 分别代表矩阵的行和列数 dp[i][j]
表示从左上角到矩阵（i，j）位置是的最短路径和。则可知 到（i，j）位置有
两种情况：1）由（i-1，j）向下走，2）由（i，j-1）向右走，所以dp[i][j]
=Math.min（dp[i-1][j],dp[i][j-1]）+m[i][j];对于dp[0][j] 只能由
dp[0][j-1] 向右走，dp[i][0] 只能由 dp[i-1][0] 向下走。所以 dp[0][j]
=dp[0][j-1]+m[0][j], dp[i][0]=dp[i-1][0]+m[i][0].

原文：https://blog.csdn.net/u013309870/article/details/69569456

* */
public class 最小路径和_动态规划_leetcode64 {
    public int minPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1; i<m;i++)
            //第一列只能由上向下
            dp[i][0] = dp[i-1][0]+grid[i-1][0];

        for(int j = 1; j<n;j++)
            //第一行只能由左向右
            dp[0][j] = dp[0][j-1]+grid[0][j-1];

        for(int i =1;i<m;i++){
            for(int j = 1; j<n;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }

}
