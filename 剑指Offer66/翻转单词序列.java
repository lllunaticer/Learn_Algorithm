/*题目：翻转单词顺序。
输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
例如输入字符串"I am a student."，则输出"student.a am I"。*/

/*这道题目流传甚广，很多公司多次拿来作为面试题，很多应聘者也多次在各种博客或者书籍上看到通过两次翻转
字符串的解法，于是很快就可以跟面试官解释清楚解题思路：第一步翻转句子中所有的字符。比如翻转
"Iam a student."中所有的字符得到".tneduts a maI"，此时不但翻转了句子中单词的顺序，
连单词内的字符顺序也被翻转了。第二步再翻转每个单词中字符的顺序，就得到了"student.a amI"。
这正是符合题目要求的输出。*/
public class 翻转单词序列 {
    public String ReverseSentence(String str) {
        //这句很难想到
        if(str.trim().equals(""))
            return str;
        String tmp = reverse(str);
        String[] arr = tmp.split(" ");
        for(int i = 0; i<arr.length;i++)
            arr[i] = reverse(arr[i]);
        StringBuilder sb = new StringBuilder();
        for(String s: arr)
            sb.append(s).append(" ");
        String res_t = sb.toString();
        return res_t.substring(0,res_t.length()-1);
    }

    String reverse(String string){
        StringBuilder sb = new StringBuilder();
        for(int i = string.length()-1;i>=0;i--)
            sb.append(string.charAt(i));
        return sb.toString();
    }

//    更简单的方法，读成字符串数组然后逆序保存
    public String ReverseSentence2(String str) {
        if(str.trim().equals("")){
            return str;
        }
        String[] a = str.split(" ");
        StringBuilder o = new StringBuilder();
        int i;
        for (i = a.length; i >0;i--){
            o.append(a[i-1]);
            if(i > 1){
                o.append(" ");
            }
        }
        return o.toString();
    }
}
