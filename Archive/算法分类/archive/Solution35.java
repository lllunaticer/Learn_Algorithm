package archive;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Example 1:

 Input: [1,3,5,6], 5
 Output: 2
 Example 2:

 Input: [1,3,5,6], 2
 Output: 1
 Example 3:

 Input: [1,3,5,6], 7
 Output: 4
 Example 4:

 Input: [1,3,5,6], 0
 Output: 0
 */
//Binary search
public class Solution35 {
    public int searchInsert(int[] nums, int target){
        int s = nums.length;
        int pointer = 0;
        if(target<nums[0]){
            pointer = 0;
        }
        else if(target>nums[s-1]){
            pointer = s;
        }
        else{
            int head = 0;
            int tail = s;
            pointer=(head+tail)/2;
            while(pointer!=head){
                if(target<nums[pointer]){
                    tail = pointer;
                }
                if(target>nums[pointer]){
                    head = pointer;
                }
                if(target==nums[pointer]){
                    break;
                }
                pointer = (head + tail)/2;
            }
            if(target!=nums[pointer])
                pointer++;
        }

        return pointer;
    }

    public static void main(String[] args) {
        Solution35 a = new Solution35();
        int[] c = {1,3,5,6};
        System.out.println(a.searchInsert(c, 5));
    }
}
