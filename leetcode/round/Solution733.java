package 刷题;

public class Solution733 {
    int m, n;
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image==null || image.length==0 || (image.length==1 && image[0].length == 0))
            return image;
        m = image.length;
        n = image[0].length;
        int oldColor = image[sr][sc];
        if(sr<0 || sr>m || sc<0 || sc>n || oldColor == newColor)
            return image;
        dfs(image, sr, sc, newColor, oldColor);
        return image;
    }

    void dfs(int[][] image, int sr, int sc, int newColor, int oldColor){
        if(sr<0 || sr>=m || sc<0 || sc>=n || image[sr][sc]!=oldColor)
            return;
        image[sr][sc] = newColor;
        for(int[] d : dirs){
            dfs(image, sr+d[0], sc+d[1],newColor, oldColor);
        }
        return;
    }
}
