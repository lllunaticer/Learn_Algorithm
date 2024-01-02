package 搜索与遍历_BFS_DFS_树和图的遍历.DFS.leetcode深搜题.leetCode329;

public class leetcode329Longest_Increasing_Path_in_a_Matrix_朴素DFS {
    int m, n;
    int[][] dirs = {{-1, 0}, {0, 1,}, {1, 0}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || (matrix.length == 1 && matrix[0].length == 0))
            return 0;
        m = matrix.length;
        n = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j, matrix));
            }
        }
        return ans;
    }


    int dfs(int i, int j, int[][] matrix) {
        int res = 0;
        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                res = Math.max(res, dfs(x, y, matrix));
            }
        }
        return ++res;
    }
}
