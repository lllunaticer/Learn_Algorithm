package archive;

import java.util.*;
class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        NavigableSet<Integer> numss = new TreeSet<>();
        List<Integer> nums = new LinkedList<>();
        double result = 0;
        for(int i=0;i<nums1.length;i++){
            numss.add(nums1[i]);
        }
        for(int j=0;j<nums2.length;j++){
            numss.add(nums2[j]);
        }
        while (numss.size()>0){
            nums.add(numss.pollFirst());
        }

        if(nums.size()%2==0){
            double a =(double)nums.get(nums.size()/2);
            double b =(double)nums.get(nums.size()/2-1);
            result = (a+b)/2;
            //result = (nums.get(nums.size()/2)+nums.get(nums.size()/2+1))/2;
        }
        else{
            result = nums.get(nums.size()/2);
        }
        return result;
    }

    public static void main(String[] args){
        Solution4 a = new Solution4();
        int[] s1 = {1,1,1,1,1,1,1,1,1,1,4,4};
        int[] s2 = {1,3,4,4,4,4,4,4,4,4,4};
        System.out.println(a.findMedianSortedArrays(s1,s2));
    }
}
