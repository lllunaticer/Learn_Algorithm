package 排序草稿本;


import java.util.Arrays;

public class HeapSort {
    //先建堆(使用heapInsert将数组所有元素排成堆)
    //再输出，每次输出堆顶元素(把堆顶和堆尾交换)，再使用heapify调整堆顶元素使之重新符合大根堆
    static void heapSort(int[] arr){
        if(arr == null || arr.length == 0)
            return;
        else{
            for(int i = 0; i < arr.length; i++)
                heapInsert(arr, i);
            int heapSize = arr.length-1;
            Swap.swap(arr,0, heapSize--);
            while(heapSize>0){
                heapify(arr,0,heapSize);
                Swap.swap(arr,0,heapSize--);
            }
        }
    }

    //本函数的作用将数组中位置为index的元素插入到堆中正确位置（对大根堆的任意节点，它都小于它的父节点）
    // --index位置的数不能比它的父节点大，如果比父节点大就交换它和父节点的位置
    static void heapInsert(int[] arr, int index){
        int father = (index-1)/2;
        while(arr[index]>arr[father]){
            Swap.swap(arr,index,father);
            index = father;
        }
    }

    //如果堆中有某个元素变小了（变得比它的字节点小了，因此不符合大根堆的性质了），就要将这个元素下沉以保持大根堆的属性
    //本函数的作用是当index位置上的元素变小了，就要调整它的位置保持大根堆。
    static void heapify(int[] arr, int index, int heapSize){
        int lchild = 2*index + 1;
        while (lchild<=heapSize){
            int largest = lchild + 1 <= heapSize && arr[lchild+1]>arr[lchild]?lchild+1:lchild;
            largest = arr[largest]>arr[index]?largest:index;
            if(largest == index) break;
            else{
                Swap.swap(arr,largest,index);
                index = largest;
                lchild = 2*index + 1;
            }
        }
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49};
        HeapSort.heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
