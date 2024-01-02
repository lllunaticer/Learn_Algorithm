import java.util.PriorityQueue;

/*
 * 求一组数前k大的所有数， 最常用的方法是利用堆。需要额外O(n)的时间复杂度。
 * 但是对于空间复杂度要求比较高的场景，需要用类似快排的思想来做。
 * */
public class 求一组数前k大的数 {
    static int findLargestK(int[] arr, int start, int end, int k) {
        int i = start;
        int j = end - 1;
        int key = arr[start];

        while (i < j) {
            while (i < j && key <= arr[j])
                j--;
            swap(arr, i, j);
            while (i < j && arr[i] <= key)
                i++;
        }

        //运行到此, i == j, 且在arr[i]左边的元素都小于等于arr[i], 在arr[i]右边的元素都大于等于arr[i]

        //如果i到end的数的个数等于k, 返回i。以arr[i]为分界点刚好将数组分成了arr[i]后面是k个大于arr[i]的数
        if (end - i == k)
            return i;
        //如果i到end的个数大于k,则说明以arr[i]作为分界点，分出来大于arr[i]的数的数目比k多，需要在这个右边区间继续分。
        else if (end - i > k)
            return findLargestK(arr, i + 1, end, k);
        //如果i到end的个数小于k，则说明以arr[i]作为分界点，分出来大于arr[i]的数的数目比k少，需要在这个右边区间继续分。
        //但要注意，此时右边区间从i到end的数已经是符合要求的前k个数中的部分了，但是还不够k个，我们需要在左边的区间找
        //剩下的k-(end-i)个数字。
        else
            return findLargestK(arr, start, i, k - (end - i));
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //用堆求前k大的数，其实效率不如快排。堆输出的是排好序的前K大的数组，但如果只求前K大的数而不要求排序的话，这一步是多余的。
    static void findKBiggestHeap(int[] arr, int k) {
        //求前K大的数建小根堆
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for(int i = 0; i<k; i++)
            que.offer(arr[i]);
        for(int i = k; i<arr.length; i++){
            que.offer(arr[i]);
            que.poll();
        }
        while(!que.isEmpty())
            System.out.print(que.poll()+" ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] vec = {5, 7, 8, 3, 9, 3, 4};
        int k = 6;
        findKBiggestHeap(vec,k);

        int start = findLargestK(vec, 0, vec.length, k);
        for (int i = 0; i < k; i++)
            System.out.print(vec[start + i]+ " ");
        System.out.println();
    }
}
