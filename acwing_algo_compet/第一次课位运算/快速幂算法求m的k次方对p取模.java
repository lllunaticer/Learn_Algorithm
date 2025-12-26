package 第一次课位运算;

import java.io.*;

/*
* 求 a 的 b 次方对 p 取模的值。

输入格式
三个整数 a,b,p ,在同一行用空格隔开。

输出格式
输出一个整数，表示a^b mod p的值。

数据范围
1≤a,b,p≤109
输入样例：
3 2 7
输出样例：
2
* */
//题目来源： https://www.acwing.com/problem/content/91/
public class 快速幂算法求m的k次方对p取模 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true) {
            String[] parts = bf.readLine().split(" ");
            //用long型接收，防止大数据溢出: 126348976 982638476 938420413
            long m = Integer.valueOf(parts[0]);
            long k = Integer.valueOf(parts[1]);
            long p = Integer.valueOf(parts[2]);

            long res = 1 % p; //防止k == 0, 不走底下的循环
            m %= p;
            while (k != 0) {
                if ((k & 1) == 1)
                    res = res * m % p;
                k >>= 1;
                m = m * m % p;
            }
            log.write(String.valueOf(res)+ '\n');
            log.flush();
        }
    }
}
