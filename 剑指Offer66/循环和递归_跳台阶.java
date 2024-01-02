/*一只青蛙一次可以跳上1级台阶，也可以跳上2级。
求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。*/
public class 循环和递归_跳台阶 {
    //    递归
    public int JumpFloor(int target) {
        if (target < 2)
            return 1;
        else
            return JumpFloor(target - 1) + JumpFloor(target - 2);
    }

    //    动态规划

    public int JumpFloorI(int target) {
        int[] val = new int[target + 1];
        return dp(target, val);
    }

    public int dp(int target, int[] val) {
        if (target < 2) {
            val[target] = 1;
            return val[target];
        } else {
            if (val[target] == 0)
                val[target] = dp(target - 1, val) + dp(target - 2, val);
            return val[target];
        }
    }
}
