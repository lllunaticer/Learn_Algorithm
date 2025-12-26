package 刷题;

public class Solution463 {
    int m, n;
    public int islandPerimeter(int[][] grid) {
        if(grid==null || grid.length==0 || (grid.length == 1 && grid[0].length== 0))
            return 0;
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 1)
                    res += find(i, j, grid);
            }
        }
        return res;
    }

    int find(int i, int j, int[][] grid){
        int res = 0;
        if(i-1<0 || grid[i-1][j] == 0)
            res++;
        if(i+1>=m || grid[i+1][j] == 0)
            res++;
        if(j-1<0 || grid[i][j-1] == 0)
            res++;
        if(j+1>=n || grid[i][j+1] == 0)
            res++;
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {{0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}};
        int[][] a= {{1}};
        Solution463 s = new Solution463();
        s.islandPerimeter(a);
    }
}
