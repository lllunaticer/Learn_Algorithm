package 搜索与遍历_BFS_DFS_树和图的遍历.BFS;
//题目及评测链接 ：https://www.acwing.com/problem/content/846/
/*
*
给定一个n*m的二维整数数组，用来表示一个迷宫，数组中只包含0或1，其中0表示可以走的路，1表示不可通过的墙壁。
最初，有一个人位于左上角(1, 1)处，已知该人每次可以向上、下、左、右任意一个方向移动一个位置。
请问，该人从左上角移动至右下角(n, m)处，至少需要移动多少次。
数据保证(1, 1)处和(n, m)处的数字为0，且一定至少存在一条通路。
输入格式
第一行包含两个整数n和m。
接下来n行，每行包含m个整数（0或1），表示完整的二维数组迷宫。
输出格式
输出一个整数，表示从左上角移动至右下角的最少移动次数。
数据范围
1≤n,m≤100
输入样例：
5 5
0 1 0 0 0
0 1 0 1 0
0 0 0 0 0
0 1 1 1 0
0 0 0 1 0
输出样例：
8* */
import java.util.Arrays;
import java.util.Scanner;
public class 走迷宫 {
    final int N = 110;

    int n, m;
    int[][] g = new int[N][N];//保存地图
    int[][] d = new int[N][N];//g[i][j]表示从位置(i,j)到起点(0,0)的距离

    //用数组模拟队列，这个队列的每一行是一个元素，每个元素是一个长度为2的数组，用于保存某个位置的横纵坐标(i,j)
    //如q[0]是队列的第一个元素，他保存了一个位置的横纵坐标(i,j)。这个元素又有两个子元素:q[0][0]=i保存横坐标，q[0][1]=j保存纵坐标
    int[][] q = new int[N * N][2];

    void initilizaiton() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                g[i][j] = sc.nextInt();
    }

    int bfs() {
        int hh = 0;//hh是队列头部的位置，一开始为0；
        int tt = 0;//tt是队列尾部的位置，一开始也为0；
        //将距离矩阵d初始化为-1
        for (int[] row : d)
            Arrays.fill(row, -1);
        d[0][0] = 0;//起点距离自己的位置为0

        //遍历上下左右四个方向的常用方法，定义四个向量
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        q[tt++] = new int[]{0, 0};//把起点放入队列
        //只要队列不为空
        while (hh <= tt) {
            int[] t = q[hh++];//取出队头元素;
            //遍历t位置的上下左右四个方向
            for (int i = 0; i < 4; i++) {
                int x = t[0] + dx[i], y = t[1] + dy[i];
                //前四个是边界条件，g[x][y]=0表示此处可通行，d[x][y]=-1表示此处没走过
                if (x >= 0 && x < n && y >= 0 && y < m && g[x][y] == 0 && d[x][y] == -1) {
                    d[x][y] = d[t[0]][t[1]] + 1;//(x,y)是从(t[0],t[1])走过来的，它到原点的距离是(t[0],t[1])到原点的距离加1
                    q[tt++] = new int[]{x, y};//把(x,y)放入队列中
                }
            }
        }
        return d[n-1][m-1];
    }

    public static void main(String[] args) {
        走迷宫 s = new 走迷宫();
        s.initilizaiton();
        System.out.println(s.bfs());
    }
}
