//https://www.acwing.com/blog/content/114/
//这个模板返回待查找元素在数组中的位置
//如果没有匹配的元素，则返回离该元素最近的元素的位置.
//第一个函数binarySearch_SmallestLarge返回的是离target最近的比target大的元素
//第二个函数binarySearch_LargestSmall 返回的是离target最近的比target小的元素
public class 二分查找模板_返回最接近目标值下标 {
    //    当我们将需要查找的区间[L, R]分成[L, mid],[mid+1, R]时， 计算mid不需要加1,mid = (L+R)>>1, 更新边界时使用 L = mid+1 或 R = mid。
//    这一点也比较好理解，因为除2的操作时向下取整的，所以计算出来的mid是属于左半边界的

    //    区间划分为[L,mid],[mid+1, R]. 返回待查找元素的下标，如果不存在，则返回最接近的大于该元素的元素下标
    static int binarySearch_SmallestLarge(int target, int[] arr) {
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] < target)//注意此处是小于号，如果arr[mid]==target条件成立，则必须将mid保留进判断区间中，
                L = mid + 1;      //但此处的 L = mid+1会将mid排除出判断区间，故将等于的情况留到else中交给R保留
            else
                R = mid;
        }
        return L;
    }

    //区间划分为[L，mid-1],[mid, R]。返回待查找元素的下标，如果不存在，则返回最接近的小于该元素的元素下标。此时计算mid要+1
    static int binarySearch_LargestSmall(int target, int[] arr) {
        int L = 0;
        int R = arr.length - 1;
        while (L < R){
            int mid = L + 1 + ((R-L)>>1);
            if(arr[mid]<=target)//注意此处是小于等于号，如果arr[mid]==target条件成立，则必须将mid保留进判断区间中，也就是L=mid
                L = mid;
            else
                R = mid-1;
        }
        return L;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5, 6};
        System.out.println(a[binarySearch_SmallestLarge(4, a)]);
        System.out.println(a[binarySearch_LargestSmall(4, a)]);
    }
}
