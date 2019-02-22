/*
* Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
* */
public class NumIsland {
    public static void main(String[] args) {
        char[][] a = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        NumIsland numIsland = new NumIsland();
        int s = numIsland.numIslands(a);
        System.out.println(s);
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfsFind(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    void dfsFind(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1')
            return;
        grid[i][j] = '0';
        dfsFind(grid, i - 1, j);
        dfsFind(grid, i + 1, j);
        dfsFind(grid, i, j - 1);
        dfsFind(grid, i, j + 1);
    }
}
