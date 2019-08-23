/*题目描述
统计一个数字在排序数组中出现的次数。*/

/*两次二分分别找左右边界*/

/*二分法找左右边界*/
public class 二分查找_数字在排序数组中出现的次数 {
    public static int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0)
            return 0;
        //二分查找模板找左边界
//        模板1: 将区间[l, r]划分成[l, mid]和[mid + 1, r]时，其更新操作是r = mid或者l = mid + 1;，计算mid时不需要加1。
        int l = 0, r = array.length-1;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(array[mid] >= k)
                r = mid;
            else
                l = mid + 1;
        }
        int left = l;

        l = 0;
        r = array.length-1;

        //二分查找模板找右边界
//        模板2：将区间[l, r]划分成[l, mid - 1]和[mid, r]时，其更新操作是r = mid - 1或者l = mid;，此时为了防止死循环，计算mid时需要加1。
        /*两个模板的记忆：
        * 1.必定有一个mid,要么在左区间要么在右区间
        * 2.更新时mid的值交叉等于，
        * [l,mid],[mid+1,r]时更新时r = mid; [l,mid-1],[mid,r]时更新时l = mid
        * 3. 更新mid时看区间中有没有mid+1, 没有的时候更新时要加1*/
        while(l<r){
            int mid = l + ((r-l)>>1)+1;
            if(array[mid] <= k)
                l = mid;
            else
                r = mid-1;
        }

        int right = l;

        if(array[right]==k)
            return right-left+1;
        else
            return 0;

    }

    public static void main(String[] args) {
        int[] a = {3,3,3,3,4,7};
        System.out.println(GetNumberOfK(a,3));
    }
}
