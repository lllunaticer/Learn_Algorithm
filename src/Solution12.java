
public class Solution12 {
    public int maxArea(int[] height){
        int length = height.length;
        int volumn  = 0;
        for (int i=0;i<length-1;i++){
            for(int j=i+1;j<=length-1;j++){
                int current_vol = (j-i)*Math.min(height[i],height[j]);
                if(volumn<current_vol)
                    volumn = current_vol;
            }
        }
        return volumn;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        Solution12 a = new Solution12();
        System.out.println(a.maxArea(height));
    }
}
/**better solution, from other committer in leecoder
 * class Solution {
 public int maxArea(int[] height) {
 int max = 0, vol=0;
 int i=0;
 int j = height.length-1;
 while(i!=j){
 vol = Math.min(height[i], height[j]) * (j-i);
 max = (vol > max) ? vol : max;
 if(height[i]>height[j]) j--;
 else i++;
 }
 return max;
 }
 }
 */