package 并查集;
/*
* 题目及评测链接:https://www.acwing.com/problem/content/838/
* 一共有n个数，编号是1~n，最开始每个数各自在一个集合中。
现在要进行m个操作，操作共有两种：
“M a b”，将编号为a和b的两个数所在的集合合并，如果两个数已经在同一个集合中，则忽略这个操作；
“Q a b”，询问编号为a和b的两个数是否在同一个集合中；
输入格式
第一行输入整数n和m。
接下来m行，每行包含一个操作指令，指令为“M a b”或“Q a b”中的一种。
输出格式
对于每个询问指令”Q a b”，都要输出一个结果，如果a和b在同一集合内，则输出“Yes”，否则输出“No”。
每个结果占一行。
数据范围
1≤n,m≤105
输入样例：
4 5
M 1 2
M 3 4
Q 1 2
Q 1 3
Q 3 4
输出样例：
Yes
No
Yes
* */

//本题使用Scanner处理输入会TLE，要使用BufferedReader处理输入
//BufferedReader的效率比Scanner高10-20倍
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 用数组构建并查集 {
    static final int N = 100010;
    static int n, m;
    static int[] p = new int[N];//p[i]表示第i个节点的父亲节点

    //返回祖宗节点同时对路径进行压缩
    static int find(int i) {
        //路径压缩
        if(p[i]!=i)
            p[i] = find(p[i]);
        //路径未压缩
//        while (p[i] != i)
//            i = p[i];
        return p[i];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        n = Integer.parseInt(ss[0]);
        m = Integer.parseInt(ss[1]);
        for(int i = 1; i<=n; i++)
            p[i] = i;
        while(m -- >0){
            String[] str = br.readLine().split(" ");

            int a = Integer.valueOf(str[1]);
            int b = Integer.valueOf(str[2]);
            if(str[0].equals("M")){
                p[find(a)] = find(b);
            }else{
                if(p[find(a)] == p[find(b)])
                    System.out.println("Yes");
                else
                    System.out.println("No");
            }
        }
    }
}
