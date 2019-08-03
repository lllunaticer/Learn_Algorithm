package ACwing打卡;

public class Solution24 {
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = head;
        ListNode p2 = head.next;

        ListNode tmp = p2.next;
        p2.next = p1;
        p1.next = tmp;
        dummy.next = p2;

        ListNode l;

        while(p1.next!=null && p1.next.next!=null){
            l = p1;
            p1 = p1.next;
            p2 = p1.next;

            tmp = p2.next;
            p2.next = p1;
            p1.next = tmp;
            l.next = p2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);
        a.next.next.next = new ListNode(4);
        swapPairs(a);
    }
}
