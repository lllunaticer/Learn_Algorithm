/*题目描述
输入一个链表，反转链表后，输出新链表的表头。*/
//解释见本文件夹下的 反转链表详细解释.png
public class 第十四题反转链表 {
    //    递归方法
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode h = ReverseList(head.next);//带着成为新头节点的尾节点一直到递归最外层成为头
        head.next.next = head;
        head.next = null;
        return h;
    }
//    https://blog.csdn.net/geekmanong/article/details/51097196 经典单链表反转递归与非递归算法
//    非递归方法
//    利用两个结点指针和一个中间结点指针temp(用来记录当前结点的下一个节点的位置)，分别指向当前结点和前一个结点，
//    每次循环让当前结点的指针域指向前一个结点即可，翻转结束后，记得将最后一个节点的链域置为空。

    public ListNode ReverseList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        else {
            ListNode prev = head;
            ListNode cur = head.next;
            ListNode temp;

            while (cur != null) {
                temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
            }
            head.next = null;
            return prev;
        }
    }
}
