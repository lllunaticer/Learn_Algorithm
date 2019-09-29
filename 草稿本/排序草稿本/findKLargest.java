package 排序草稿本;

public class findKLargest {
    static int findK(int[] arr, int start, int end, int k) {
        int i = start;
        int j = end-1;
        int base = arr[start];

        while (i < j) {
            while (i < j && arr[j] >= base)
                j--;
            swap(arr, i, j);
            while (i < j && arr[i] <= base)
                i++;
        }
        //arr[end] = base;
        if (end-i == k)
            return i;
        else if (end-i > k)
            return findK(arr, i + 1, end, k);
        else
            return findK(arr, start, i, k - (end-i));
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 8, 3, 9, 3, 4};
        int k = 6;
        int start = findK(arr, 0, arr.length, k);
        for (int i = 0; i < k; i++)
            System.out.print(arr[start+i]+" ");
    }
}
