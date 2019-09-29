package 背包问题;

import java.util.Scanner;

/*
*题目及评测链接： https://www.acwing.com/problem/content/5/
*
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
0<N≤1000
0<V≤2000
0<vi,wi,si≤2000
提示：
本题考查多重背包的二进制优化方法。
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
 * 二进制优化版本的多重背包问题解法
 * */
public class 多重背包问题二进制优化解法 {
    //本题的数据量是N = 1000, V = 2000, S = 2000。每个物品按最大的S=2000计算，则每个物品可以
    //分成logS约等于22组, 一共有N=1000种物品，故一共有约22000组。这里为了冗余，将N取为25000
    final int N = 25000, M = 2010;
    int n, m;
    int[] v = new int[N];
    int[] w = new int[N];
    int[] f = new int[N];

    //初始化，在初始化的时候就对每个物品分组，分到同一组的物品统计他们的总重量W、总价值W作为一个新的物品存入数组,
    //作为0-1背包处理的输入。
    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, s;
        int cnt = 0;//cnt作为新的物品编号的起始位置
        for (int i = 1; i <= n; i++) {
             a = sc.nextInt();//第i个物品的体积
             b = sc.nextInt();//第i个物品的价值
             s = sc.nextInt();//第i个物品的数目

            int k = 1;//分组从k = 1 开始， 1，2，4，... ，
            while (k <= s) {
                cnt++;
                v[cnt] = a * k;//将这属于同一组的物品的总重量作为新物品的重量
                w[cnt] = b * k;//将这属于同一组的物品的总重量作为新物品的重量
                s -= k;
                k *= 2;
            }
            //若s在1，2，4，8, ... , 分组后还有剩余， 则将剩下的分为一组
            if(s>0){
                cnt++;
                v[cnt] = a * s;
                w[cnt] = b * s;
            }
        }
        n = cnt;//不要忘了将实际分出来的总的组数赋给n
    }

    //按0-1背包处理分好组的数据
    int dfs(){
        for(int i = 1; i<=n; i++){
            for(int j = m; j >= v[i]; j--){
                f[j] = Math.max(f[j],f[j-v[i]]+w[i]);
            }
        }
       return f[m];
    }

    public static void main(String[] args) {
        多重背包问题二进制优化解法 s = new 多重背包问题二进制优化解法();
        s.initialization();
        int res = s.dfs();
        System.out.println(res);
    }
}
