package 排序草稿本;

public class QuickSort {
    static void quickSort(int[] arr, int L, int R) {
        if(L<R){
            int[] p = partition(arr, L, R);
            quickSort(arr,L,p[0]-1);
            quickSort(arr,p[1]+1,R);
        }
    }

    static int[] partition(int[] arr, int L, int R) {
//
        int less = L-1;
        int more = R;
        int cur = L;
        int base = arr[R];
        while(cur<more){
            if(arr[cur]<base){
                swap(arr, ++less,cur++);
            }else if(arr[cur]>base){
                swap(arr,--more,cur);
            }else{
                cur++;
            }
        }
        swap(arr,R,more);
        return new int[]{less+1,more};
    }

    static void swap(int[] arr, int m, int n){
        int tmp = arr[m];
        arr[m] = arr[n];
        arr[n] = tmp;
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49};
        QuickSort.quickSort(a, 0, a.length - 1);
        for (int i : a)
            System.out.print(i + ",");
    }
}
