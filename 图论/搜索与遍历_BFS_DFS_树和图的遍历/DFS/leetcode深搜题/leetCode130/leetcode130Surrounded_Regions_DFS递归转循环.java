package 搜索与遍历_BFS_DFS_树和图的遍历.DFS.leetcode深搜题.leetCode130;

import java.util.Stack;

//非递归的方式，我们需要记录每一次遍历过的位置，我们用 stack 来记录，因为它先进后出的特点。
public class leetcode130Surrounded_Regions_DFS递归转循环 {
    public void solve(char[][] board) {
        //棋盘的行数或者列数小于3的时候所有的元素都是边上的元素
        if (board == null || board.length < 3 || board[0].length < 3) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从边缘第一个是o的开始搜索
                boolean isEdge = (i == 0 || j == 0 || i == m - 1 || j == n - 1);
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});
        board[i][j] = '#';
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            for (int[] d : dirs) {
                int x = current[0] + d[0];
                int y = current[1] + d[1];
                if (x > 0 && x < board.length && y > 0 && y < board[0].length && board[x][y] == 'O') {
                    board[x][y] = '#';
                    stack.push(new int[]{x, y});
                }
            }
        }
    }
}
