package 背包问题;
/*
* 题目及评测链接:https://www.acwing.com/video/219/
* 有 N 种物品和一个容量是 V 的背包。
物品一共有三类：
第一类物品只能用1次（01背包）；
第二类物品可以用无限次（完全背包）；
第三类物品最多只能用 si 次（多重背包）；
每种体积是 vi，价值是 wi。
求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
输出最大价值。
输入格式
第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
si=−1 表示第 i 种物品只能用1次；
si=0 表示第 i 种物品可以用无限次；
si>0 表示第 i 种物品可以使用 si 次；
输出格式
输出一个整数，表示最大价值。
数据范围
0<N,V≤1000
0<vi,wi≤1000
−1≤si≤1000
输入样例
4 5
1 2 -1
2 4 1
3 4 0
4 5 2
输出样例：
8
* */


import java.util.*;

public class 混合背包问题 {
    final int N = 1010;//物品的种类
    int n, m;//n是一共有多少种物品，m是背包的体积
    int[] f = new int[N];

    class Thing {
        //kind表示物品属于哪一类， 等于-1表示属于0-1背包， 等于0表示属于完全背包，大于0表示属于多重背包
        int kind, v, w;//v表示物品体积， w表示物品价值

        Thing(int kind, int v, int w) {
            this.kind = kind;
            this.v = v;
            this.w = w;
        }
    }

    //things列表保存物品信息
    List<Thing> things = new ArrayList<>();

    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
//        读入物品，如果物品属于0-1背包(s=-1)或者属于完全背包(s=0)，则直接存入list作为一个物品
//        如果物品属于多重背包(s=1), 则将其二进制分组后每组当作一个物品存入list
        for (int i = 1; i <= n; i++) {
            int v, w, s;
            v = sc.nextInt();
            w = sc.nextInt();
            s = sc.nextInt();//s是物品种类
            //s<0是0-1背包，直接加入物品list
            if (s < 0)
                things.add(new Thing(-1, v, w));
                //s=0是完全背包，也直接加入物品list
            else if (s == 0)
                things.add(new Thing(0, v, w));
                //否则就是s>0,是完全背包问题，s表示该物品有多少个
            else {
                //如果是多重背包，按二进制分组，每组按0-1背包存入
                for (int k = 1; k <= s; k *= 2) {
                    //每组按0-1背包存入
                    things.add(new Thing(-1, k * v, k * w));
                    s -= k;
                }
                if (s > 0)
                    things.add(new Thing(-1, s * v, s * w));
            }

        }
    }

    int dfs() {
        for (Thing thing : things) {
            //如果物品是0-1背包，则更新一维状态数组要从大到小枚举
            if (thing.kind < 0)
                for (int j = m; j >= thing.v; j--)
                    f[j] = Math.max(f[j], f[j - thing.v] + thing.w);

                //否则就是完全背包问题，更新一维状态数组要按从小到大枚举
            else
                for (int j = thing.v; j <= m; j++)
                    f[j] = Math.max(f[j], f[j - thing.v] + thing.w);

        }
        return f[m];
    }

    public static void main(String[] args) {
        混合背包问题 s = new 混合背包问题();
        s.initialization();
        int res = s.dfs();
        System.out.println(res);
    }

}
