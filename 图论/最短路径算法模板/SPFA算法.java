package 最短路径算法模板;
/*
题目及评测链接:https://www.acwing.com/problem/content/853/
给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出impossible。
数据保证不存在负权回路。
输入格式
第一行包含整数n和m。
接下来m行每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。
输出格式
输出一个整数，表示1号点到n号点的最短距离。
如果路径不存在，则输出”impossible”。
数据范围
1≤n,m≤105,
图中涉及边长绝对值均不超过10000。
输入样例：
3 3
1 2 5
2 3 -3
1 3 4
输出样例：
2*/


import java.util.*;

public class SPFA算法 {
    final int N = 100010;//由数据量决定；
    int n, m, idx;//n是节点数目，m是边的数目，idx是边的编号。
    //邻接表的数据结构
    //关于如何用数组表示邻接表，参见https://bbs.codeaha.com/thread-4612-1-1.html
    int[] h = new int[N];
    int[] w = new int[N];
    int[] e = new int[N];
    int[] ne = new int[N];
    //dist用来保存起点到其余其他节点的最短距离。st用来标识某节点的最短距离是否已经确定。
    int[] dist = new int[N];
    boolean[] st = new boolean[N];

    final int INF = Integer.MAX_VALUE >> 1;

    //为邻接表添加一条数据的模板
    void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        Arrays.fill(h, -1);
        while (m > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            add(a, b, c);
            m--;
        }
    }

    int spfa() {
        Arrays.fill(dist, INF);
        dist[1]=0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        st[1] = true;//st数组用来判断当前的点在不在队列当中，防止存储重复的点
        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;//该点出队列了，则将标志位置为False

            //更新t的所有邻边
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    if (!st[j]) {
                        q.offer(j);
                        st[j] = true;
                    }
                }
            }
        }
        if (dist[n] == INF)
            return -1;
        return dist[n];
    }

    public static void main(String[] args) {
        SPFA算法 s = new SPFA算法();
        s.initialization();
        int res = s.spfa();
        if (res == -1)
            System.out.println("impossible");
        else
            System.out.println(res);
    }
}
