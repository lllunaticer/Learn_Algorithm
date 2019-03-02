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

    //使用并查集解决此问题
    public int numIslands2(char[][] grid) {
        if(grid.length==0)
            return 0;
        UF uf = new UF();
        int m = grid.length;
        int n = grid[0].length;
        int[] id = new int[m * n];
        //将数组内容初始化为自己的下标，刚开始时整个数组的连通分量都是自身，每个位置都是独立的一个‘岛屿’
        int islandNum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                id[i * n + j] = i * n + j;
            }
        }
        //接下来根据char[][]的内容来连通那些属于同一片区域的岛屿
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    islandNum++;
                    int p = i * n + j;
//                    先检查右边是否也标记为1
                    if (j < n - 1 && grid[i][j + 1] == '1') {
                        int q = p + 1;
//                        唯一一种可能重复检查的情况是右边的元素之前被上面的元素连过了
                        if (!uf.isConnected(p, q, id)) {
                            uf.union(p, q, id);
                            islandNum--;
                        }
                    }
//                    再检查下面是否标记为1,下方的元素不可能被访问过
                    if (i < m - 1 && grid[i + 1][j] == '1') {
                        int q = p + n;
                        uf.union(p, q, id);
                        islandNum--;
                    }
                }
            }
        }
        return islandNum;
    }

    //    定义并查集工具函数
    class UF {
        //    寻找p的连通分量
        int find(int p, int[] id) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }

        boolean isConnected(int p, int q, int[] id) {
            return find(p, id) == find(q, id);
        }

        void union(int p, int q, int[] id) {
            id[find(p, id)] = find(q, id);
        }

    }

}
