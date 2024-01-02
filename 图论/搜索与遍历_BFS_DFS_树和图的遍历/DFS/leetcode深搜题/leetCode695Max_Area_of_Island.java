package 搜索与遍历_BFS_DFS_树和图的遍历.DFS.leetcode深搜题;

public class leetCode695Max_Area_of_Island {
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    int m, n;
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0 || (grid.length == 1 && grid[0].length == 0))
            return 0;
        m = grid.length;
        n = grid[0].length;
        //boolean[][] visited = new boolean[m][n];
        int max = 0;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 1)
                    max = Math.max(max, dfs(i, j, grid));
            }
        }
        return max;
    }

    int dfs(int i, int j, int[][] grid){
        grid[i][j] = 0;
        int res = 0;
        for(int[] d:dirs){
            int x = i+d[0];
            int y = j+d[1];
            if(x>=0 && x<m && y>=0 && y<n && grid[x][y] == 1){
                res += dfs(x, y, grid);
            }
        }
        return ++res;
    }
}
