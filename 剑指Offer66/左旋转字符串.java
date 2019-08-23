/*
* 题目二：左旋转字符串。
字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。*/

/*我们可以从翻转字符串的思路中找到启发？在该问题中，如果输入的字符串之中只有两个单词，比如“hello world"，
那么翻转这个句子中的单词顺序就得到了“world hello”。比较这两个字符串，我们是不是可以把"world hello”看
成把原始字符串"hello world”的前面若干个字符转移到后面？也就是说这两个问题是非常相似的，我们同样可以通过
翻转字符串的办法来解决第二个问题。
以“abcdefg”为例，我们可以把它分为两部分。由于想把它的前两个字符移到后面，我们就把前两个字符分到第一部分，
把后面的所有字符分到第二部分。我们先分别翻转这两部分，于是就得到“bagfedc”。接下来翻转整个字符串，得到的
"cdefgab”刚好就是把原始字符串左旋转两位的结果。
通过前面的分析，我们发现只需要调用3次前面的Reverse函数就可以实现字符串的左旋转功能。
*/
public class 左旋转字符串 {

    public String LeftRotateString(String str,int n) {
        //勿忘特殊条件
        if(str == null || str.length() == 0 || n < 0)
            return str;
        n %= str.length();
        String s1 = str.substring(0,n);
        String s2 = str.substring(n,str.length());
        s1 = reverse(s1);
        s2 = reverse(s2);
        return reverse(s1+s2);
    }

    String reverse(String string){
        StringBuilder sb = new StringBuilder();
        for(int i = string.length()-1;i>=0;i--)
            sb.append(string.charAt(i));
        return sb.toString();
    }

    /*思路二可以用队列做实际翻转*/
}
