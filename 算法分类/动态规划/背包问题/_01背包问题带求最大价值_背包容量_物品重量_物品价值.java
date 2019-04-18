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
    public static int knapsack3() {
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

    //使用一维数组优化空间
    /*
    * 空间优化，每一次f(i)(j)改变的值只与f(i-1)(x) {x:1...j}有关，f(i-1)(x)是前一次i循环保存下来的值；

　　因此，可以将f缩减成一维数组，从而达到优化空间的目的，状态转移方程转换为 B(j)= max{B(j), B(j-w(i))+v(i)}；

　　并且，状态转移方程，每一次推导V(i)(j)是通过V(i-1)(j-w(i))来推导的，所以一维数组中j的扫描顺序应该从大到小(capacity到0)，
    否者前一次循环保存下来的值将会被修改，从而造成错误。

    01背包问题关于空间优化的讨论:
    https://blog.csdn.net/baiyifeifei/article/details/81355860
    * */
    public static int knapsack4(){
        int[] f = new int[n];//f[i]表示体积为i的时候的最大价值
        for(int i = 1; i<=n;i++){
            for(int j = w;j>=value[i];j--){
                f[j] = Math.max(f[j],f[j-weight[i]]+value[i]);
            }
        }
        return f[n-1];
    }

    //测试
    public static void main(String[] args) {
        knapsack1(0, 0, 0);
        System.out.println(maxV);
        System.out.println(knapsack2());
        System.out.println(knapsack3());
    }
}

