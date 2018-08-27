public class Solution5 {
    public String longestPalindrome(String s) {
        String result = "";
        double king = (double)s.length()/2-0.5;
        int soilder=1;
        int[] max_left_right= {0,0};
        int flag = 1;
        double king_Offset = 0.5;

        int left_soilder =1;
        int right_soilder =1;

        if(s.length()!=0) {
            while (king > 0 && king < s.length() - 1) {
                boolean f = king % 1 == 0;
                if (f) {
                    left_soilder = (int) Math.floor(king - soilder);
                    right_soilder = (int) Math.floor(king + soilder);

                } else {
                    left_soilder = (int) Math.ceil(king - soilder);
                    right_soilder = (int) Math.floor(king + soilder);
                }

                if (s.charAt(left_soilder) == s.charAt(right_soilder)) {
                    //如果king的左右数字相等，则让soiler往外再走一步进行比较
                    soilder++;
                    //如果这次soilder走的够远，则将结果保存
                    if (max_left_right[1] - max_left_right[0] < right_soilder - left_soilder) {
                        max_left_right[0] = left_soilder;
                        max_left_right[1] = right_soilder;
                    }
                    if (left_soilder <= 0 || right_soilder >= s.length() - 1)
                        break;

                } else {
                    //如果这次soilder走的够远，则将结果保存
                    if (max_left_right[1] - max_left_right[0] < right_soilder - left_soilder - 2) {
                        max_left_right[0] = left_soilder;
                        max_left_right[1] = right_soilder;
                    }
                    //进入else说明soilder这次比较失败，king的左右soilder距离处数字不相等
                    //需要移动king的位置，king的移动原则是从字符串正中间开始，向左右两边交替移动，即king的值应该+1,-2,+3,-4.....
                    king = king + flag * king_Offset;
                    flag = -flag;//用于左右交替
                    king_Offset = king_Offset + 0.5;//用于增加距离
                    soilder = 1;//将soiler重设为1，再次检查
                }
            }
            result = s.substring(max_left_right[0], max_left_right[1] + 1);
        }else
        {result = s;}
        return result;
    }

    public static void main(String[] args){
        Solution5 a = new Solution5();
        System.out.println(a.longestPalindrome(""));
    }
}
