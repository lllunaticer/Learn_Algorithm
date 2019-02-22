package archive;

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        outer:
        for(int i=0;i<=nums.length-2;i++){
            for(int j=i+1;j<=nums.length-1;j++){
                if(nums[i]+nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    break outer;
                }
            }
        }
        return result;
    }
}