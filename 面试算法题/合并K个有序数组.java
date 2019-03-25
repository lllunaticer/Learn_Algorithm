import java.util.Comparator;
import java.util.PriorityQueue;

/*
*本题是本人字节跳动一面考的算法题
* 给定K个有序数组，每个数组有n个元素，想把这些数组合并成一个有序数组

可以利用最小堆完成，时间复杂度是O(nklogk)，具体过程如下：

创建一个大小为n*k的数组保存最后的结果
创建一个大小为k的最小堆，堆中元素为k个数组中的每个数组的第一个元素
重复下列步骤n*k次：
每次从堆中取出最小元素（堆顶元素），并将其存入输出数组中
用堆顶元素所在数组的下一元素将堆顶元素替换掉，
如果数组中元素被取光了，将堆顶元素替换为无穷大。每次替换堆顶元素后，重新调整堆
初始化最小堆的时间复杂度O（k），总共有kn次循环，每次循环调整最小堆的时间复杂度是O（logk）
，所以总的时间复杂度是O（knlogk）
* */
public class 合并K个有序数组 {
    public static ListNode merge(ListNode[] arr) {
        int k = arr.length;
        ListNode dumy = new ListNode(-1);
        ListNode p = dumy;

        PriorityQueue<ListNode> maxHeap = new PriorityQueue<>(k, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < k; i++)
            maxHeap.offer(arr[i]);

        while (!maxHeap.isEmpty()) {
            ListNode tmp = maxHeap.poll();
            if (tmp.next != null)
                maxHeap.offer(tmp.next);
            p.next = tmp;
            p = p.next;
        }
        return dumy.next;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        a1.next = new ListNode(2);
        a1.next.next = new ListNode(3);
        a1.next.next.next = new ListNode(100);

        ListNode b1 = new ListNode(1);
        b1.next = new ListNode(2);
        b1.next.next = new ListNode(3);

        ListNode c1 = new ListNode(2);
        c1.next = new ListNode(3);
        c1.next.next = new ListNode(5);

        ListNode[] arr = {a1, b1, c1};

        ListNode res = merge(arr);
        while (res!= null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
