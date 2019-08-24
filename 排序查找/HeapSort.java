import java.util.Arrays;
/*
*堆就是用数组实现的二叉树，所有它没有使用父指针或者子指针。堆根据“堆属性”来排序，“堆属性”决定了树中节点的位置。
* 堆分为两种：最大堆和最小堆，两者的差别在于节点的排序方式。

在最大堆中，父节点的值比每一个子节点的值都要大。在最小堆中，父节点的值比每一个子节点的值都要小。这个属性对堆中
的每一个节点都成立。
建堆和调整的过程都要遵循堆的这个属性
* */

//堆排序
//堆是完全二叉树
//二叉树的底层可以用线性的结构来储存，也就是说可以用数组来储存一个二叉树，通过数组中下标的关系来表示这个堆。
//设完全二叉树的一个节点在数组中的下标为i, 可以用简单二叉树来助记: 父节点1的左孩子是3-->2i+1, 右孩子是4-->2i+2;
//则其父节点的下标应该为(i-1)/2,其左孩子节点应该是2*i+1, 其右孩子节点应该为2*i+2
//特例法: 0 的左孩子是1，右孩子是2: 0 = (1-1)/2 = (2-1)/2; 2*0+1 = 0; 2*0+2=2;
public class HeapSort {
    //先建堆(使用heapInsert将数组所有元素排成堆)
    //再输出，每次输出堆顶元素(把堆顶和堆尾交换)，再使用heapify调整堆顶元素使之重新符合大根堆
    static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        else
            //堆插入
            for (int i = 0; i < arr.length; i++)
                heapInsert(arr, i);
        //输出元素
        int heapSize = arr.length;//堆的大小等于数组的长度
        //交换堆顶和最后一个元素
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    //本函数的作用将数组中位置为index的元素插入到堆中正确位置（对大根堆的任意节点，它都小于它的父节点）
    // --index位置的数不能比它的父节点大，如果比父节点大就交换它和父节点的位置
    static void heapInsert(int[] arr, int index) {
        while (arr[(index - 1) / 2] < arr[index]) {//如果index=0, -1/2=0是根节点
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    //如果堆中有某个元素变小了（变得比它的字节点小了，因此不符合大根堆的性质了），就要将这个元素下沉以保持大根堆的属性
    //本函数的作用是当index位置上的元素变小了，就要调整它的位置保持大根堆。
    static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;//在用数组存储的堆中，节点i的左孩子节点是2*i+1, 右节点是2*i+2;
        //这里heapSize是最后一个元素，做堆排的时候，因为是从堆顶交换来的最大值，所以重新heapify要把它排除在外；
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index)
                break;
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
