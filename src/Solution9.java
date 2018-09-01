import java.util.Stack;

public class Solution9 {
    public boolean isPalindrome(int x){
        boolean result = true;
        if(x<0){
            result = false;
        }else{
            Stack<Integer> temp = new Stack<>();
            int index = 1;
            int x_temp = x;
            while(x_temp!=0){
                temp.push(x_temp%10);
                x_temp = (x_temp-x_temp%10)/10;
            }
            while(temp.size()!=0){
                x_temp = x_temp+temp.pop()*index;
                index = index*10;
            }
            result =  (x==x_temp);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution9 a = new Solution9();
        System.out.println(a.isPalindrome(211));
    }
}
