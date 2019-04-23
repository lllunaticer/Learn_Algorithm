package 排序草稿本;


import java.util.Arrays;

public class HeapSort {
    static void heapSort(int[] arr){
        if(arr == null || arr.length<2)
            return;
        else{
            for(int i = 0; i<arr.length;i++)
                heapInsert(arr,i);

            int heapSize = arr.length-1;
            Swap.swap(arr,0,heapSize);
            while(heapSize>0){
                heapify(arr,0,heapSize);
                heapSize -=1;
                Swap.swap(arr,0,heapSize);
            }
        }


    }

    static void heapInsert(int[] arr, int index){
        while(arr[index]>arr[(index-1)/2]){
            Swap.swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    static void heapify(int[] arr, int index, int heapSize){
        int left = index*2+1;
        while(left<heapSize){
            int largest = left+1<heapSize && arr[left+1]>arr[left]?left+1:left;
            largest = arr[index]>arr[largest]?index:largest;
            if(largest==index)
                break;
            else{
                Swap.swap(arr,index,largest);
                index = largest;
                left = 2*index+1;
            }
        }
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49};
        HeapSort.heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
