/*
* 题目描述
请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，
第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
输出描述:
如果当前字符流没有存在出现一次的字符，返回#字符
* */

/*
*字符只能一个接着一个从字符流中读出来。可以定义一个数据容器来保存字符在字符流中的位置。当一个字符
* 第一次从字符流中读出来时，把它在字符流中的位置保存到数据容器里。当这个字符再次从字符流中读出来时，
* 那么它就不是只出现一次的字符，也就可以被忽略了。这时把它在数据容器里保存的值更新成一个特殊的值（
* 如负数值）。
为了尽可能高效地解决这个问题，需要在O（1）时间内往数据容器里插入一个字符，以及更新一个字符对应的值。
受面试题50的启发，这个数据容器可以用哈希表来实现。用字符的ASCII码作为哈希表的键值，而把字符对应的
位置作为哈希表的值。
* */
public class 字符流中第一个不重复的数 {
    int[] hashtable=new int[256];
    StringBuffer s=new StringBuffer();
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        s.append(ch);
        if(hashtable[ch]==0)
            hashtable[ch]=1;
        else hashtable[ch]+=1;
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        char[] str=s.toString().toCharArray();
        for(char c:str)
        {
            if(hashtable[c]==1)
                return c;
        }
        return '#';
    }
}
