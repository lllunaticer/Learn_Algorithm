package 搜索与遍历_BFS_DFS_树和图的遍历.BFS;
//题目及评测链接https://acm.ecnu.edu.cn/problem/1224/
/*
* 一天，sunny 不小心进入了一个迷宫，不仅很难寻找出路，而且有的地方还有怪物，但是 sunny 有足够的能力杀死怪物，但是需要一定的时间，但是 sunny 想早一点走出迷宫，所以请你帮助他计算出最少的时间走出迷宫，输出这个最少时间。
我们规定每走一格需要时间单位 1, 杀死怪物也需要时间 1, 如果不能走到出口，则输出 impossible. 每次走只能是上下左右 4 个方向。
输入格式
每次首先 2 个数 n,m (0<n,m≤200)，代表迷宫的高和宽，然后 n 行，每行 m 个字符。
S 代码你现在所在的位置。
T 代表迷宫的出口。
# 代表墙，你是不能走的。
X 代表怪物。
. 代表路，可以走。
处理到文件结束。
输出格式
输出最少的时间走出迷宫。不能走出输出 impossible。

样例
input
4 4
S.X.
#..#
..#.
X..T
4 4
S.X.
#..#
..#.
X.#T
output
6
impossible
* */
import java.util.PriorityQueue;
import java.util.Scanner;
public class 走迷宫升级版_边的权值不同 {
    final int N = 210;
    char[][] maze = new char[N][N];

    int sx, sy, tx, ty;//(sx,sy)是起点坐标，(tx, ty)是出口坐标
    int n, m;

    //使用二维数组q[i][3]表示每个节点的距离信息，q[i][0] = i点的横坐标， q[i][1] = i点的纵坐标， q[i][2] = i点到起点的距离
    //把所有找到的节点以上述二维数组的形式保存进优先队列，优先队列的比较标准是按q[i][2]也就是节点到起点的距离从小到达排序。

    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();//读掉本行的空格
        String str;
        for (int i = 0; i < n; i++) {
            str = sc.nextLine();
            for (int j = 0; j < m; j++) {
                char c = str.charAt(j);
                maze[i][j] = c;
                if (c == 'S') {
                    sx = i;
                    sy = j;
                }
                if (c == 'T') {
                    tx = i;
                    ty = j;
                }
            }
        }
    }

    void bfs() {
        PriorityQueue<int[]> que = new PriorityQueue<>((int[] o1, int[] o2) -> o1[2] - o2[2]);
        int[] st = {sx, sy, 0};//搜索的起点，前两个元素是起点坐标，第三个元素是到起点的距离
        maze[sx][sy] = '#'; //把起点标记为已经走过，不能再走
        que.offer(st);//把起点加入优先队列
        //遍历用的向量
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!que.isEmpty()) {
            int[] p = que.poll();
            if (p[0] == tx && p[1] == ty) {
                System.out.println(p[2]);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int x = p[0] + dx[i];
                int y = p[1] + dy[i];
                if (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] != '#') {
                    if (maze[x][y] == 'X')
                        //如果(x,y)处是怪物，则此处到(0,0)的距离等于(0,0)先到(p[0],p[1])的距离(也就是p[2])加上2；
                        que.offer(new int[]{x, y, p[2] + 2});
                    else
                        //如果(x,y)处是路，则此处到(0,0)的距离等于(0,0)先到(p[0],p[1])的距离(也就是p[2])加上1；
                        que.offer(new int[]{x, y, p[2] + 1});
                    maze[x][y] = '#';//走过的路标记为墙不能再走
                }
            }
        }
        System.out.println("impossible");
    }

    public static void main(String[] args) {
        走迷宫升级版_边的权值不同 s = new 走迷宫升级版_边的权值不同();
        s.initialization();
        s.bfs();
    }
}
