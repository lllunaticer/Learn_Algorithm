package 线性DP;
/*题目及评测链接:https://www.acwing.com/problem/content/900/
*给定一个如下图所示的数字三角形，从顶部出发，在每一结点可以选择移动至其左下方的结点或移动至其右下方的结点，
* 一直走到底层，要求找出一条路径，使路径上的数字的和最大。
        7
      3   8
    8   1   0
  2   7   4   4
4   5   2   6   5
输入格式
第一行包含整数n，表示数字三角形的层数。
接下来n行，每行包含若干整数，其中第 i 行表示数字三角形第 i 层包含的整数。
输出格式
输出一个整数，表示最大的路径数字和。
数据范围
1≤n≤500,
−10000≤三角形中的整数≤10000
输入样例：
5
7
3 8
8 1 0
2 7 4 4
4 5 2 6 5
输出样例：
30
难度：简单
* */

import java.util.Arrays;
import java.util.Scanner;

public class 数字三角形 {
    final int N = 510;
    int INF = Integer.MIN_VALUE / 2;//为防止溢出，负无穷取MIN_VALUE的一半
    int n;
    int[][] arr = new int[N][N]; //保存数字三角形的矩阵
    int[][] f = new int[N][N];   //状态矩阵

    void initialization() {
        //初始状态置为负无穷
        //注意在置初始状态的时候，初始的状态一定要比实际矩阵的大小大一圈，以便处理边界情况。
        //下面是将所有的f都初始化为负无穷
        for (int[] k : f)
            Arrays.fill(k, INF);

        //也可以按照输入三角形的大小来初始化f矩阵
/*        for(int i = 0; i<=n+1; i++){
            for(int j = 0; j <= i+1; j++)
                f[i][j] = INF;

                注意在这种情况下，f状态矩阵的初始化的范围要比arr三角形要大一圈，以处理边界情况。
                在f的初始化中， i 和 j是从0开始初始化的，且终止条件是i<=n+1和j<=i+1;
                而下面的arr三角形的读入中, i 和 j是从1开始的，且终止条件是i<=n和j<=i;
        }*/

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
    }

    /*
     * 从状态的计算过程看，这个解法是从f[1][1]开始计算，使用之前的f, 从上往下依次计算每个f[i][j]。
     * 这也是所谓的top-down(自顶向下)的计算方法。因为最长的结果有可能出现在最后一层的任意一个节点，所以最后需要遍历f[n][i]来找到符合要求的那一个。
     * 本题如果采用自底向上的计算方式，则最后直接返回f[1][1]就能得到最终结果。
     * */
    int dp_top_down() {
        f[1][1] = arr[1][1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                f[i][j] = Math.max(f[i - 1][j - 1] + arr[i][j], f[i - 1][j] + arr[i][j]);//状态转移方程
        }

        int res = INF;
        //最长路径的终点一定出现在三角形最底下一层，因此遍历最底下一层的f[i][j]找出最大的f[i][j]
        for (int i = 1; i <= n; i++)
            res = Math.max(res, f[n][i]);
        return res;
    }

    /*
     * 从底向上计算每个状态，最终的结果保存在f[1][1]中，无需遍历。
     * */
    int dp_bottom_up() {
        //首先要将最底层的所有f[n][i]都初始化为arr[n][i]
        for (int i = 1; i <= n; i++)
            f[n][i] = arr[n][i];

        //从倒数第二层开始依次往上层计算f[i][j]
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= i; j++)
                f[i][j] = Math.max(f[i + 1][j + 1] + arr[i][j], f[i + 1][j] + arr[i][j]);//状态转移方程
        }
        //最终的结果保存在f[1][1],省却了上面方法还需要搜索的烦恼
        return f[1][1];
    }

    public static void main(String[] args) {
        数字三角形 s = new 数字三角形();
        s.initialization();
        int res = s.dp_top_down();
        System.out.println(res);
    }
}
