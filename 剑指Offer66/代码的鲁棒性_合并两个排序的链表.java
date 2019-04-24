/*
*题目描述
输入两个单调递增的链表，输出两个链表合成后的链表，
当然我们需要合成后的链表满足单调不减规则。
* */

/*
* 归并排序的步骤
* */
public class 代码的鲁棒性_合并两个排序的链表 {
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode l = head;

        while(list1!=null&&list2!=null){
            if(list1.val<list2.val){
                l.next = list1;
                l = l.next;
                list1 = list1.next;
            }else{
                l.next = list2;
                l = l.next;
                list2 = list2.next;
            }
        }

        if(list1!=null){
            l.next = list1;
        }
        if(list2!=null){
            l.next = list2;
        }

        return head.next;
    }
}
