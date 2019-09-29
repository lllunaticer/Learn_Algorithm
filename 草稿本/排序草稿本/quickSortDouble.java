package 排序草稿本;

public class quickSortDouble {
    static void quickSort(int[] arr, int start, int end) {
        if (start >= 0 && end < arr.length && start < end) {
            int low = start;
            int high = end;
            int base = arr[start];

            while (start < end) {
                while (start < end && arr[end] >= base)
                    end--;
                swap(arr, start, end);
                while (start < end && arr[start] <= base)
                    start++;
            }

            //arr[start] = base;
            quickSort(arr, low, start - 1);
            quickSort(arr, start + 1, high);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 8, 3, 9, 3, 4};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr)
            System.out.print(i + " ");
    }
}
