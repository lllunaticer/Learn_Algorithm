package linkedList;

public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        } else {
            ListNode temp = head;
            ListNode cursor = head;
            while (cursor.next != null) {
                cursor = cursor.next;
                if (temp.val != cursor.val) {
                    temp.next = cursor;
                    temp = temp.next;
                }
            }
            if(cursor.val==temp.val){
                temp.next = null;
            }
            return head;
        }


    }

    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.addAtHead(1);
        l.addAtHead(1);
        l.addAtTail(2);
        l.addAtTail(3);
        l.addAtTail(3);
        DeleteDuplicates d = new DeleteDuplicates();
        d.deleteDuplicates(l.head);
    }
}
