package linkedList;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumHead = new ListNode(0);
        int carry = 0;
        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        ListNode sump = sumHead;
        while (tmp1 != null && tmp2 != null) {
            sump.next = new ListNode((tmp1.val + tmp2.val + carry) % 10);
            carry = (tmp1.val + tmp2.val + carry) / 10;
            sump = sump.next;

            if (tmp1.next != null) {
                tmp1 = tmp1.next;
                if (tmp2.next != null) {
                    tmp2 = tmp2.next;
                } else {
                    while(tmp1!=null){
                        sump.next = new ListNode((tmp1.val+carry)%10);
                        carry = (tmp1.val+carry)/10;
                        sump = sump.next;
                        tmp1 = tmp1.next;
                    }
                    if(carry!=0)
                        sump.next = new ListNode(carry);
                    break;
                }
            } else {
                if(tmp2.next==null){
                    if(carry!=0)
                        sump.next = new ListNode(1);
                    break;
                }
                while(tmp2.next!=null){
                    tmp2 = tmp2.next;
                    sump.next = new ListNode((tmp2.val+carry)%10);
                    carry = (tmp2.val+carry)/10;
                    sump = sump.next;
                }
                if(carry!=0)
                    sump.next = new ListNode(carry);
                break;
            }
        }

        return sumHead.next;

    }

    public static void main(String[] args) {
        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();
        l1.addAtHead(0);
//        l1.addAtTail(8);
//        l1.addAtTail(3);

        l2.addAtHead(7);
        l2.addAtTail(3);
//        l2.addAtTail(4);

        AddTwoNumbers a = new AddTwoNumbers();
        ListNode p = a.addTwoNumbers(l1.head, l2.head);
        System.out.println(" ");
    }
}
