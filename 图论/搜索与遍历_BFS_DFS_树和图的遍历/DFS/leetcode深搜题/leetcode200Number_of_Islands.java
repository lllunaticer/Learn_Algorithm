package 搜索与遍历_BFS_DFS_树和图的遍历.DFS.leetcode深搜题;

public class leetcode200Number_of_Islands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || (grid.length == 1 && grid[0] == null))
            return 0;
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    //用深搜将所有与(i, j)相连的1都变为0
                    dfs(grid, i, j);
                }
            }
        }
        return cnt;
    }

    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    void dfs(char[][] grid, int i, int j) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
            grid[i][j] = '0';
            for (int[] d : dirs) {
                int x = i + d[0];
                int y = j + d[1];
                dfs(grid, x, y);
            }
            return;
        }
        return;
    }
}
