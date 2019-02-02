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
public class Solution12 {
    public String[] mark1 = {"I","IV","V","IX"};
    public String[] mark10 = {"X","XL","L","XC"};
    public String[] mark100 = {"C","CD","D","CM"};
    public String[] mark1000 = {"M"};

    public String intToRoman(int n){
        if(n>=0 && n<=3999) {
            StringBuilder Roman = new StringBuilder();
            int i;

            i = (n - n % 1000) / 1000;
            n = n % 1000;
            Roman = this.parse(i, mark1000, Roman);

            i = (n - n % 100) / 100;
            n = n % 100;
            Roman = this.parse(i, mark100, Roman);

            i = (n - n % 10) / 10;
            n = n % 10;
            Roman = this.parse(i, mark10, Roman);

            i = n;
            Roman = this.parse(i, mark1, Roman);

            return Roman.toString();
        }
        else
            return "the input is invalid!";
        }

        private StringBuilder parse(int n, String[] mark, StringBuilder Roman){
            if (n<=3) {
                for(int i=n;i>0;i--){
                    Roman.append(mark[0]);
                }
            }
            if (n==4) {
                Roman.append(mark[1]);
            }
            if (n>=5 && n<9) {
                Roman.append(mark[2]);
                for(int i=n-5;i>0;i--){
                    Roman.append(mark[0]);
                }
            }
            if (n==9) {
                Roman.append(mark[3]);
            }
            return Roman;
        }

    public static void main(String[] args) {
        Solution12 foo = new Solution12();
        System.out.println(foo.intToRoman(3999));
    }
}

