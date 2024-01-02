package 搜索与遍历_BFS_DFS_树和图的遍历.DFS;/*题目及评测链接:https://www.acwing.com/activity/content/problem/content/906/1/*/
/*
* n-皇后问题是指将 n 个皇后放在 n∗n 的国际象棋棋盘上，使得皇后不能相互攻击到，即任意两个皇后都不能处于同一行、同一列或同一斜线上。
1_597ec77c49-8-queens.png
现在给定整数n，请你输出所有的满足条件的棋子摆法。
输入格式
共一行，包含整数n。
输出格式
每个解决方案占n行，每行输出一个长度为n的字符串，用来表示完整的棋盘状态。
其中”.”表示某一个位置的方格状态为空，”Q”表示某一个位置的方格上摆着皇后。
每个方案输出完成后，输出一个空行。
数据范围
1≤n≤9
输入样例：
4
输出样例：
.Q..
...Q
Q...
..Q.

..Q.
Q...
...Q
.Q..
* */

import java.util.Scanner;

public class n皇后问题解法1枚举每行搜索_推荐解法 {
    final int N = 10;//这里n<9, 所以取N=10;
    int n;//棋盘尺寸是n*n
    char[][] g = new char[N][N];//状态数组，用于保存棋盘状态
    boolean[] col = new boolean[N];//列标识数组，col[i]=true表示第i列已经放置了皇后，本层以下不能再在第i列放置皇后
    //如果棋盘是n*n, 则正对角线有2*n-1条，反对角线也有2*n-1条，所以标记数组的长度最少要有2*n-1才能完全标识所有的对角线状态。
    boolean[] dg = new boolean[2*N];//正对角线标识数组，dg[i]=true表示第i个正对角线已经放置了皇后，本层以下不能再在第i个对角线上放置皇后
    boolean[] udg = new boolean[2*N];//反对角线标识数组，udg[i]=true表示第i个反对角线已经放置了皇后，本层以下不能再在第i个反对角线上放置皇后

    void initialization(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        //初始化棋盘状态
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                g[i][j] = '.';
    }

    void dfs(int u){
        //如果u = n, 说明前n行都已经放置了皇后，找到了一个合法的放置方法，输出该方法；
       if(u == n){
           for(int i = 0; i<n; i++){
               for(int j = 0; j<n; j++){
                   System.out.print(g[i][j]);
               }
               System.out.println();
           }
           System.out.println();
           return;//退出本层，回溯到调用本层的第n-1层
       }
       //当u!=n时，则说明第u层，也就是第i行的皇后还没有放置，遍历所有的列去找合法的列去放置本行的皇后
       for(int i = 0; i<n; i++){
           //通过查看标识数组来确定每一列是否是可以放置的合法的列
           //至于为什么i对应的正对角线是i+u, 对应的斜对角线是n+u-i, 见overview.md的分析
           if(!col[i]&&!dg[i+u]&&!udg[n-u+i]){
               //找到了合法的可以放置的列，将第u行第i列标识为皇后，并在本层标记第i列， 第u+i个正对角线，第n-u+i个斜对角线都是已经使用过的了，下层不能再使用了
               g[u][i] = 'Q';
               col[i] = dg[i+u] = udg[n-u+i] = true;
               //标记完成后，开始确定下一层，也就是下一行的皇后的位置。上层的标记会传给下一层
               dfs(u+1);
               //递归下层的函数结束后，会回溯到本层继续执行，本层使用过的i在下一层不能使用，但是在本层以上是可以使用的。所以在退出本层回溯到上一层之前，
               //必须清除掉本层对i的使用标记，以便上层能继续使用i。并且棋盘状态数组也要恢复。
               col[i] = dg[i+u] = udg[n-u+i] = false;
               g[u][i] = '.';
           }
       }
    }

    public static void main(String[] args) {
        n皇后问题解法1枚举每行搜索_推荐解法 s = new n皇后问题解法1枚举每行搜索_推荐解法();
        s.initialization();
        s.dfs(0);
    }
}
