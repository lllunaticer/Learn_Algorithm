/*
* 题目描述
给一个链表，若其中包含环，
请找出该链表的环的入口结点，否则，输出null。
* */

import java.util.HashSet;


public class 链表_链表中环的入口结点 {
    /*
     * 题解1:见 链表_链表中环的入口节点.JPG
     * */
    public ListNode EntryNodeOfLoop1(ListNode pHead) {
        ListNode i = pHead;
        ListNode j = pHead;
        while (i != null && j != null) {
            i = i.next;
            j = j.next;
            if (j != null)
                j = j.next;
            else
                return null;
            if (i == j) {
                i = pHead;
                while (i != j) {
                    i = i.next;
                    j = j.next;
                }
                return i;
            }
        }
        return null;
    }
    /*
     * 题解2:用set依次保存节点，主要依据set不能存储重复元素的性质
     * */
    public ListNode EntryNodeOfLoop(ListNode pHead){
        HashSet<ListNode> res = new HashSet<>();
        while(pHead!=null){
            if(res.add(pHead))
                pHead = pHead.next;
            else
                return pHead;
        }
        return null;
    }
}
