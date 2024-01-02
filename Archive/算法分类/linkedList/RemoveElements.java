package linkedList;

public class RemoveElements {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.addAtHead(1);
        l.addAtHead(1);
        l.addAtHead(1);
        RemoveElements r = new RemoveElements();
        r.removeElements(l.head, 1);
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        } else {
            ListNode vhead = new ListNode(0);
            vhead.next = head;
            ListNode temp = vhead;
            while (temp.next != null) {
                if (temp.next.val == val) {
                    temp.next = temp.next.next;
                } else {
                    temp = temp.next;
                }
            }
            return vhead.next;
        }
    }
}
