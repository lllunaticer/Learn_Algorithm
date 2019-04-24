/*
*题目描述
请实现一个函数，
用来判断一颗二叉树是不是对称的。注意，
如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
* */

/*
*题解:https://www.acwing.com/solution/acwing/content/747/
* (二叉树，递归) O(n)O(n)
递归判断两个子树是否互为镜像。

两个子树互为镜像当且仅当：

两个子树的根节点值相等；
第一棵子树的左子树和第二棵子树的右子树互为镜像，且第一棵子树的右子树和第二棵子树的左子树互为镜像；
时间复杂度
从上到下每个节点仅被遍历一遍，所以时间复杂度是 O(n)O(n)。

* */
public class 树_对称的二叉树 {
    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot == null)
            return true;
        else{
            return dfs(pRoot.left, pRoot.right);
        }
    }

    boolean dfs(TreeNode p1, TreeNode p2){
        if(p1==null||p2==null)
            return p1 == null && p2 ==null;
        if(p1.val!=p2.val) return false;
        return dfs(p1.left, p2.right) && dfs(p1.right,p2.left);
    }
}
