/*
* 给定一个n个点m条边的有向图，图中可能存在重边和自环，所有边权均为正值。

请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出-1。

输入格式
第一行包含整数n和m。

接下来m行每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。

输出格式
输出一个整数，表示1号点到n号点的最短距离。

如果路径不存在，则输出-1。

数据范围
1≤n≤500,
1≤m≤10^5,
图中涉及边长均不超过10000。
* */

import java.util.Arrays;
import java.util.Scanner;

/*朴素的Dijkstra算法适用于稠密图，即边的数目>=定点数目的平方倍。本题数据范围是稠密图。对于稠密图，我们使用邻接矩阵来保存图。*/
/*Dijkstra算法是贪心算法，时间复杂度是O(n^2)*/
public class 朴素Dijkstra算法 {
    final int N = 510;
    int[][] g = new int[N][N];//邻接矩阵
    int[] dist = new int[N];//表示从起点走到每个点的距离是多少，起点是1号点。dist[2]表示从1号走到2号点的最短距离。
    boolean[] st = new boolean[N]; //表示从起点到每个点的最短路径是否已经确定。boolean数组默认初始化值为false。
    int n, m;//n表示节点数目，m表示边的数目
    //设为Integer.MAX_VALUE的一半（不是Integer.VALUE），因为我们后面计算中间经过某一点的路径时会用dist里面的值加上一个整数，若此处采用这个最大值，则后面的计算结果必然溢出，得到错误的答案。
    final int INF = Integer.MAX_VALUE>>1;

    //    初始化邻接矩阵
    public void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
//        将邻接数组的边的权重初始化为一个很大的值，表示初始时所有边都没有连接。这个很大的值在c++里面一般设为0x3f;在Java里面我们把他
        for (int i = 0; i < N; i++)
            Arrays.fill(g[i], INF);//Arrays.fill只能用来初始化一维数组，且内部也使用for循环实现的，并不能提高初始化效率

//        按边依次读入权重并保存到邻接矩阵中
        while (m > 0) {
            int a, b, c;
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            g[a][b] = Math.min(g[a][b], c);//根据题意有可能存在重复边，只保留权重最小的边。
            m--;
        }
    }

    //    dijkstra算法
    int dijkstra() {
        //首先将距离矩阵dist初始化为正无穷，表示起点到所有其他点的距离一开始都是正无穷；
        Arrays.fill(dist, INF);
        dist[1] = 0;//1号点作为起点，起点距离自己的距离是0；

        //迭代n次，每次迭代确定一个点到起点的最短路径, 两层for循环决定了其时间复杂度为O(n^2)。
        for (int i = 0; i < n; i++) {
            int t = 0;
            //从未确定到起点的最短路径集合里面，找到dist最小的那一个(也即离起点最近的那个), 根据贪心的原理, 这个点到原点的最小距离已经时确定的了
            //因为这个点的到起点有两种可能,一种是不经过剩下这些未确定最短路的点到起点的距离,也就是现在的dist[t];
            //一种是要经过剩下这些未确定最短路的点再到起点的距离.由于t在剩余的点里已经是到起点最近的了,所以不可能通过先经过其余的点再到t得到更短的距离
            //如果这个寻找最小dist的操作交给堆这样的数据结构来做，则能够把查找的时间复杂度从O(n)降低到log(m)
            for (int j = 1; j <= n; j++)
                if (!st[j] && dist[t] > dist[j])
                    t = j;

            //如果t = n,说明在某一步里面找到了到n的最短路径了,可以提前break终止循环.此处是一个优化,可以不写.
            if (t == n)
                break;

            //t到起点的最短路径已经确定，标记一下
            st[t] = true;
            //用已经确定到起点路径最短的t去更新其余未确定最短路的到起点的距离，看经过t到起点的距离比较小还是原来的距离比较小，用小的更新之。
            for (int j = 1; j <= n; j++)
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
        }

//        如果到最后n点的距离矩阵还是初始的Integer.MAX_VALUE>>1, 说明它没有被更新过, 也就是从起点到不了这个点.
       if (dist[n] == INF)
            return -1;
        return dist[n];
    }

    public static void main(String[] args) {
        朴素Dijkstra算法 s = new 朴素Dijkstra算法();
        s.initialization();
        int res = s.dijkstra();
        System.out.println(res);
    }
}
