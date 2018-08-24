import java.util.*;
public class Solution3 {
    public int lengthOfLongestSubstring(String s){
        int result=0;
        char[] ms = s.toCharArray();
        List<Character> ps= new LinkedList<>();
        Set<Character> duplicatedPs = new HashSet<>();
        for(int i=0;i<ms.length;i++){
            if(duplicatedPs.add(ms[i])){//HashSet的add方法如果向HashSet添加元素成功则返回true（说明待添加元素与HashSet已有元素不重复）
                ps.add(ms[i]);
            }else{
                if(result<ps.size()){result=ps.size();}
                duplicatedPs.clear();
                int j = ps.indexOf(ms[i]);
                for(int temp = 0; temp<=j;temp++)
                    ps.remove(0);
                ps.add(ms[i]);
                duplicatedPs.addAll(ps);
            }
        }
        if(result<ps.size()){result=ps.size();}
        return result;
    }

    public static void main(String[] args){
        String s = "abaabcbb";
        Solution3 a = new Solution3();
        System.out.println(a.lengthOfLongestSubstring(s));
    }

}
