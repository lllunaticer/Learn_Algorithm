package 背包问题;
/*
* 题目及评测链接:https://www.acwing.com/problem/content/4/
* 有 N 种物品和一个容量是 V 的背包。
第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
输出最大价值。
输入格式
第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
输出格式
输出一个整数，表示最大价值。
数据范围
0<N,V≤100
0<vi,wi,si≤100
输入样例
4 5
1 2 3
2 4 1
3 4 3
4 5 2
输出样例：
10
* */

/*
 *
 * */

import java.util.Scanner;

public class 多重背包问题 {
    int N = 110;
    int n, m;//n代表物品编号
    int[] v = new int[N];
    int[] w = new int[N];
    int[] s = new int[N];

    int[][] f = new int[N][N];

    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 1; i <= n; i++) {
           v[i] = sc.nextInt();
           w[i] = sc.nextInt();
           s[i] = sc.nextInt();
        }
    }

    //朴素解法，与完全背包问题的朴素解法类似，使用三重循环，效率不高
    int dfs_row(){
        for(int i = 1; i<=n; i++){
            for(int j = 0; j<= m; j++){
                //与完全背包稍微不同的是，因为物品i的数目最多为s[i]个，故此处对k的约束还要加上k<=s[i]
                for(int k = 0; k <= s[i] && k*v[i] <= j; k++)
                    f[i][j] = Math.max(f[i][j],f[i-1][j-k*v[i]]+k*w[i]);
            }
        }
        return f[n][m];
    }

    public static void main(String[] args) {
        多重背包问题 s = new 多重背包问题();
        s.initialization();
        int res = s.dfs_row();
        System.out.println(res);
    }
}
