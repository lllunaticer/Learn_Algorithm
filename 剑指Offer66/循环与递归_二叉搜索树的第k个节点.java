/*题目描述
给定一棵二叉搜索树，请找出其中的第k小的结点。例如，（5，3，7，2，4，6，8）中，按结点数值大小顺序
第三小结点的值为4。*/

/*如果按照中序遍历的顺序遍历一棵二叉搜索树，则遍历序列的数值是递增排序的。
因此，只需要用中序遍历算法遍历一棵二叉搜索树，我们就很容易找出它的第k大节点。*/

import java.util.Stack;

public class 循环与递归_二叉搜索树的第k个节点 {
    //中序递归
    int count = 0; //计数器

    TreeNode KthNode(TreeNode root, int k) {
        //递归截至条件
        if(root == null)
            return null;
        //从左子树中找到非空节点并返回
        TreeNode node = KthNode(root.left,k);
        if(node != null)
            return node;
        //中序记录返回的节点的个数
        if(++count == k)
            return root;
        //在右子树中找到非空节点并返回
        node = KthNode(root.right,k);
        if(node != null)
            return node;
        return null;
    }


    //中序循环
    TreeNode KthNode2(TreeNode root, int k) {
        if (root == null || k == 0)
            return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int count = 0;
        TreeNode node = root;
        do {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                count++;
                if (count == k)
                    return node;
                node = node.right;
            }
        } while (node != null || !stack.isEmpty());
        return null;
    }
}
