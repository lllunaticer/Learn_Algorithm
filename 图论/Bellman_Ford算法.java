/*
题目及评测连接:https://www.acwing.com/problem/content/855/
给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
请你求出从1号点到n号点的最多经过k条边的最短距离，如果无法从1号点走到n号点，输出impossible。
注意：图中可能 存在负权回路 。
输入格式
第一行包含三个整数n，m，k。
接下来m行，每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。
输出格式
输出一个整数，表示从1号点到n号点的最多经过k条边的最短距离。
如果不存在满足条件的路径，则输出“impossible”。
数据范围
1≤n,k≤500,
1≤m≤10000,
任意边长的绝对值不超过10000。
输入样例：
3 3 1
1 2 1
2 3 1
1 3 3
输出样例：
3*
* */

import java.util.Arrays;
import java.util.Scanner;

/*
 * Bellman-Ford算法算法流程如下:
 * //循环所有点，复杂度O（n）
 * for i = 1 ~n
 * // 每次内循环访问所有边, 复杂度O(m)
 *   for 所有边 a,b,w     (表示从a到b有一条权重为w的边)
 *       dist[b] = Math.min(dist[b], dist[a] + w)
 *
 *  整个的算法流程就是上述的两重循环, 可以看出，Bellman-Ford算法的时间复杂度为O(mn)。
 *  Bellman-Ford算法计算完成之后，能够保证对于所有的a,b,w都有dist[a]<=dist[b]+w, 此条件称为三角不等式
 *  Bellman-Ford算法的更新操作 dist[b] = Math.min(dist[b], dist[a] + w)被称为松弛操作。
 *
 *  另外，由于Bellman-Ford算法每次都要遍历所有的边，且遍历的顺序不限，所以在保存数据的时候可以采用最直接的方式：
 *  定义一个类来保存一条边的起点、终点、权重，然后把所有的边都读入一个边类的数组中保存即可。
 *
 *  Bellman-Ford算法的外层迭代次数是有实际意义的
 *  for i = 1~n 当 i 迭代到等于k的时候， 此时dist数组中每个元素的含义是 从起点 经过不超过k条边走到每个点的最短距离。
 *  利用这一特性，我们可以处理那些要求最短路径最多经过k个点的问题。
 *
 *  另外用这一特性还能检测图中是否有负环。
 *  当i = n时，如果还有更新操作发生, 说明从起点到某个点的最短距离经过了n条边, 这条路径上一共出现了n+1个节点。根据抽屉
 *  原理，说明这条边上出现了重复节点，也就是出现了环。且此重复节点的出现比不出现能缩短最短路径，根据松弛条件，我们可以推断
 *  该环是一个负环。
 * */
public class Bellman_Ford算法 {
    final int N = 510, M = 10010;
    final int INF = Integer.MAX_VALUE >> 1;
    int n, m, k;
    int[] dist = new int[N];
    int[] backup = new int[N];

    class Edge {
        int a, b, w;

        Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    Edge[] edges = new Edge[M];

    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(a, b, w);
        }
    }

    int bellman_ford() {
        Arrays.fill(dist, INF);
        dist[1] = 0;
        for (int i = 0; i < k; i++) {
            backup = dist.clone();//备份dist数组
            for (int j = 0; j < m; j++) {
                int a = edges[j].a, b = edges[j].b, w = edges[j].w;
                dist[b] = Math.min(dist[b], backup[a] + w);
            }
        }
        if (dist[n] > (INF >> 1))
            return -1;
        return dist[n];
    }

    public static void main(String[] args) {
        Bellman_Ford算法 b = new Bellman_Ford算法();
        b.initialization();
        int res = b.bellman_ford();
        if (res == -1)
            System.out.println("impossible");
        else
            System.out.println(res);
    }


}
