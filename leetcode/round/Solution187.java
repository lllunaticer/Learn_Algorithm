package 刷题;
import java.util.*;
public class Solution187 {

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> record = new HashSet<>();
        Set<String> res = new HashSet<>();
        List<String> ress;
        for(int i = 0; i<=s.length()-10;i++){
            if(!record.add(s.substring(i,i+10)))
                res.add(s.substring(i,i+10));
        }
        ress = new ArrayList<>(res);
        return ress;
    }

    public static List<String> findRepeatedDnaSequences_deprecation(String s) {
        Set<String> res = new HashSet<>();
        char[] string = s.toCharArray();
        int p1,p2;
        for(int i = 0; i<string.length-10; i++){
            p1 = i;
            int tmp = 0;
            p2 = i+1;
            while(p2<string.length-10){
                if(string[p1+tmp]!=string[p2+tmp]){
                    //不匹配，将p2移动tmp位，重新开始10位的匹配
                    p2++;
                    tmp = 0;
                    continue;
                }
                if(tmp == 9){
                    //找到一个结果，保存，p1往前移动一位
                    res.add(s.substring(p1,p1+tmp));
                    break;
                }
                //本位匹配，继续往后看
                tmp++;
            }
        }
        List<String> ress = new ArrayList<>(res);
        return ress;
    }

    public static void main(String[] args) {
        String s = "AAAAAAAAAAAA";
        Solution187 tmp = new Solution187();
        tmp.findRepeatedDnaSequences(s);
    }

}
