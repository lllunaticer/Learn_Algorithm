package 树形DP;
/*
* 题目及评测链接:https://www.acwing.com/problem/content/10/
*有 N 个物品和一个容量是 V 的背包。
物品之间具有依赖关系，且依赖关系组成一棵树的形状。如果选择一个物品，则必须选择它的父节点。
如下图所示：
         1
        / \
       2   3
     /  \
    4    5
如果选择物品5，则必须选择物品1和2。这是因为2是5的父节点，1是2的父节点。
每件物品的编号是 i，体积是 vi，价值是 wi，依赖的父节点编号是 pi。物品的下标范围是 1…N。
求解将哪些物品装入背包，可使物品总体积不超过背包容量，且总价值最大。
输出最大价值。
输入格式
第一行有两个整数 N，V，用空格隔开，分别表示物品个数和背包容量。
接下来有 N 行数据，每行数据表示一个物品。
第 i 行有三个整数 vi,wi,pi，用空格隔开，分别表示物品的体积、价值和依赖的物品编号。
如果 pi=−1，表示根节点。 数据保证所有物品构成一棵树。
输出格式
输出一个整数，表示最大价值。
数据范围
1≤N,V≤100
1≤vi,wi≤100
父节点编号范围：
内部结点：1≤pi≤N;
根节点 pi=−1;
输入样例
5 7
2 3 -1
2 2 1
3 5 1
4 7 2
3 6 2
输出样例：
11
* */

import java.util.*;

public class 有依赖的背包问题 {
    final int N = 110;
    int n, m;//n表示物品个数，m表示背包容量
    //定义邻接表, 使用邻接表来保存图(树是特殊的图，也可以使用邻接表来保存)
    int[] h = new int[N];
    int[] e = new int[N];
    int[] ne = new int[N];
    int idx = 0;
    //v,w数组用来保存每个物品的体积、价值信息
    int[] v = new int[N];//v[i]表示第i个物品的体积
    int[] w = new int[N];//w[i]表示第i个物品的价值
    //状态数组 f[i][j]表示从i的子树中选择物品且体积不超过j的最大价值。由题目的规则知
    //如果要选择i的子树则必须选择子树的根节点，也就是节点i必须选择。
    int[][] f = new int[N][N];

    int root;//用于记录树的父节点

    //邻接表添加出边的函数
    //采用头插法, 把b节点插入到以a为头节点的链表中。
    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx;
        idx++;
    }

    //读入数据
    private void initialization() {
        //初始化邻接表的头指针数组为-1
        Arrays.fill(h, -1);
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            int p;//保存第i个节点的父节点，如果遇到某个p = -1， 说明它是root，将其保存下来
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            p = sc.nextInt();
            //如果第i个节点没有父节点(p = -1),将其保存到root中
            if (p == -1) root = i;
                //否则将父子节点关系保存到邻接表中
            else
                add(p, i);//p是父节点，将节点i插入到p链的链表中
        }
    }

    //计算f[u][i]
    private void dfs(int u) {
        //遍历u的每个子节点
        for (int i = h[u]; i != -1; i = ne[i]) {
            int son = e[i];
            //f[u]由u的子节点计算而来，所以必须先计算出f[son];
            dfs(son);
            //枚举体积
            for (int j = m - v[u]; j >= 0; j--) {
                //枚举分组的决策（子状态可以用掉0-j的体积，每个体积都是一个分组，子状态只能从这些分组中选出使得f[u][j]最大的那个）
                for (int k = 0; k <= j; k++) {
                    f[u][j] = Math.max(f[u][j], f[u][j - k] + f[son][k]);
                }
            }
        }
        //对每个子节点来说，因为u必须选，故每个子节点状态必须要留出v[u]的空间
        for (int i = m; i >= v[u]; i--)
            f[u][i] = f[u][i - v[u]] + w[u];
        //如果子节点状态的空间不足v[u], 那么说明这个子状态是无效的，直接将这个子状态的最大价值置为0
        for (int i = 0; i < v[u]; i++)
            f[u][i] = 0;
    }

    int solve() {
        initialization();
        dfs(root);
        return f[root][m];
    }

    public static void main(String[] args) {
        有依赖的背包问题 s = new 有依赖的背包问题();
        int res = s.solve();
        System.out.println(res);
    }
}
