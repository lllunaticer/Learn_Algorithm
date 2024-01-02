package 树形DP;

import java.util.Arrays;
import java.util.Scanner;

/*题目及评测链接:https://www.acwing.com/problem/content/287/
* Ural大学有N名职员，编号为1~N。
他们的关系就像一棵以校长为根的树，父节点就是子节点的直接上司。
每个职员有一个快乐指数，用整数 Hi 给出，其中 1≤i≤N。
现在要召开一场周年庆宴会，不过，没有职员愿意和直接上司一起参会。
在满足这个条件的前提下，主办方希望邀请一部分职员参会，使得所有参会职员的快乐指数总和最大，求这个最大值。
输入格式
第一行一个整数N。
接下来N行，第 i 行表示 i 号职员的快乐指数Hi。
接下来N-1行，每行输入一对整数L, K,表示K是L的直接上司。
最后一行输入0,0。
输出格式
输出最大的快乐指数。
数据范围
1≤N≤6000,
−128≤Hi≤127
输入样例：
7
1
1
1
1
1
1
1
1 3
2 3
6 4
7 4
4 5
3 5
0 0
输出样例：
5
* */
public class 没有上司的舞会 {
    final int N = 6010;

    int n;//节点数目
    int[] happy = new int[N];//快乐指数
    //定义邻接表
    int[] h = new int[N], e = new int[N], ne = new int[N];
    int idx = 0;
    //定义状态数组，f[i][0]表示从i节点的子树中选择但不选择i节点获得的最大快乐值
    //f[i][0]表示从i节点的子树中选择且选择i节点获得的最大快乐值
    int[][] f = new int[N][2];
    //has_father[i]标识第i个节点是否有父节点，定义这个数组的目的是为了找出树的root
    //因为题目的输入数据没有明确指出那个节点是父节点，所以在添加节点的时候，我们为每个节点
    //在这个数组中保存其是否有父节点。
    //最后遍历这个数组找出没有父节点的那个节点，则它是root.
    boolean[] has_father = new boolean[N];

    //为邻接表添加新的出边的函数，采用头插法, 把b节点插入到以a为头节点的链表中
    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx;
        idx++;
    }

    private void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        //读入每个节点的快乐值
        for (int i = 1; i <= n; i++)
            happy[i] = sc.nextInt();
        //初始化邻接链表的头指针为-1
        Arrays.fill(h, -1);
        //按照题目接下来有n-1条边，读入这些边加入邻接表
        for (int i = 1; i <= n - 1; i++) {
            int a, b;//按照题意b是a的直接上司
            a = sc.nextInt();
            b = sc.nextInt();
            has_father[a] = true;//按照题意b是a的直接上司, 故a存在父节点
            add(b, a);//将b插入到以a为头节点的链表中。
        }
    }

    //根据Overview.md中推导的状态转移方程计算f[u][1]和f[u][0]
    private void dfs(int u){
        f[u][1] = happy[u];//首先，f[u][1]标识从u的子树中选并选择u节点，故要先加上u的快乐值
        //根据状态转移方程，从u的子树中得到f[u][0]和f[u][1]
        //遍历子树
        for(int i = h[u]; i!=-1; i = ne[i]){
            int j = e[i];
            dfs(j);//想要使用f[j][0]和f[j][1]去计算f[s][0]和f[s][1], 必须先计算出f[j][0]和f[j][1]

            f[u][0] += Math.max(f[j][0],f[j][1]);
            f[u][1] += f[j][0];
        }
    }

    int solve(){
        //初始化读入数据
        initialization();
        //先查找树的root
        int root = 1;
        while(has_father[root])
            root++;
        dfs(root);
        return Math.max(f[root][0],f[root][1]);//返回根节点选或不选得到的最大快乐值
    }

    public static void main(String[] args) {
        没有上司的舞会 s = new 没有上司的舞会();
        int res = s.solve();
        System.out.println(res);
    }
}
