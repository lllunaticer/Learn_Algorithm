package 最短路径算法模板;
/*
题目及评测链接:https://www.acwing.com/problem/content/854/
*给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
请你判断图中是否存在负权回路。
输入格式
第一行包含整数n和m。
接下来m行每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。
输出格式
如果图中存在负权回路，则输出“Yes”，否则输出“No”。
数据范围
1≤n≤2000,
1≤m≤10000,
图中涉及边长绝对值均不超过10000。
输入样例：
3 3
1 2 -1
2 3 4
3 1 -4
输出样例：
Yes
* */

/*
* 对SPFA求最短路径算法稍加改动:
* 1. 开始的时候要将所有的节点都添加进队列里面(因为图中的环有可能由起始点
* 并不能到达，如果只把起点像求单源最短路那样只把起点加入队列中，则只能判断
* 图中和起点有连接的部分是否有环，而那些跟起点不相连的部分的环不能被正确
* 检测出来)
* 2. 添加一个cnt[N]数组，cnt[i]表示当前i节点最短路径包含的边数，每次在更新
* dist数组的时候一起更新cnt数组:
* 如果从起点到x的最短路径需要更新成从起点经过t再到x，那么cnt[x]应该等于cnt[t]+1  （从起点到x经过的边数应该更新为从起点到t的边数+1）
* dist[x] = dist[t] + w[i];
* cnt[x] = cnt[t] + 1;
* 如果在这个更新的过程中某一次发生了cnt[x]>=n，则说明到某一点x的最短路径中经过了n条边，也就是n+1个节点，这说明图中出现了负环。
*
* */
import java.util.*;

public class SPFA算法判断有无负环{
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
    int[] cnt = new int[N];
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

    boolean spfa() {
        Queue<Integer> q = new LinkedList<>();
        //因为负环可能出现在起点不能连通到的区域中，所以需要将所有点都加入到队列中去查找。
        for(int i = 1; i<=n;i++){
            q.offer(i);
            st[i] = true;
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;//该点出队列了，则将标志位置为False

            //更新t的所有邻边
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    cnt[j] = cnt[t] + 1;
                    if(cnt[j]>=n)
                        return true;
                    if (!st[j]) {
                        q.offer(j);
                        st[j] = true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SPFA算法判断有无负环 s = new SPFA算法判断有无负环();
        s.initialization();
        if(s.spfa())
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}

