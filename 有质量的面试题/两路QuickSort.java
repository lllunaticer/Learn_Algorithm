public class 两路QuickSort {
    public static void quickSort(int[] arr, int start, int end) {
        if (start >= 0 && end <= arr.length - 1 && start < end) {
            int low = start;
            int high = end;
            //记录一个关键值 spiltKey
            int spiltKey = arr[start];
            //遍历数组
            while (start < end) {
                //从后向前比较，直到遇到比spiltKey小的数
                while (start < end && arr[end] >= spiltKey) end--;
                //交换位置
                swap(arr, start, end);
                //从前向后比较，直到遇到有比spiltKey大的数
                while (start < end && arr[start] <= spiltKey) start++;
            }
            /**
             * 此时第一次循环比较结束，关键值的位置已经确定了。
             * 左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
             */
            arr[start] = spiltKey;//start = end, splitKey放到end或者说start位置了
            //递归，再对左半部分排序
            quickSort(arr, low, start - 1);
            //递归，再对右半部分排序
            quickSort(arr, start + 1, high);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {1, 9, 6, 7, 2, 3, 8, 5};
        quickSort(a, 0, a.length - 1);
        for(int i : a)
            System.out.print(i+" ");
    }

}
