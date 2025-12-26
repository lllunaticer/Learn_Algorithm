import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 从 1~n 这 n 个整数中随机选取任意多个，输出所有可能的选择方案。

输入格式
输入一个整数n。

输出格式
每行输出一种方案。

同一行内的数必须升序排列，相邻两个数用恰好1个空格隔开。

对于没有选任何数的方案，输出空行。

本题有自定义校验器（SPJ），各行（不同方案）之间的顺序任意。

数据范围
1≤n≤15
输入样例：
3
输出样例：

3
2
2 3
1
1 3
1 2
1 2 3*/
//来源:https://www.acwing.com/problem/content/94/
public class 递归实现指数型枚举 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(bf.readLine());

        dfs(0, 0);
    }

    static void dfs(int u, int state) {//使用位运算来储存结果
        if (u == n) {
            for (int i = 0; i < n; i++) {
                if(((state >> i) & 1) == 1)//将state右移i位与1， 结果为1说明state的第i为是1
                    System.out.print(i+1+" ");
            }
            System.out.println();
            return;
        }
        dfs(u+1, state);//递归处理第u+1位，不选择第u位；
        dfs(u+1, state | 1 << u);//递归处理第u+1位，选择第u位；state | 1 << u 是把state的第u位置为1，表示我们要选用这个数
    }
}
