/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2024-06-12
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 题目来源: {@link  <a href="https://leetcode.cn/problems/sum-of-subarray-minimums/description/">LeetCode第907题</a>}
 * 问题描述：
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * 示例 1：
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * 示例 2：
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 * 提示：
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 * 题解: {@link
 * <a href="https://mp.weixin.qq.com/s/lJQoThknn4XBv89fX8AdjA?poc_token=HLApXWajgEW-UlC0dPLr0wYSZvmMj2C6K-0fBEHR">LeetCode第907题</a>}
 * <p>
 * 关键点：
 */
public class Solution907 {
    int MOD = (int) 1e9 + 7;

    public int sumSubarrayMins(int[] arr) {
        int length = arr.length;
        // l[i] 代表 i 的左边，比arr[l[i]]小的最近的元素的下标，如果不存在，则为-1
        int[] l = new int[length];
        //r[i] 代表 i 的右边，比arr[r[i]]小的最近的元素的下标，如果不存在，则为 length
        int[] r = new int[length];
        Deque<Integer> monotonicStack = new ArrayDeque<>();
        for (int i = length - 1; i >= 0; i--) {
            while (!monotonicStack.isEmpty() && arr[monotonicStack.peek()] >= arr[i]) {
                monotonicStack.pop();
            }
            r[i] = monotonicStack.isEmpty() ? length : monotonicStack.peek();
            monotonicStack.push(i);
        }
//        System.out.println("r: " + Arrays.toString(r));
        monotonicStack.clear();
        for (int i = 0; i < length; i++) {
            while (!monotonicStack.isEmpty() && arr[monotonicStack.peek()] > arr[i]) {
                monotonicStack.pop();
            }
            l[i] = monotonicStack.isEmpty() ? -1 : monotonicStack.peek();
            monotonicStack.push(i);
        }
//        System.out.println("l: " + Arrays.toString(l));
        int res = 0;
        for (int i = 0; i < length; i++) {
            int a = i - l[i];
            int b = r[i] - i;
            res += (a * b * arr[i]) % MOD;
        }
        return res % MOD;
    }

    public static void main(String[] args) {
        // [3,1,2,4]
        int res = new Solution907().sumSubarrayMins(new int[] {11,81,94,43,3});
        System.out.println(res);
    }
}
