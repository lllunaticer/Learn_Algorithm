package 最短路径算法模板;

import java.util.Arrays;

public class testhere {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Arrays.fill(arr, Integer.MAX_VALUE);
        int[] b = arr.clone();
        b[0] = 3;
        System.out.println(arr[0]);
        System.out.println(b[0]);
    }
}
