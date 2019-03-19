package 排序草稿本;

public class MergeSort {
    static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        else
            sortProcess(arr, 0, arr.length - 1);
    }

    static void sortProcess(int[] arr, int left, int right){
        if(left==right)
            return;
        else {
            int mid = left+((right-left)>>1);
            sortProcess(arr,left,mid);
            sortProcess(arr, mid+1, right);
            merge(arr,left, mid, right);
        }

    }

    static void merge(int[] arr, int left, int mid, int right){
        int[] help = new int[right-left+1];
        int k = 0;
        int i = left;
        int j = mid+1;
        while(i<=mid&&j<=right){
            help[k++] = arr[i]<=arr[j]?arr[i++]:arr[j++];
        }
        while(i<=mid)
            help[k++] = arr[i++];
        while(j<=right)
            help[k++] = arr[j++];

        for(k = 0; k<help.length;k++)
            arr[left++] = help[k];
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        MergeSort.mergeSort(a);
        for(int i:a)
            System.out.print(i+",");
    }
}
