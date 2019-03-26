/*给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。*/
public class 第十五题二叉树的下一个节点 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
//        第一种情况，只要当前节点有右子树，则在中序遍历的条件下，
//        当前节点的下一个节点是它右子树的最左边的节点
        if(pNode.right!=null){
            pNode = pNode.right;
//            找到右子树中最左边的节点
            while(pNode.left!=null)
                pNode = pNode.left;
            return pNode;
        }else{
//            如果当前节点没有右子树，则根据中序的顺序，当前节点的后继是其
//            这样的一个祖先，即本节点所在的子树是该祖先节点的左子树
            while(pNode.next!=null && pNode == pNode.next.right)//只要当前节点不是父节点的左孩子，则把指针移到父节点，往上找，直到找到满足pNode == pNode.next.left则找到了后继
                pNode = pNode.next;
//            返回的是pNode的父节点
            return pNode.next;
        }

    }
}
