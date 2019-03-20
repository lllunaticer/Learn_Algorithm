
//此二分查找用来判断给定数组中有无待查找对象
public class 二分查找_判断有无给定元素 {
//    递归
    static boolean binarySearch(int target, int[] list, int low, int high) {
        if (low > high)
            return false;
        else {
            int mid = low + ((high - low) >> 1);
            if (list[mid] > target)
                return binarySearch(target, list, low, mid - 1);
            else if (list[mid] < target)
                return binarySearch(target, list, mid + 1, high);
            else
                return true;
        }
    }

//    非递归
    static boolean binarySearch2(int target, int[] list) {
        int left = 0;
        int right = list.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);//
            if (list[mid] == target) {
                return true;
            } else if (list[mid] > target) {
                right = mid - 1;
            } else if (list[mid] < target) {
                left = mid + 1;
            }
        }
        return false;
    }
}
