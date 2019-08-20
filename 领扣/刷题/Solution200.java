package 刷题;

import java.util.*;
class Solution200 {
    public int numIslands(char[][] grid) {
        int N = grid.length;
        if(N == 0)
            return 0;
        int M = grid[0].length;
        Set<String> res = new HashSet<>();
        String[] root = new String[M*N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(grid[i][j] == '0')
                    continue;
                if(i-1>=0&&grid[i-1][j] == '1')
                    root[i*M+j] = root[(i-1)*M+j];
                else if(j-1>=0&&grid[i][j-1] == '1')
                    root[i*M+j] = root[i*M+j-1];
                else
                    root[i*M+j] = i+""+j;
                res.add(root[i*M+j]);
            }
        }
        return res.size();
    }

    public static void main(String[] args) {
        Solution200 s = new Solution200();
        char[][] arr = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        s.numIslands(arr);
    }
}