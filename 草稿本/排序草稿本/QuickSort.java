package 排序草稿本;

public class QuickSort {
    static void quickSort(int[] arr, int L, int R) {

    }

    static int[] partition(int[] arr, int L, int R) {
//
        return null;
    }

    static void swap(int[] arr, int m, int n){
        int tmp = arr[m];
        arr[m] = arr[n];
        arr[n] = tmp;
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49};
        QuickSort.quickSort(a, 0, a.length - 1);
        for (int i : a)
            System.out.print(i + ",");
    }
}
