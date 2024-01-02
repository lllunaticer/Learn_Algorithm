/*
* 题目描述
一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
求该青蛙跳上一个n级的台阶总共有多少种跳法。
* */
public class 循环和递归_变态跳台阶 {
    //    递归
    static public int JumpFloorII(int target) {
        if (target < 2)
            return 1;
        else {
            int res = 0;
            for (int i = 0; i < target; i++)
                res = res + JumpFloorII(i);
            return res;
        }
    }

    //    动态规划
    static public int JumpFloorIII(int target) {
        int[] val = new int[target];
        int res = 0;
        for (int i = 0; i < target; i++)
            res = res + dp(i, val);
        return res;
    }

    static int dp(int target, int[] val) {
        if (target < 2) {
            val[target] = 1;
            return val[target];
        } else {
            if (val[target] == 0) {
                for (int i = 0; i < target; i++) {
                    val[target] += dp(i, val);
                }
            }
            return val[target];
        }
    }

    public static void main(String[] args) {
        System.out.println(JumpFloorIII(4));
        System.out.println(JumpFloorII(4));
    }
}
