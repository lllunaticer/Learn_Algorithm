/*
* 题目描述
输入一个链表，输出该链表中倒数第k个结点。
* */

/*
* */
public class 代码的鲁棒性_链表中倒数第k个结点 {
    public static ListNode FindKthToTail(ListNode head,int k) {

        int i = 0;
        ListNode l = head;
        while(l!=null){
            i++;
            l = l.next;
        }

        int m = i-k+1;
        if (k>i)
            return null;
        l = head;
        while(m>1){//注意理清节点以及遍历次数，一个好的想法是可以举例子来判断边界是否正确
            m--;
            l = l.next;
        }
        return l;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l = head;
        l.next = new ListNode(2);
        l = l.next;
        l.next = new ListNode(3);
        l = l.next;
        l.next = new ListNode(4);
        l = l.next;
        l.next = new ListNode(5);

        FindKthToTail(head, 1);
    }

}
