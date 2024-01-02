package 动态规划;
/*
* 最长上升子序列（LIS）问题：给定长度为n的序列a，从a中抽取出一个子序列，
* 这个子序列需要单调递增。问最长的上升子序列（LIS）的长度。
*　e.g. 1,5,3,4,6,9,7,8的LIS为1,3,4,6,7,8，长度为6。
* */

import java.util.Scanner;

public class 最长上升子序列问题LSI {
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        while(in.hasNextLine()){
            String[] s = in.nextLine().split(",");
            //为了方便编号，从1开始编号
            int[] arr = new int[s.length+1];
            int[] f = new int[s.length+1];
            for(int i = 1; i<=s.length; i++){
                arr[i] = Integer.valueOf(s[i-1]);
                f[i] = 1;
            }

            for(int x = 1; x<=s.length;x++){
                for(int p = 1; p<x;p++)
                    if(arr[p]<arr[x])
                        f[x] = Math.max(f[x],f[p]+1);
                    System.out.println("f["+x+"]="+f[x]);
            }

            int ans = 0;
            for(int x = 1; x<=s.length;x++)
                ans = Math.max(ans,f[x]);

            System.out.println(ans);

        }
    }
}
