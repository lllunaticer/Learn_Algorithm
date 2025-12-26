package 刷题;
import java.util.*;
public class Solution560 {
    //暴力解法，不可取
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int p = i;
            int sum = 0;
            while (p < nums.length) {
                sum += nums[p];
                if (sum == k)
                    ans++;
                p++;
            }
        }
        return ans;
    }

    //前缀和，优质解法


    public int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> hash = new HashMap<>();
        int tot = 0, ans = 0;
        hash.put(0, 1);
        for (int x : nums) {
            tot += x;
            if (hash.containsKey(tot - k))
                ans += hash.get(tot - k);
            if (!hash.containsKey(tot))
                hash.put(tot, 1);
            else
                hash.put(tot, hash.get(tot) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution560 s = new Solution560();
        int[] arr = {1,2,1,-2,3,2,1,2};
        s.subarraySum2(arr,3);
    }

}
