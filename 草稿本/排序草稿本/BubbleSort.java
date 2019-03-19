package 排序草稿本;

public class BubbleSort {
    public static void bubleSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        else {
            for (int end = arr.length - 1; end > 0; end--) {
                for (int i = 0; i < end; i++) {
                    if (arr[i] > arr[i + 1])
                        Swap.swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        BubbleSort.bubleSort(a);
        for (int i : a)
            System.out.print(i+",");
    }
}
