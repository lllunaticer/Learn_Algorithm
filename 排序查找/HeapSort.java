import java.util.Arrays;

//堆排序
//堆是完全二叉树
//二叉树的底层可以用线性的结构来储存，也就是说可以用数组来储存一个二叉树，通过数组中下标的关系来表示这个堆。
// 设完全二叉树的一个节点在数组中的下标为i,
//则其父节点的下标应该为(i-1)/2,其左孩子节点应该是2*i+1, 其右孩子节点应该为2*i+2
//特例法: 0 的左孩子是1，右孩子是2: 0 = (1-1)/2 = (2-1)/2; 2*0+1 = 0; 2*0+2=2;
public class HeapSort {
    static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        else
            for (int i = 0; i < arr.length; i++)
                heapInsert(arr, i);

        int heapSize = arr.length;//堆的大小等于数组的长度
        //交换堆顶和最后一个元素
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    static void heapInsert(int[] arr, int index) {
        while (arr[(index - 1) / 2] < arr[index]) {//如果index=0, -1/2=0是根节点
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    //    如果堆中有某个元素变小了，将这个元素下沉以保持大根堆的过程heapify
    static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;//在用数组存储的堆中，节点i的左孩子节点是2*i+1, 右节点是2*i+2;
        //这里heapSize是最后一个元素，做堆排的时候，因为是从堆顶交换来的最大值，所以重新heapify要把它排除在外；
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49};
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
