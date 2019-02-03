/*
* 234. Palindrome Linked List
Easy

1375

208

Favorite

Share
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
* */
package linkedList;

public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null){
            return true;
        } else{
            ReverseList r = new ReverseList();
            ListNode newhead = r.reverseList(head);
            ListNode tmp1 = head;
            ListNode tmp2 = newhead;
            while(tmp1.next!=null){
                if(tmp1.val!=tmp2.val){
                    return false;
                }
                tmp1 = tmp1.next;
                tmp2 = tmp2.next;
            }
            return true;
        }

    }

}
