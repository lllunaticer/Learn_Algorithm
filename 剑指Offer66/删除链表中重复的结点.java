/*
 * 在一个排序的链表中，存在重复的结点，
 * 请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * */
public class 删除链表中重复的结点 {
    public ListNode deleteDuplication(ListNode pHead) {
//        设置一个虚拟节点,用来处理头节点有可能被删除的情况.
//        这个节点的值随便,因为在后面的匹配中,我们并不涉及到这个头节点的值.返回的头节点时候记得返回的是虚拟节点的next
        ListNode dumy = new ListNode(-1);
        ListNode q = dumy;
        /*每次考察,将p指向q后面一个节点*/
        while (q.next != null) {
            ListNode p = q.next;
//            如果p存在且q的下一个节点的值和p节点的值相当, 就将p往后移动一个节点考察下一个节点
            while (p!=null && q.next.val == p.val) p = p.next;
//            如果q的下下个节点是p,说明q从p的下个节点往后只移动了一次,变成了下下个节点,说明从q的下一个位置开始没有重复的元素, q往后移动移位考察下个位置
            if (q.next.next == p)
                q = q.next;
//            只要p的位置不在q的下下个位置,说明p移动了超过了一次,说明遇到了相同的元素,将q移动到p的位置,跳过中间所有相同的元素.
            else
                q = p;
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
