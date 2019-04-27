import java.util.Arrays;

//快排
//时间复杂度最好为O(NlogN). 数组逆序的时候最差，时间复杂度为O(N^2),可以通过随机快排的方式使得其长期时间复杂度期望为O（N*logN）
//空间复杂度最好为O（logN）,数组逆序的时候最差，空间复杂度为O(N)，额外空间主要是每次partition函数返回的二元数组造成的。
//通过随机快排的方式使得其长期时间复杂度期望为O（logN）
//所有递归函数都可以改为非递归版本，因为递归的本质行为是系统在帮我们压栈。改为非递归就是改成我们自己来压栈
// 在工程上是不允许递归行为存在的，因为递归过深可能会导致系统栈爆满，系统不稳定。因此工程上的快排都是非递归版本实现的。
//库函数都是高度优化过的
public class QuickSort {


    static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
//            随机快排， 每次将中间随机一个数和数列最后一个元素交换位置，防止逆序数列产生差的结果
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }

    static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        int cur = L;
        int base = arr[R];
//        以arr[R]作为基准，有了随机快排，这里的arr[R]被重新洗牌
//        这里一次性处理了大于基准等于基准和小于基准的三种情况，速度比传统快排要快--三路快排
        while (cur < more) {
            if (arr[cur] < base) {
                // cur++,因为换到cur位置上的一定是比基准arr[R]小的数，直接将其扩到less范围去，且cur指向下一位置
                swap(arr, ++less, cur++);
            } else if (arr[cur] > base) {
                //交换到cur位置上的数大小位置，交换过去的数一定大于基准arr[R]， 故more--,将其扩到more区域， 但cur位置不变
                //因为从--more交换过来的元素大小不确定，还需要判断
                swap(arr, --more, cur);
            } else {
                //当前位置和基准arr[R]相等，不扩到less区域和more区域，放在相等区域
                cur++;
            }
        }
        //最后将基准交换到more区域的下一位置
        swap(arr, more, R);
       // 返回相等区域下标,注意此时more位置上是交换过来的基准值，不用加1
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
