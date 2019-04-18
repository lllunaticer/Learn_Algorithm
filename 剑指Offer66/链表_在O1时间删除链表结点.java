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
public class 链表_在O1时间删除链表结点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
