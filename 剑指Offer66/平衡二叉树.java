/*题目描述
输入一棵二叉树，判断该二叉树是否是平衡二叉树。*/
public class 平衡二叉树 {
    /*有了求二叉树的深度的经验之后再解决这个问题，我们很容易就能想到一种思路：在遍历树的每个节点的时候，
    调用函数TreeDepth得到它的左、右子树的深度。如果每个节点的左、右子树的深度相差都不超过1，那么按照
    定义它就是一棵平衡二叉树。这种思路对应的代码如下：*/

    /*上面的代码固然简洁，但我们也要注意到由于一个节点会被重复遍历多次，这种思路的时间效率不高。例如，
    在函数IsBalance中输入图6.2中的二叉树，我们将首先判断根节点（节点1）是不是平衡的。此时我们往函
    数TreeDepth里输入左子树的根节点（节点2）时，需要遍历节点4、5、7。
     接下来判断以节点2为根节点的子树是不是平衡树的时候，仍然会遍历节点4、5、7。毫无疑问，重复遍历
     同一个节点会影响性能。*/
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null)
            return true;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int diff = left - right;
        if(diff<-1||diff>1)
            return false;
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    int TreeDepth(TreeNode root){
        if(root == null)
            return 0;
        int nLeft = TreeDepth(root.left);
        int nRight = TreeDepth(root.right);

        return nLeft>nRight?++nLeft:++nRight;
    }

    /*上面的代码固然简洁，但我们也要注意到由于一个节点会被重复遍历多次，这种思路的时间效率不高。例如，
    在函数IsBalance中输入图6.2中的二叉树，我们将首先判断根节点（节点1）是不是平衡的。此时我们往函
    数TreeDepth里输入左子树的根节点（节点2）时，需要遍历节点4、5、7。
     接下来判断以节点2为根节点的子树是不是平衡树的时候，仍然会遍历节点4、5、7。毫无疑问，重复遍历
     同一个节点会影响性能。*/

    /*思路2：如果我们用后序遍历的方式遍历二叉树的每个节点，那么在遍历到一个节点之前我们就已经遍历
    了它的左、右子树。只要在遍历每个节点的时候记录它的深度（某一节点的深度等于它到叶节点的路径的长
    度），我们就可以一边遍历一边判断每个节点是不是平衡的。

    如果改为从下往上遍历，如果子树是平衡二叉树，则返回子树的高度；如果发现子树不是平衡二叉树，则直
    接停止遍历，这样至多只对每个结点访问一次。
    下面是这种思路的参考代码：*/
    public boolean IsBalanced_Solution2(TreeNode root) {
        return getDepth(root) != -1;
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        int left = getDepth(root.left);
        if (left == -1) return -1;
        int right = getDepth(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }
}
