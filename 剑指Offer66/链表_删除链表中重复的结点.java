/*
 * 在一个排序的链表中，存在重复的结点，
 * 请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * */
public class 链表_删除链表中重复的结点 {
    public ListNode deleteDuplication(ListNode pHead) {
//        设置一个虚拟节点,用来处理头节点有可能被删除的情况.
//        这个节点的值随便,因为在后面的匹配中,我们并不涉及到这个头节点的值.返回的头节点时候记得返回的是虚拟节点的next
        ListNode dumy = new ListNode(-1);
        dumy.next = pHead;

        ListNode p = dumy;//p一开始室指向虚拟节点不是头节点！
        /*每次考察,将q指向p后面一个节点*/
        while (p.next != null) {
            ListNode q = p.next;
//            如果q存在且p的下一个节点的值和q节点的值相等，说明遇到了重复节点, 就将p往后移动一个节点考察下一个节点
//            用图示模拟一下就懂了:
//            V 1 2 2 3 3
//            p q
//            * p q
            while (q!=null && p.next.val == q.val) q = q.next;
//            如果p的下下个节点是q,说明q从p的下个节点往后只移动了一次,变成了下下个节点,说明从p的下一个位置开始没有重复的元素, p往后移动移位考察下个位置
            if (p.next.next == q)
                p = p.next;
//            只要q的位置不在p的下下个位置,说明q移动了超过了一次,说明遇到了相同的元素,将p的next指针指向q,跳过中间所有相同的元素.
            else
                p.next = q;
        }
        return dumy.next;
    }

//    思考,本题是去除所有重复的元素,也就是说如果一个元素有超过了两个,则把他们全部删除;
//    如果要保留至少一个元素应该怎么处理?
//    双指针,如果第二个指针的值等于第一个指针,第一个的next直接指向第二个指针的next,第二个指针指向第一个指针的next.next
//    ListNode dumy = new ListNode(-1);
//    ListNode p1 = dumy;
//    while(p1.next!=null){
//        ListNode p2 = p1.next;
//          if(p2!=null && p1.val == p2.val){
//              p1.next = p2.next;
//          }
//    }
//    return dumy.next;
}
