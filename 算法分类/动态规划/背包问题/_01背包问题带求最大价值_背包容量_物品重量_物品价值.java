package 动态规划.背包问题;

public class _01背包问题带求最大价值_背包容量_物品重量_物品价值 {
    /*
     * 我们刚刚讲的背包问题，只涉及背包重量和物品重量。我们现在引入物品价值这一变量。
     * 对于一组不同重量、不同价值、不可分割的物品，我们选择将某些物品装入背包，
     * 在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大是多少呢？
     * */

    //一、极客时间解法

    //1. 回溯方法（使用递归）
    static private int maxV = Integer.MIN_VALUE; // 结果放到 maxV 中
    static private int[] weight = {2, 2, 4, 6, 3};  // 物品的重量
    static private int[] value = {3, 4, 8, 9, 6}; // 物品的价值
    static private int n = 5; // 物品个数
    static private int w = 9; // 背包承受的最大重量

    static public void knapsack1(int i, int cw, int cv) { // 调用 f(0, 0, 0)
        if (cw == w || i == n) {// cw==w 表示装满了，i==n 表示物品都考察完了
            if (maxV < cv)
                maxV = cv;
            return;
        }
        knapsack1(i + 1, cw, cv);// 选择不装第 i 个物品
        if (cw + weight[i] <= w) {// 选择装第 i 个物品
            knapsack1(i + 1, cw + weight[i], cv + value[i]);
        }
    }

    //2. 动态规划方法
    public static int knapsack2() {
        int[][] states = new int[n][w+1];
        for (int i = 0; i < n; ++i) { // 初始化 states
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][weight[0]] = value[0];
        for (int i = 1; i < n; ++i) { // 动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第 i 个物品
                if (states[i-1][j] >= 0) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) { // 选择第 i 个物品
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+weight[i]]) {
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n-1][j] > maxvalue) maxvalue = states[n-1][j];
        }
        return maxvalue;
    }



    //二、大雪菜B站背包九讲解法
    //
    static public int knapsack3() {
        int[][] f = new int[n][w + 1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                f[i][j] = f[i - 1][j];//不取第i个物品，则f[i][j] = f[i - 1][j];
                if (j >= weight[i])//如果选第i个物品，首先要满足背包装得下的条件
                    //如果选第i个物品，则f[i][j] = f[i - 1][j - weight[i]] + value[i]
                    //然后最后的f[i][j]要在选和不选第i个物品得到的价值中取较大的那一个
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - weight[i]] + value[i]);
            }
        }
        int res = 0;
        for (int i = 0; i <= w; i++)
            res = Math.max(res, f[n - 1][i]);
        return res;
    }

    //测试
    public static void main(String[] args) {
        knapsack1(0, 0, 0);
        System.out.println(maxV);
        System.out.println(knapsack2());
        System.out.println(knapsack3());
    }
}

