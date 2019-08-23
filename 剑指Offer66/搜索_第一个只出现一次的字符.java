/*题目描述
在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
并返回它的位置, 如果没有则返回 -1（需要区分大小写）.*/

/*第一次遍历用map记录下来所有字符出现的次数，最终再从头遍历一遍字符串，在map中查看依次查看每个字符出现的次数
，第一次为1的即为答案。时间复杂度为O(n)*/
import java.util.*;
public class 搜索_第一个只出现一次的字符 {
    public int FirstNotRepeatingChar(String str) {
        char[] arr = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : arr) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }
        int res = 0;
        int i = 0;
        for (i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == 1) {
                res = i;
                break;
            }
        }
        return i == arr.length ? -1 : res;
    }

    /*可以用数组来节约空间*/
    /*
    * 利用每个字母的ASCII码作hash来作为数组的index。首先用一个58长度的数组来存储每个字母出现
    * 的次数，为什么是58呢，主要是由于A-Z对应的ASCII码为65-90，a-z对应的ASCII码值为97-122，
    * 而每个字母的index=int(word)-65，比如g=103-65=38，而数组中具体记录的内容是该字母出现
    * 的次数，最终遍历一遍字符串，找出第一个数组内容为1的字母就可以了，时间复杂度为O(n)*/

    public static int solution2(String str){
        int[] words = new int[58];
        for(int i = 0;i<str.length();i++){
            words[((int)str.charAt(i))-65] += 1;
        }
        for(int i=0;i<str.length();i++){
            if(words[((int)str.charAt(i))-65]==1)
                return i;
        }
        return -1;
    }
}
