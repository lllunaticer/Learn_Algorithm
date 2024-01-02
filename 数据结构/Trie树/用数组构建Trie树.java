package Trie树;
/*题目及评测链接:https://www.acwing.com/activity/content/problem/content/883/1/
* 维护一个字符串集合，支持两种操作：

“I x”向集合中插入一个字符串x；
“Q x”询问一个字符串在集合中出现了多少次。
共有N个操作，输入的字符串总长度不超过 105，字符串仅包含小写英文字母。
输入格式
第一行包含整数N，表示操作数。
接下来N行，每行包含一个操作指令，指令为”I x”或”Q x”中的一种。
输出格式
对于每个询问指令”Q x”，都要输出一个整数作为结果，表示x在集合中出现的次数。
每个结果占一行。
数据范围
1≤N≤2∗104
输入样例：
5
I abc
Q abc
Q ab
I ab
Q ab
输出样例：
1
0
1
*
* */


import java.util.Scanner;

public class 用数组构建Trie树 {
    final int N = 100010;
    int[][] son = new int[N][26];
    int[] cnt = new int[N];
    int idx;

    void insert(char[] str) {
        int p = 0;
        for (int i = 0; i < str.length; i++) {
            int u = str[i] - 'a';
            if (son[p][u] == 0)
                son[p][u] = ++idx;
            p = son[p][u];
        }
        cnt[p]++;
    }

    int query(char[] str) {
        int p = 0;
        for (int i = 0; i < str.length; i++) {
            int u = str[i] - 'a';
            if (son[p][u] == 0)
                return 0;
            p = son[p][u];
        }
        return cnt[p];
    }

    public static void main(String[] args) {
        用数组构建Trie树 ss = new 用数组构建Trie树();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            if(s.startsWith("I")){
                ss.insert(s.substring(2).toCharArray());
            }else
                System.out.println( ss.query(s.substring(2).toCharArray()));
        }
    }
}
