package 单调队列;
/*
* 题目及评测链接：https://www.acwing.com/problem/content/156/
* 给定一个大小为n≤10的6次方的数组。
有一个大小为k的滑动窗口，它从数组的最左边移动到最右边。
您只能在窗口中看到k个数字。
每次滑动窗口向右移动一个位置。
以下是一个例子：
该数组为[1 3 -1 -3 5 3 6 7]，k为3。

    窗口位置	           最小值	    最大值
[1 3 -1] -3 5 3 6 7	    -1	          3
1 [3 -1 -3] 5 3 6 7	    -3	          3
1 3 [-1 -3 5] 3 6 7	    -3	          5
1 3 -1 [-3 5 3] 6 7	    -3	          5
1 3 -1 -3 [5 3 6] 7    	 3	          6
1 3 -1 -3 5 [3 6 7]	     3	          7
您的任务是确定滑动窗口位于每个位置时，窗口中的最大值和最小值。
输入格式
输入包含两行。
第一行包含两个整数n和k，分别代表数组长度和滑动窗口的长度。
第二行有n个整数，代表数组的具体数值。
同行数据之间用空格隔开。
输出格式
输出包含两个。
第一行输出，从左至右，每个位置滑动窗口中的最小值。
第二行输出，从左至右，每个位置滑动窗口中的最大值。
输入样例：
8 3
1 3 -1 -3 5 3 6 7
输出样例：
-1 -3 -3 -3 3 3
3 3 5 5 6 7
* */

import java.util.Scanner;

public class 滑动窗口 {
    final int N = 1000010;

    int n, k;
    int[] a = new int[N];
    int[] q = new int[N];

    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
    }

    int[] minWindow() {
        initialization();
        int[] res = new int[n - k + 1];
        int idx = 0;
        int hh = 0;//hh是单调队列的队头，从此处进入新数据。
        int tt = -1;//tt是单调队列的队尾，队尾元素是单调队列的最小元素，每次取最小值时直接取这个位置的元素。
        for (int i = 0; i < n; i++) {
            if (hh <= tt && q[hh] < i - k + 1) hh++;
            while (hh <= tt && a[q[tt]] >= a[i]) tt--;
            q[++tt] = i;
            if (i >= k - 1)
                res[idx++] = a[q[hh]];
        }
        return res;
    }

    int[] maxWindow() {
        initialization();
        int[] res = new int[n - k + 1];
        int idx = 0;
        int hh = 0;//hh是单调队列的队头，从此处进入新数据。
        int tt = -1;//tt是单调队列的队尾，队尾元素是单调队列的最小元素，每次取最小值时直接取这个位置的元素。
        for (int i = 0; i < n; i++) {
            if (hh <= tt && q[hh] < i - k + 1) hh++;
            while (hh <= tt && a[q[tt]] <= a[i]) tt--;
            q[++tt] = i;
            if (i >= k - 1)
                res[idx++] = a[q[hh]];
        }
        return res;
    }

    public static void main(String[] args) {
        滑动窗口 s = new 滑动窗口();
        int[] min = s.minWindow();
        for (int i : min)
            System.out.print(i + " ");
        System.out.println();
        int[] max = s.maxWindow();
        for (int i : max)
            System.out.print(i + " ");
        System.out.println();
    }
}
