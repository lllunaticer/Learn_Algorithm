/*题目描述
输入两个链表，找出它们的第一个公共结点。*/
import java.util.*;
public class 链表_两个链表的第一个公共节点 {
    //思路1，把两个链表往set里面放，第一个放不进去的节点即为交点。空间复杂度为O(m+n)
        public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
            Set<ListNode> set = new HashSet<>();
            while(pHead1!=null){
                set.add(pHead1);
                pHead1 = pHead1.next;
            }
            while(pHead2!=null){
                if(!set.add(pHead2))
                    return pHead2;
                pHead2 = pHead2.next;
            }
            return null;
        }

        /*思路2：分别把两个链表的节点放入两个栈里，这样两个链表的尾节点就位于两个栈的栈顶，
        接下来比较两个栈顶的节点是否相同。如果相同，则把栈顶弹出接着比较下一个栈顶，直到找
        到最后一个相同的节点。
        在上述思路中，我们需要用两个辅助栈。如果链表的长度分别为m和n，那么空间复杂度是O（m+n）。
        这种思路的时间复杂度也是O（m+n）。和最开始的蛮力法相比，时间效率得到了提高，相当于用
        空间消耗换取了时间效率。
*/

        //不需要格外空间的解法
        /*思路3：之所以需要用到栈，是因为我们想同时遍历到达两个栈的尾节点。当两个链表的长度不相同时，
        如果我们从头开始遍历，那么到达尾节点的时间就不一致。其实解决这个问题还有一种更简单的办
        法：首先遍历两个链表得到它们的长度，就能知道哪个链表比较长，以及长的链表比短的链表多几
        个节点。在第二次遍历的时候，在较长的链表上先走若干步，接着同时在两个链表上遍历，找到的
        第一个相同的节点就是它们的第一个公共节点。*/

        /*思路4:
        * 把这个题化为找环的入口，从前往后遍历两个链表并比较，遇到链表末尾了将其指向另一个链表的开头，这样
         * 就类似于找环的入口*/
        public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
            ListNode p1 = pHead1;
            ListNode p2 = pHead2;
            while (p1!=p2){
                p1 = (p1==null?pHead2:p1.next);
                p2 = (p2==null?pHead1:p2.next);
            }
            return p1;
        }

}
