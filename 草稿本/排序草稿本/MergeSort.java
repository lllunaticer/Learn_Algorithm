package 排序草稿本;

public class MergeSort {
    static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        sortProcess(arr, 0, arr.length - 1);
    }

    static void sortProcess(int[] arr, int l, int r) {
        if (l == r)
            return;
        //分成[l,mid]和[mid+1,r]
        int mid = l + ((r - l) >> 1);
        sortProcess(arr, l, mid);
        sortProcess(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l, p2 = mid + 1, i = 0;
        while (p1 <= mid && p2 <= r)
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        while (p1 <= mid)
            help[i++] = arr[p1++];
        while (p2<=r)
            help[i++] = arr[p2++];
        i = 0;
        for(i = 0;i<help.length;i++)
            arr[l+i] = help[i];
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        MergeSort.mergeSort(a);
        for (int i : a)
            System.out.print(i + ",");
    }
}
