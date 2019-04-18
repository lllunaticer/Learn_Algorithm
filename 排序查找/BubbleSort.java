//冒泡排序
//时间复杂度为O(N^2)，空间复杂度为O（N）
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr.length == 0 || arr.length == 1) {
            return;
        } else {
//            随着每轮比较的进行，都有一个大数沉到后面排好序，因此外层的循环长度应该递减
            for (int end = arr.length - 1; end > 0; end--) {
                for (int i = 0; i < end; i++) {
                    if (arr[i] > arr[i + 1]) {
                        swap(arr, i, i + 1);
                    }
                }
            }
        }

    }

    static void swap(int[] arr, int i, int j) {
//        不利用第三个变量交换两变量的位置。1.a和同一个数异或运算两次得到a本身 2.异或运算满足交换律
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[j] ^ arr[i];
        arr[j] = arr[j] ^ arr[i];
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        bubbleSort(a);
        for(int i:a)
            System.out.print(i+",");
    }
}
