/*
* Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
* */
//题解来自: https://www.cnblogs.com/grandyang/p/4340948.html
public class 经典滑动窗口问题 {
    public static String minWindow(String s, String t) {
        String res = "";
        int[] letterCnt = new int[128];
        int left = 0;
        int cnt = 0;
        int minLen = s.length();
        for (char c : t.toCharArray())
            ++letterCnt[c];
        for (int i = 0; i < s.length(); ++i) {
            if (--letterCnt[s.charAt(i)] >= 0)
                ++cnt;
            while (cnt == t.length()) {
                if (minLen >= i - left + 1) {
                    minLen = i - left + 1;
                    res = s.substring(left, left+minLen);
                }
                if (++letterCnt[s.charAt(left)] > 0)
                    --cnt;
                ++left;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "A";
        String t = "A";
        System.out.println(minWindow(s, t));
    }
}
