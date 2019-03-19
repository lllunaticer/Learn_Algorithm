package 排序草稿本;

import java.util.Arrays;

public class QuickSort {
    static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }

    static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < arr[R]) {
                Swap.swap(arr, ++less, cur++);
            }
//          不加else是错误的：if(arr[cur]>arr[R])，因为这三种情况只能执行一个，如果不加else有可能上一步修改了cur使得它能够重新命中下一个if
            else if (arr[cur] > arr[R]) {
                Swap.swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        Swap.swap(arr, R, more);
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49};
        QuickSort.quickSort(a, 0, a.length - 1);
        for (int i : a)
            System.out.print(i + ",");
    }
}
