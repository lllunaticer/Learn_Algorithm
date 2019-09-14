package 刷题;

public class solution72 {
    String w1, w2;
    int[][] memo;
    public int minDistance(String word1, String word2) {
        w1 = word1;
        w2 = word2;
        memo = new int[w1.length()][w2.length()];
        return dp(w1.length()-1, w2.length()-1);
    }

    int dp(int i, int j){
        if(memo[i][j]!=0)
            return memo[i][j];
        if(i == -1){
            return j+1;
        }
        if(j == -1)
            return i+1;
        if(w1.charAt(i) == w2.charAt(j))
            return dp(i-1, j-1);
        else{
            int res = 0;
            res = Math.min(dp(i, j-1)+1, dp(i-1,j)+1);
            res = Math.min(res, dp(i-1, j-1)+1);
            return res;
        }
    }
}
