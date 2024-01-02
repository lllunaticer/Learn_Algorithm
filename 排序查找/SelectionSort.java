//选择排序
//时间复杂度为O(N^2),空间复杂度为O(1)
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        } else {
//            每轮都从未排序的数列中取出一个数，将其与后面所有未排序的数作比较，得到这些未排序数列里面的最小数，将它换到已排好序数列的后面，并扩大已排好序数列的范围。
            for (int i = 0; i < arr.length - 1; i++) {
                int minIndex = i;
//                i = 0作为第一个已排序列
                for (int j = i + 1; j < arr.length; j++) {
                    minIndex = arr[j] < arr[minIndex] ? j : minIndex;
                }
                swap(arr, i, minIndex);
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
//        此处不能用异或来完成交换，因为如果i=j, 两个相同的数异或等于0，“arr[j] = arr[j] ^ arr[i]”会将arr[i]和arr[j]同时置为0，这样就丢失了所有信息。
//        如果i和j不相等，但a[i]==a[j]是可以完成异或交换功能的，因为0和任何数异或等于其本身
//        arr[j] = arr[j] ^ arr[i];
//        arr[i] = arr[j] ^ arr[i];
//        arr[j] = arr[j] ^ arr[i];
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        selectionSort(a);
        for (int i : a)
            System.out.print(i + ",");
    }
}
