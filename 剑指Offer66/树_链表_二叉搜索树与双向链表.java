/*
* 题目描述
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
要求不能创建任何新的结点，只能调整树中结点指针的指向。
见 树_链表_二叉搜索树与双向链表_题目描述.png
* */

/*
题解:
见 树_链表_二叉搜索树与双向链表.png
* */
public class 树_链表_二叉搜索树与双向链表 {
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null)
            return null;
        TreeNode[] sides = dfs(pRootOfTree);
        return sides[0];
    }

    TreeNode[] dfs(TreeNode root) {
        TreeNode[] res = {};
        if (root.left == null && root.right == null)
            res = new TreeNode[]{root, root};
        if (root.left != null && root.right != null) {
            TreeNode[] lsides = dfs(root.left);
            TreeNode[] rsides = dfs(root.right);
            lsides[1].right = root;
            root.left = lsides[1];
            rsides[0].left = root;
            root.right = rsides[0];
            res = new TreeNode[]{lsides[0], rsides[1]};
        }
        if (root.right == null && root.left != null) {
            TreeNode[] lsides = dfs(root.left);
            lsides[1].right = root;
            root.left = lsides[1];
            res = new TreeNode[]{lsides[0], root};
        }
        if (root.left == null && root.right != null) {
            TreeNode[] rsides = dfs(root.right);
            rsides[0].left = root;
            root.right = rsides[0];
            res = new TreeNode[]{root, rsides[1]};
        }
        return res;
    }
}
