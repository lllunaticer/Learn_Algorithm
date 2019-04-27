package 排序草稿本;

public class MergeSort {
    static void mergeSort(int[] arr) {
        if(arr == null || arr.length<2)
            return;
        sortProcess(arr,0, arr.length-1);
    }

    static void sortProcess(int[] arr, int L, int R) {
        if(L>=R)
            return;
        int mid = L + ((R-L)>>2);
        sortProcess(arr,L,mid);
        sortProcess(arr,mid+1,R);
        merge(arr,L,mid, R);
    }

    static void merge(int[] arr, int L, int mid, int R) {

    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        MergeSort.mergeSort(a);
        for (int i : a)
            System.out.print(i + ",");
    }
}
