//插入排序
//复杂度和数据状况有关系，如果本来数组的有序性就比较好则复杂度低
public class InsertSort {
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        } else {
            for (int i = 1; i < arr.length; i++) {
//如果数组的有序性比较好，如1，2，3，4，5，则arr[j + 1] < arr[j]这个条件可以使得比较提前终止，
//如果数组刚好是逆序的，如5，4，3，2，1，则需要从j一直比较到i=0；
                for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[j] ^ arr[i];
        arr[j] = arr[j] ^ arr[i];
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        insertSort(a);
        for (int i : a)
            System.out.print(i + ",");
    }
}
