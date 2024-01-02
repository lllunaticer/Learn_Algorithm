/*
* 题目及评测链接:https://www.acwing.com/problem/content/2/
* 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
第 i 件物品的体积是 vi，价值是 wi。
求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
输出最大价值。
输入格式
第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。
输出格式
输出一个整数，表示最大价值。
数据范围
0<N,V≤1000
0<vi,wi≤1000
输入样例
4 5
1 2
2 4
3 4
4 5
输出样例：
8
* */

/*
 * */
package 背包问题;

import java.util.Scanner;

public class _01背包问题 {
    final int N = 1010;//物品的数目

    int n, m;//n表示物品数目，m表示背包容量
    int[] v = new int[N];//v[i] 表示第 i 个物品的体积
    int[] w = new int[N];//w[i] 表示第 i 个物品的价值
    //二维矩阵解法需要用到的状态矩阵
    int[][] f = new int[N][N];//f[i][j] 表示f(i,j)
    //一维矩阵解法需要用到的状态矩阵
    int[] g = new int[N];

    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();//物品数目
        m = sc.nextInt();//背包体积
        //物品编号从下标1开始，保存在数组中也从1开始
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
    }

    //二维解法, 效率比较低，但是是理解解题思路的基础
    int dfs_2D() {
//        f[0][0~m]表示在前0个物品中选，总体积不超过0~m的的选法中，价值的最大值。此时的价值显然为0,数组默认初始化为0，所以直接从i = 1开始遍历即可
//        for (int i = 0; i <= m; i++)
//            f[0][i] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
               f[i][j] = f[i-1][j];
               if(j>=v[i]) f[i][j] = Math.max(f[i][j],f[i-1][j-v[i]]+w[i]);
            }
        }
        return f[n][m];
    }

    //将上面二维解法进行优化的解法，只需用到一维的矩阵，效率更高。（滚动数组的思想）
    int dfs_1D(){
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= v[i]; j--) {
                g[j] = Math.max(g[j],g[j-v[i]]+w[i]);
            }
        }
        return g[m];
    }
    public static void main(String[] args) {
        _01背包问题 s = new _01背包问题();
        s.initialization();
        int res_2d = s.dfs_2D();
        int res_1d = s.dfs_1D();
        System.out.println(res_2d);
        System.out.println(res_1d);
    }
}
