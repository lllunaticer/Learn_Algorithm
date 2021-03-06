/*题目：https://www.acwing.com/problem/content/85/
* 给定单向链表的一个节点指针，定义一个函数在O(1)时间删除该结点。

假设链表一定存在，并且该节点一定不是尾节点。

样例
输入：链表 1->4->6->8
      删掉节点：第2个节点即6（头节点为第0个节点）

输出：新链表 1->4->8
* */

/*
* 题解：
* 将链表的下一个节点的值赋给当前节点，然后跳过当前节点的下一个节点
* 将下一个节点复制到当前节点的位置取代当前节点，然后删除掉下一个节点；这样相当于删除了本节点。
* */

/*
* 把下一个节点的内容复制到需要删除的节点上覆盖原有的内容，再把下一个节点删除; 如果要删除的节点位于链表的尾部，
* 那么它就没有下一个节点，仍然从链表的头节点开始，顺序遍历得到该节点的前序节点，并完成删除操作。
如果链表中只有一个节点，而我们又要删除链表的头节点（也是尾节点），那么，此时我们在删除节点之后，还需要把链表
的头节点设置为nullptr。
*/
public class 链表_在O1时间删除链表结点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
