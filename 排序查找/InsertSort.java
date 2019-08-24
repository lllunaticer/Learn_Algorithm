//插入排序
//复杂度和数据状况有关系，如果本来数组的有序性就比较好则复杂度低
/*插入排序的基本思想是：每步将一个待排序的记录，按其关键码值的大小插入前面
  已经排序的文件中适当位置上，直到全部插入完为止。*/
public class InsertSort {
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        } else {
            /*i的左边是有序的，i的右边(包括i是无序的),每次把第i个位置的元素“插入”到i左边的有序区使得其
            * 仍然保持有序，并将i往后移动一位。
            * j = i-1是i左边有序区的临界位置，从此位置开始与i位置的数开始比较，如果 arr[j + 1] < arr[j]
            * 则交换元素并将j--，直到该元素放到了正确的位置使得arr[j + 1] > arr[j]*/
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
