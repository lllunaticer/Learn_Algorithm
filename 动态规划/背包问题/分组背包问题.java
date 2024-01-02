package 背包问题;

/*题目及评测链接:https://www.acwing.com/problem/content/9/
* 有 N 组物品和一个容量是 V 的背包。
每组物品有若干个，同一组内的物品最多只能选一个。
每件物品的体积是 vij，价值是 wij，其中 i 是组号，j 是组内编号。
求解将哪些物品装入背包，可使物品总体积不超过背包容量，且总价值最大。
输出最大价值。
输入格式
第一行有两个整数 N，V，用空格隔开，分别表示物品组数和背包容量。
接下来有 N 组数据：
每组数据第一行有一个整数 Si，表示第 i 个物品组的物品数量；
每组数据接下来有 Si 行，每行有两个整数 vij,wij，用空格隔开，分别表示第 i 个物品组的第 j 个物品的体积和价值；
输出格式
输出一个整数，表示最大价值。
数据范围
0<N,V≤100
0<Si≤100
0<vij,wij≤100
输入样例
3 5
2
1 2
2 4
1
3 4
1
4 5
输出样例：
8
* */

import java.util.Scanner;

public class 分组背包问题 {
    final int N = 110;
    int n, m;
    int[][] v = new int[N][N];//v[i][k]表示第i组物品中的第k个物品的体积
    int[][] w = new int[N][N];//w[i][k]表示第i组物品中的第k个物品的价值
    int[] s = new int[N];//s[i]表示第i组物品的个数
    int[] f = new int[N];//一维状态向量

    void initializaiton() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            s[i] = sc.nextInt();
            for (int k = 0; k < s[i]; k++) {
                v[i][k] = sc.nextInt();
                w[i][k] = sc.nextInt();
            }
        }
    }

    //状态转移方程是f[i][j] = Math.max(f[i-1][j-v[i,k]]+w[i,k]) k = 0,1,2, ... 表示第i组物品的第k个物品
    //观察状态转移方程发现更新本层f[i][j]时使用的是上一层的状态f[i-1][j-v[j-v[i][k]]],故一维数组的更新方向是从右往左
    int dfs() {
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 0; j--)
                for (int k = 0; k < s[i]; k++)//枚举第i组物品的所有元素
                    //枚举到第i组元素的第k个物品的时候要求该元素体积小于背包体积才能被选用，即v[i][k]<=j
                    if (v[i][k] <= j)
                        f[j] = Math.max(f[j], f[j - v[i][k]] + w[i][k]);
        }
        return f[m];
    }

    public static void main(String[] args) {
        分组背包问题 s = new 分组背包问题();
        s.initializaiton();
        int res = s.dfs();
        System.out.println(res);
    }
}
