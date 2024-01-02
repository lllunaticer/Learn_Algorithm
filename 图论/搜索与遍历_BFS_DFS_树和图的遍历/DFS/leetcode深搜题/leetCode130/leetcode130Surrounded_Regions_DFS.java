package 搜索与遍历_BFS_DFS_树和图的遍历.DFS.leetcode深搜题.leetCode130;

public class leetcode130Surrounded_Regions_DFS {
    public void solve(char[][] board) {
        //行数或者列数小于等于2的矩阵所有的元素都在边上
        if (board == null || board.length < 3 || board[0].length < 3)
            return;
        //用dfs将所有的'O'标记为'#'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean isEdge = (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1);
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }
        //把所有的'O'换为‘X’， 所有的'#'换回'O'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    //dfs深度优先遍历
    void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#')
            return;
        board[i][j] = '#';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }


}
