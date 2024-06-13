import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目来源: https://leetcode.cn/problems/next-greater-element-i/description/
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2024-06-11
 * <p>
 * 思路详解 ：
 */
public class Solution496 {
    /**
     * 单调栈
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> vMap = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {//注意我们是从后往遍历的
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                stack.pop(); //比num[i]都小的弹出，直到找到第一个比num[i]大的，则这个数是num[i]右边第一个比nums[i]大的数字
            }
            vMap.put(nums2[i], stack.isEmpty() ? -1 : stack.peek()); // 栈顶元素就是num[i]右边第一个比num[i]大的元素，这是num[i]的答案
            stack.push(nums2[i]);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = vMap.get(nums1[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println(Arrays.toString(new Solution496().nextGreaterElement(nums1, nums2)));
    }
}

