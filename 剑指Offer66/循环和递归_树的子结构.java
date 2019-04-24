/*
*题目描述
输入两棵二叉树A，B，判断B是不是A的子结构。
（ps：我们约定空树不是任意一个树的子结构）
* */

/*
*题解:
* 代码分为两个部分：

遍历树A中的所有非空节点R；
判断树A中以R为根节点的子树是不是包含和树B一样的结构，且我们从根节点开始匹配；
对于第一部分，我们直接递归遍历树A即可，遇到非空节点后，就进行第二部分的判断。

对于第二部分，我们同时从根节点开始遍历两棵子树：

如果树B中的节点为空，则表示当前分支是匹配的，返回true；
如果树A中的节点为空，但树B中的节点不为空，则说明不匹配，返回false；
如果两个节点都不为空，但数值不同，则说明不匹配，返回false；
否则说明当前这个点是匹配的，然后递归判断左子树和右子树是否分别匹配即可；
时间复杂度
最坏情况下，我们对于树A中的每个节点都要递归判断一遍，每次判断在最坏情况下需要遍历完树B中的所有节点。
所以时间复杂度是 O(nm)O(nm)，其中 nn 是树A中的节点数， mm 是树B中的节点数。

链接：https://www.acwing.com/solution/acwing/content/745/
* */
public class 循环和递归_树的子结构 {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1 == null || root2 == null) return false;
        if(isPart(root1, root2)) return true;
        return HasSubtree(root1.left, root2)||HasSubtree(root1.right, root2);
    }

    public boolean isPart(TreeNode p1, TreeNode p2){
        if(p2 == null) return true;
        if(p1 == null || p1.val!=p2.val) return false;
        return isPart(p1.left, p2.left)&&isPart(p1.right, p2.right);
    }
}
