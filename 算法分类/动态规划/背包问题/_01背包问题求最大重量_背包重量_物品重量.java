package 动态规划.背包问题;

/*
 * 假设背包的最大承载重量是 9。有5个不同的物品，每个物品的重量分别是 2，2，4，6，3。
 * 问如何选择物品使得背包装下尽可能多的物品？
 * */
public class _01背包问题求最大重量_背包重量_物品重量 {

    static int maxW = Integer.MIN_VALUE;//记录最大结果
    static int[] weight = {2, 2, 4, 6, 3};//物品重量
    static int n = 5;//物品个数
    static int w = 9;//背包承受的最大重量

    // 回溯算法。
    public static void beibao_solution0(int i, int cw) {//调用 beibao_solution0(0,0)
        if (cw == w || i == n) {// cw==w 表示装满了，i==n 表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        beibao_solution0(i + 1, w);// 选择不装第 i 个物品，
        if (cw + weight[i] <= w)
            beibao_solution0(i + 1, weight[i]);// 选择装第 i 个物品
    }

    //备忘录法，避免重复计算状态
    //// 备忘录，默认值 false
    static boolean[][] mem = new boolean[5][10];

    public static void beibao_solution1(int i, int cw) {
        if (cw == w || i == n) {
            if (cw > maxW)
                maxW = cw;
            return;
        }
        if (mem[i][cw]) return;
        mem[i][w] = true;
        beibao_solution1(i + 1, w);
        if (cw + weight[i] <= w)
            beibao_solution1(i + 1, cw + weight[i]);
    }

    //push型转移动态规划-极客时间上写法
    /*
     * 设计DP算法，往往可以遵循DP三连：
     * 　　我是谁？  ——设计状态，表示局面
     * 　　我从哪里来？
     * 　　我要到哪里去？  ——设计转移
     * 　　设计状态是DP的基础。接下来的设计转移，有两种方式：一种是考虑我从哪里来（本文之前提到的两个例子，都是在考虑“我从哪里来”）；
     * 另一种是考虑我到哪里去，这常见于求出f(x)之后，更新能从x走到的一些解。这种DP也是不少的，我们以后会遇到。
     * 　　总而言之，“我从哪里来”和“我要到哪里去”只需要考虑清楚其中一个，就能设计出状态转移方程，从而写代码求解问题。
     * 前者又称pull型的转移，后者又称push型的转移。
     * */
    static boolean[][] states = new boolean[n][w + 1];

    public static int beibao_solution2() {
//        第一行的数据需要特殊处理
        states[0][0] = true;
        states[0][weight[0]] = true;
        for (int i = 1; i < n; ++i) {//动态规划状态转移
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j] == true) {//不把第i个物品放入背包
                    states[i][j] = true;
                    if(j<=w-weight[i]){//把第i个物品放入背包
                        states[i][j+weight[i]]=true;
                    }
                }
            }
//            for (int j = 0; j <= w - weight[i]; ++j) {
//                if (states[i - 1][j] == true)
//                    states[i][j + weight[i]] = true;
//            }
        }
        for (int i = w; i >= 0; --i) {//输出结果
            if (states[n - 1][i] == true)
                return i;
        }
        return 0;
    }

    /*
    * 用滚动数组实现的动归，节约额外空间
    * */




    public static void main(String[] args) {
        beibao_solution0(0, 0);
        System.out.println(maxW);
        maxW = Integer.MIN_VALUE;
        beibao_solution1(0, 0);
        System.out.println(maxW);
        System.out.println(beibao_solution2());
    }

}
