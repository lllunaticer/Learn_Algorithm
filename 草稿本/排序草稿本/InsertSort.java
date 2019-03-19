package 排序草稿本;

public class InsertSort {
    static void insertSort(int[] arr){
        if(arr==null||arr.length<2)
            return;
        else{
            for(int i = 1; i<=arr.length-1; i++){
                for(int j = i-1; j>=0&&arr[j+1]<arr[j];j--){
                    Swap.swap(arr, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 7, 10, 3, 9, 5, 4, 6, 8};
        InsertSort.insertSort(a);
        for (int i : a)
            System.out.print(i+",");
    }
}
