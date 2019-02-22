/*Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4*/
package linkedList;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else {
            ListNode tmp1=l1;
            ListNode tmp2=l2;
            ListNode mergedHead = new ListNode(0);
            ListNode tmpH = mergedHead;
            while(tmp1!=null&&tmp2!=null){
                if(tmp1.val<=tmp2.val){
                    tmpH.next = tmp1;
                    tmpH = tmpH.next;
                    if(tmp1.next!=null){
                        tmp1 = tmp1.next;
                    }else {
                        tmpH.next = tmp2;
                        break;
                    }
                }else {
                    tmpH.next = tmp2;
                    tmpH = tmpH.next;
                    if(tmp2.next!=null){
                        tmp2 = tmp2.next;
                    }else {
                        tmpH.next = tmp1;
                        break;
                    }
                }
            }
            return mergedHead.next;
        }

        /*递归法
        * public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
}
        * */
    }

    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.addAtHead(1);
        l.addAtTail(2);
        l.addAtTail(4);

        LinkedList m = new LinkedList();
        m.addAtHead(1);
        m.addAtTail(3);
        m.addAtTail(4);

        MergeTwoLists n = new MergeTwoLists();
        n.mergeTwoLists(l.head,m.head);
    }
}
