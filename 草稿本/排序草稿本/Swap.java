package 排序草稿本;

public class Swap {
    static void swap(int[] arr, int i, int j){
        int tmp;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
