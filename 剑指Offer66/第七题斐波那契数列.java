/*题目描述
大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
n<=39*/
public class 第七题斐波那契数列 {
    public static int Fibonacci(int n) {
        int[] dp = new int[n + 1];
        return Fib(n, dp);
    }

    static int Fib(int i, int[] dp) {
        if (i < 2) {
            dp[i] = i;
            return dp[i];
        } else {
            if (dp[i] == 0)
                dp[i] = Fib(i - 1, dp) + Fib(i - 2, dp);
            return dp[i];
        }
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci(0));
    }

}
