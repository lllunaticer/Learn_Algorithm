package 背包问题;
/*题目及评测链接:https://www.acwing.com/problem/content/8/*/
/*
*有 N 件物品和一个容量是 V 的背包，背包能承受的最大重量是 M。
每件物品只能用一次。体积是 vi，重量是 mi，价值是 wi。
求解将哪些物品装入背包，可使物品总体积不超过背包容量，总重量不超过背包可承受的最大重量，且价值总和最大。
输出最大价值。
输入格式
第一行两个整数，N，V,M，用空格隔开，分别表示物品件数、背包容积和背包可承受的最大重量。
接下来有 N 行，每行三个整数 vi,mi,wi，用空格隔开，分别表示第 i 件物品的体积、重量和价值。
输出格式
输出一个整数，表示最大价值。
数据范围
0<N≤1000
0<V,M≤100
0<vi,mi≤100
0<wi≤1000
输入样例
4 5 6
1 2 3
2 4 4
3 4 5
4 5 6
输出样例：
8
* */

import java.util.Scanner;

public class 二维费用背包问题 {
    final int N = 110;

    int n, v, m;//n是物品个数， v是背包可容纳体积， m是背包可承受重量
    int[][] f = new int[N][N];

    int dfs() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        v = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;//a表示物品体积，b表示物品重量，c表示物品价值
        for (int i = 1; i <= n; i++) {
            a = sc.nextInt();//物品体积
            b = sc.nextInt();//物品重量
            c = sc.nextInt();//物品价值
            for(int j = v; j>=a; j--){
                for(int k = m; k>=b; k--)
                    f[j][k] = Math.max(f[j][k], f[j-a][k-b]+c);
            }
        }
        return f[v][m];
    }

    public static void main(String[] args) {
        二维费用背包问题 s = new 二维费用背包问题();
        int res = s.dfs();
        System.out.println(res);
    }
}
