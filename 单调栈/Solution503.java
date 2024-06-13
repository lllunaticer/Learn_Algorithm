/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2024-06-12
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 题目来源: {@link  <a href="https://leetcode.cn/problems/next-greater-element-ii/description/">LeetCode第503题</a>}
 * 问题描述：给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 * 示例 1:
 * 输入: nums = [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 示例 2:
 * 输入: nums = [1,2,3,4,3]
 * 输出: [2,3,4,-1,4]
 * 提示:
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * 关键点：
 * 思路来源 <a href="https://mp.weixin.qq.com/s/KYfjBejo84AmajnPZNs5nA">m</a>
 * 一般是通过 % 运算符求模（余数），来获得环形特效：
 * int[] arr = {1,2,3,4,5};
 * int n = arr.length, index = 0;
 * while (true) {
 * print(arr[index % n]);
 * index++;
 * }
 * 这个问题肯定还是要用单调栈的解题模板，但难点在于，比如输入是[2,1,2,4,3]，对于最后一个元素 3，如何找到元素 4 作为 Next Greater Number。
 * 对于这种需求，常用套路就是将数组长度翻倍：
 * [1,2,3,4,3] - > [1,2,3,4,3,1,2,3,4,3]
 * 这样，元素 3 就可以找到元素 4 作为 Next Greater Number 了，而且其他的元素都可以被正确地计算。
 * 有了思路，最简单的实现方式当然可以把这个双倍长度的数组构造出来，然后套用算法模板。但是，我们可以不用构造新数组，而是利用循环数组的技巧来模拟数组长度翻倍的效果。
 */
public class Solution503 {
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> monotonicStack = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n];
        // 长度加倍 & 取模，实现环形数组的特效
        for (int i = 2 * n - 1; i > 0; i--) {
            while (!monotonicStack.isEmpty() && monotonicStack.peek() <= nums[i % n]) {
                monotonicStack.pop();
            }
            res[i % n] = monotonicStack.isEmpty() ? -1 : monotonicStack.peek();
            monotonicStack.push(nums[i % n]);
        }
        return res;
    }
}
