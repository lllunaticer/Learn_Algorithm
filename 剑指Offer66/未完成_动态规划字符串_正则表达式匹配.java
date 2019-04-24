/*
 * 题目:
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有
 * 字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"
 * 和"ab*a"均不匹配
 * */

/*
 * 题解:
 *
 * */
public class 未完成_动态规划字符串_正则表达式匹配 {
    int[][] f;
    int m;
    int n;
    char[] p;
    char[] s;

    public boolean match(char[] str, char[] pattern) {
        p = pattern;
        s = str;
        n = s.length;
        m = p.length;
        f = new int[n+1][m+1];
        return dp(0, 0);
    }

    boolean dp(int x, int y) {
        if (f[x][y] != 0) return f[x][y] == 1;
        if (y == m){
            f[x][y] = x==n? 1:-1;
            return f[x][y] == 1;
        }
        boolean first_match = x<n &&(p[y] == '.' || s[x] == p[y]);
        if(y+1<m && p[y+1] == '*'){
            f[x][y] = dp(x,y+2)||dp(x+1,y)?1:-1;
        }
        else{
            f[x][y] = first_match && dp(x+1,y+1)?1:-1;
        }
        return f[x][y]==1;
    }

    public static void main(String[] args) {
        未完成_动态规划字符串_正则表达式匹配 a = new 未完成_动态规划字符串_正则表达式匹配();
        a.match("a".toCharArray(),"ab*a".toCharArray());
    }
}
