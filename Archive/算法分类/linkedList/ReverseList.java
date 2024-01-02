/*Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?*/
package linkedList;

public class ReverseList {

    public static void main(String[] args) {
        ReverseList r = new ReverseList();

    }

    //迭代解法
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        } else {
            ListNode tail = new ListNode(head.val);
            tail.next = null;
            ListNode newhead = tail;
            ListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
                ListNode addhead = new ListNode(temp.val);
                addhead.next = newhead;
                newhead = addhead;
            }
            return newhead;
        }
    }

    //递归解法
//    public ListNode rreverseList(ListNode head) {
//        if(head.next==null){
//            return head;
//        }
//        rreverseList(head.next);
//
//    }

}
