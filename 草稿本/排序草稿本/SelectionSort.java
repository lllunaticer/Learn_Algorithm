package 排序草稿本;

public class SelectionSort {
    static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        else {
            for (int i = 0; i < arr.length; i++) {//此处可优化为i<arr.length-1
                int minIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    minIndex = arr[j] < arr[minIndex] ? j : minIndex;
                }
                Swap.swap(arr, i, minIndex);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        SelectionSort.selectionSort(a);
        for (int i : a)
            System.out.print(i+",");
    }
}
