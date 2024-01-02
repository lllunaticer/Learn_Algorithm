package 最短路径算法模板;
/*
题目及评测链接:https://www.acwing.com/problem/content/852/
*给定一个n个点m条边的有向图，图中可能存在重边和自环，所有边权均为正值。
请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出-1。

输入格式
第一行包含整数n和m。
接下来m行每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。

输出格式
输出一个整数，表示1号点到n号点的最短距离。
如果路径不存在，则输出-1。

数据范围
1≤n,m≤10^5,
图中涉及边长均不超过10000。

输入样例：
3 3
1 2 2
2 3 1
1 3 4
输出样例：
3
* */

/*
 * 堆优化版本的Dijkstra算法适用于稀疏图 -- 边的数目 < 节点数目的平方倍, 观察本题的数据范围，发现结点数n和边数n
 * 是同一数量级, 故该图是稀疏图， 适用堆优化的Dijkstra算法。稀疏图的存储方式一般采用邻接表的形式。
 * 算法的时间复杂度是O(mlogn), m是边的数目, n是节点的数目
 * */

/*
 * 研究朴素版的Dijkstra算法，我们可以发现其有两层循环，第一层循环for i = 1 ~ n，时间复杂度为O(n)
 * 这层循环里面需要从剩余的节点里面找出离起点距离最小的那个，这个操作的时间复杂度是O(n)。找最小的数，
 * 可以使用堆来将这个操作优化到log(n)级别。
 * */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 堆优化版本的Dijkstra算法 {
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

    //为邻接表添加一条数据的模板。添加一条a-->b的边， 权重是c.新建一个节点idx，节点的值e[idx] = b, 节点的权重w[idx] = c, 节点的下个节点指针指向以a为头节点的那个链表的头节点的下一个节点，然后把以头节点指向idx.
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

    int dijkstra() {
        //这个小根堆的作用是：
        //小根堆的每个元素是一个保存两个整数的数组int[] a，a[0]保存的是节点编号，a[1]保存的是这个节点到起点的距离。
        //小根堆的比较规则定义成(a,b)->a[1]-b[1]，即小根堆的大小比较规则是按照节点到起点的距离来排序。
        //优先队列中最多只有m条边，故查找一个节点的时间复杂度是O(logm)
        PriorityQueue<int[]> pq = new PriorityQueue<>(n, (a, b) -> a[1] - b[1]);
        Arrays.fill(dist, INF);//初始的时候，所有节点到起点的距离都是INF
        pq.offer(new int[]{1, 0});//1号节点是起点，起点到自己的距离为0
        dist[1] = 0;//1号节点的最短距离确定
        while (!pq.isEmpty()) {//开始遍历所有节点
            int[] cur = pq.poll();//从堆中取出堆顶元素，也就是剩余未确定最短距离的节点中，离起点最近的那个节点。时间复杂度未O(logn)
            int ver = cur[0];
            int distance = cur[1];
            if (st[ver]) continue;
            st[ver] = true;
            //遍历邻接表。h中存的是某条链的开头，ne数组链式保存这条链
            for (int i = h[ver]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > distance + w[i]) {
                    dist[j] = distance + w[i];
                    pq.offer(new int[]{j, dist[j]});
                }
            }
        }
        if (dist[n] == INF)
            return -1;
        return dist[n];
    }

    public static void main(String[] args) {
        堆优化版本的Dijkstra算法 s = new 堆优化版本的Dijkstra算法();
        s.initialization();
        int res = s.dijkstra();
        System.out.println(res);
    }
}
