import java.util.Arrays;

//快排
//
public class QuickSort {

    static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
//            随机快排， 每次将中间随机一个数和数列最后一个元素交换位置，放置逆序数列产生差的结果
            swap(arr, L+(int)(Math.random()*(R-L+1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }

    static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        int cur = L;
//        以arr[R]作为基准，有了随机快排，这里的arr[R]被重新洗牌
//        这里一次性处理了大于基准等于基准和小于基准的三种情况，速度比传统快排要快
        while (cur < more) {
            if (arr[cur] < arr[R]) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > arr[R]) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;


    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
