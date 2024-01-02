package 背包问题;
/*
* 题目及评测链接: https://www.acwing.com/problem/content/6/
* 有 N 种物品和一个容量是 V 的背包。
第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
输出最大价值。
输入格式
第一行两个整数，N，V (0<N≤1000, 0<V≤20000)，用空格隔开，分别表示物品种数和背包容积。
接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
输出格式
输出一个整数，表示最大价值。
数据范围
0<N≤1000
0<V≤20000
0<vi,wi,si≤20000
提示
本题考查多重背包的单调队列优化方法。
输入样例
4 5
1 2 3
2 4 1
3 4 3
4 5 2
输出样例：
10
* */


/*下列解法参考自:https://www.acwing.com/solution/acwing/content/1537/*/
/*时间复杂度O(NV), 比二进制优化还要更快一点*/
import java.util.Scanner;
public class 多重背包问题单调队列优化解法 {
    int item_number, package_volumn;
    int volumn, value, number;

    final int N = 20010;
    int[] dp = new int[N];
    int[] dp_prev = new int[N];
    int[] monotone_queue = new int[N];

    int dfs() {
        Scanner sc = new Scanner(System.in);
        item_number = sc.nextInt();
        package_volumn = sc.nextInt();
        for (int i = 0; i < item_number; ++i) {
            dp_prev = dp.clone();
            volumn = sc.nextInt();
            value = sc.nextInt();
            number = sc.nextInt();
            for (int j = 0; j < volumn; ++j) {
                int head = 0, tail = -1;
                for (int k = j; k <= package_volumn; k += volumn) {
                    if(head<=tail && (k-monotone_queue[head])/volumn>number)
                        head++;
                    if(head<=tail)
                        dp[k] = Math.max(dp[k],dp_prev[monotone_queue[head]] + (k - monotone_queue[head])/volumn*value);
                    while(head<=tail&&dp_prev[monotone_queue[tail]]-(monotone_queue[tail]-j)/volumn*value<=dp_prev[k]-(k-j)/volumn*value)
                        --tail;

                    monotone_queue[++tail] = k;
                }
            }
        }
        return dp[package_volumn];
    }

    public static void main(String[] args) {
        多重背包问题单调队列优化解法 s  = new 多重背包问题单调队列优化解法();
        int res = s.dfs();
        System.out.println(res);
    }
}
