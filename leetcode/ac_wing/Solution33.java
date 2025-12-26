package ACwing打卡;

public class Solution33 {
    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        int l = 0;
        int r = nums.length-1;
        int minimum = 0;
        while(l<r){
            int mid = (l+r)>>1;
            if(nums[mid]<nums[r])
                r = mid;
            else
                l = mid+1;
        }
        minimum = l;

        l = minimum;
        r = nums.length-1+minimum;
        while(l<r){
            int mid = (l+r)>>1;
            if(nums[mid%nums.length]>=target)
                r = mid;
            else
                l = mid+1;
        }
        return l>=nums.length||nums[l] == target? l : -1;
    }

    public static void main(String[] args) {
        int [] a = {3,-1};
        search(a,3);
    }
}
