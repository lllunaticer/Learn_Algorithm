package archive;

public class Solution6 {
    public String convert(String s, int numRows){
        StringBuilder result = new StringBuilder();
        if(numRows>1) {
            int position = 0;
            int addtionPost = 0;
            for (int n = 1; n <= numRows; n++) {
                position = n - 1;
                while (position < s.length()) {
                    result.append(s.charAt(position));
                    addtionPost = (numRows - 1) * 2 - (n - 1) * 2 + position;
                    if (addtionPost <s.length()&& n != 1 && n != numRows) {
                        result.append(s.charAt(addtionPost));
                    }
                    position = position + (numRows - 1) * 2;
                }
            }
        }else{
            result.append(s);
        }
        return result.toString();
    }

    public static void main(String[] args){
        Solution6 a  = new Solution6();
        System.out.println(a.convert("ABCD", 3));
    }
}
