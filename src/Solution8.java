import java.util.regex.*;

public class Solution8 {
    public int myAtoi(String str){
        String s = str.trim();
        String str_result="0";
        int flag = 1;
        int result = 0;
        String pattern = "^[+-]?0*[123456789]\\d*";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);
        if(m.find()) {
            str_result = s.substring(m.start(), m.end());
            System.out.println(str_result);
            if(str_result.charAt(0)=='-'){
                flag = -1;
                str_result = str_result.substring(1, str_result.length());
            }
            if(str_result.charAt(0)=='+'){
                flag = 1;
                str_result = str_result.substring(1, str_result.length());
            }
            for(int i = 0;i<str_result.length();i++){
                if(str_result.charAt(i)!='0'){
                    str_result=str_result.substring(i, str_result.length());
                    break;
                }
            }

            if(str_result.length()>10||(str_result.length()==10&&Integer.parseInt((str_result.charAt(0)+""))>2)){
                if(flag==1)
                    result = Integer.MAX_VALUE;
                else
                    result = Integer.MIN_VALUE;
            } else if(str_result.length()==10&&Integer.parseInt((str_result.charAt(0)+""))==2){
                int nums = Integer.parseInt(str_result.substring(1, str_result.length()));
                if(nums+Integer.parseInt((str_result.charAt(0)+""))*1000000000<0){
                    if(flag==1)
                        result = Integer.MAX_VALUE;
                    else
                        result = Integer.MIN_VALUE;
                }else
                    result = Integer.parseInt(str_result)*flag;
            }else
                result = Integer.parseInt(str_result)*flag;
        }

        return result;
    }

    public static void main(String[] args){
        Solution8 a = new Solution8();
        System.out.println(a.myAtoi(" 2147483648dd"));
    }
}
