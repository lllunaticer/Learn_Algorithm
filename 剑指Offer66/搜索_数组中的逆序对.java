/*题目描述
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,
求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
输入描述:
题目保证输入的数组中没有的相同的数字
数据范围：
	对于%50的数据,size<=10^4
	对于%75的数据,size<=10^5
	对于%100的数据,size<=2*10^5

        示例1
        输入
        1,2,3,4,5,6,7,0
        输出
        7
*/

/*大致是使用归并排序，在归并的过程中统计逆序对数
* 待解决*/
public class 搜索_数组中的逆序对 {
    static int count = 0;

    public static int InversePairs(int [] array) {
        if(array == null || array.length == 0)
            return 0;
        sortProcess(array,0,array.length-1);

        return count%1000000007;
    }

    private static void sortProcess(int[] arr, int L, int R){
        if(L == R)
            return;
        else{
            int mid = L + ((R-L)>>1);
            sortProcess(arr,L,mid);
            sortProcess(arr,mid+1,R);
            merge(arr,L,mid,R);
        }
    }

    private static void merge(int[] arr, int l, int mid, int r){
        int[] help =  new int[r-l+1];
        int i = r-l;
        int p1 = mid;
        int p2 = r;

        while (p1>=l && p2>=mid+1){
            if(arr[p1]>arr[p2]){
                help[i--] = arr[p1--];
                count += p2-(mid+1)+1;
            }else{
                help[i--] = arr[p2--];
            }
        }
        while(p1>=l){
            help[i--] = arr[p1--];
        }
        while(p2>=mid+1)
            help[i--] = arr[p2--];

        i = 0;
        while (l<=r)
            arr[l++] = help[i++];
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,0};
        System.out.println(InversePairs(a));
        System.out.println();
    }

    //可以通过的代码
    public class Solution {
        public int InversePairs(int [] array) {
            if(array==null||array.length==0)
            {
                return 0;
            }
            int[] copy = new int[array.length];
            for(int i=0;i<array.length;i++)
            {
                copy[i] = array[i];
            }
            int count = InversePairsCore(array,copy,0,array.length-1);//数值过大求余
            return count;

        }
        private int InversePairsCore(int[] array,int[] copy,int low,int high)
        {
            if(low==high)
            {
                return 0;
            }
            int mid = (low+high)>>1;
            int leftCount = InversePairsCore(array,copy,low,mid)%1000000007;
            int rightCount = InversePairsCore(array,copy,mid+1,high)%1000000007;
            int count = 0;
            int i=mid;
            int j=high;
            int locCopy = high;
            while(i>=low&&j>mid)
            {
                if(array[i]>array[j])
                {
                    count += j-mid;
                    copy[locCopy--] = array[i--];
                    if(count>=1000000007)//数值过大求余
                    {
                        count%=1000000007;
                    }
                }
                else
                {
                    copy[locCopy--] = array[j--];
                }
            }
            for(;i>=low;i--)
            {
                copy[locCopy--]=array[i];
            }
            for(;j>mid;j--)
            {
                copy[locCopy--]=array[j];
            }
            for(int s=low;s<=high;s++)
            {
                array[s] = copy[s];
            }
            return (leftCount+rightCount+count)%1000000007;
        }
    }
}
