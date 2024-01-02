package 搜索与遍历_BFS_DFS_树和图的遍历.DFS;

import java.util.Scanner;
public class n皇后问题解法2枚举每个格子搜索 {
    int N = 10;
    int n;
    char[][] g = new char[N][N];
    boolean[] col = new boolean[N];
    boolean[] row = new boolean[N];
    boolean[] dg = new boolean[2 * N];
    boolean[] udg = new boolean[2 * N];

    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                g[i][j] = '.';
    }

    //x, y是格子的坐标， s是当前递归层放了多少个皇后
    void dfs(int x, int y, int s) {
        //如果y到头了，就换一行
        if (y == n) {
            y = 0;
            x++;
            //如果行也到头了，就检查是不是放好了n个皇后，如果是就表明当前有一个合法的放置方法，输出。如果否就直接返回，回溯到上层递归
            if (x == n) {
                if (s == n) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++){
                            System.out.print(g[i][j]);
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
                return;
            }
        }
        //当没有到x = n, y = n的位置时，我们开始按本层递归，也就是x,y位置是否可以放置皇后来处理问题

        //枚举在本层递归，也就是x,y位置放还是不放皇后两种情况:

        //不放皇后，直接递归下一层状态
        dfs(x,y+1,s);

        //放皇后，做好记录，递归下一层，恢复现场
        if(!col[x]&&!row[y]&&!dg[n+x-y]&&!udg[x+y]){
            g[x][y] = 'Q';
            col[x]=row[y]=dg[n+x-y]=udg[x+y] = true;
            //记录好使用状态，进入下层递归
            dfs(x, y+1, s+1);
            //退出本层递归之前恢复现场
            col[x]=row[y]=dg[n+x-y]=udg[x+y] = false;
            g[x][y] = '.';
        }
    }

    public static void main(String[] args) {
        n皇后问题解法2枚举每个格子搜索 s = new n皇后问题解法2枚举每个格子搜索();
        s.initialization();
        s.dfs(0,0,0);
    }
}
