//归并排序
//时间复杂度O（NlogN）,空间复杂度O（N）
//分治+外排的方法
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        else
            sortProcess(arr, 0, arr.length - 1);
    }

    private static void sortProcess(int[] arr, int L, int R) {
        if (L == R)
            return;
        else {
            int mid = L + ((R - L) >> 1);
//            根据Master公式求其时间复杂度：
            sortProcess(arr, L, mid);//T(N/2)
            sortProcess(arr, mid + 1, R);//T(N/2)
            merge(arr, L, mid, R);//O(N)
//            根据Master公式，其时间复杂度为T(N) = 2T(N/2)+O(N) = N*logN
        }

        /*二分法两个模板：
        * 区间分为[l, mid]和[mid+1,r],则更新操作位r = mid或者l = mid+1这是显而易见的，因为更新后
        * 的边界不应该出现在原来的区间里，而应该在另一个区间的边界上; 此时*/
    }

    //融合两个有序数组，使之成为一个更大的有序数组的方法，叫做外排
    private static void merge(int[] arr, int l, int mid, int r) {
//        空间复杂度O(体现在需要一个大小为数据量N的辅助数组help上)
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r)
            help[i++] = arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
//        两个必有且只有一个越界
        while(p1<=mid)
            help[i++] = arr[p1++];
        while(p2<=r)
            help[i++] = arr[p2++];

        i = 0;
        while(l<=r)
            arr[l++] = help[i++];
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        mergeSort(a);
        for(int i:a)
            System.out.print(i+",");
    }
}
