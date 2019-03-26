public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;//next指针指向中序遍历的条件下当前节点的下一个节点，从树的结构上看是指向父结点的指针

    TreeLinkNode(int val) {
        this.val = val;
    }
}