/*
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建二叉树并返回。二叉树重建的结果是按层序遍历返回的数组。
 * */

import java.util.HashMap;
import java.util.Map;

/*
 * 1. 从前序中得到根节点1， 在中序中找到1所在的位置，1 的左边{4，7，2}是左子树的中序， 1的右边{5，3，8，6}是右子树中序
 * 2. 在中序中得到左子树的长度为3，右子树长度为4， 所以在前序中可以得到左子树的前序是{2，4，7}， 右子树前序是{3，5，6，8}
 * 3. 有了左子树的前序和中序，可以重复上面的步骤1和步骤2的过程； 同理有了右子树的前序和中序，也可以递归上述过程；
 * */
public class 第四题重建二叉树 {
    Map<Integer, Integer> hash = new HashMap<>();
    int[] pre, in;

    public TreeNode reConstructBinaryTree(int[] _pre, int[] _in) {
        pre = _pre;
        in = _in;

        for (int i = 0; i < pre.length; i++)
            hash.put(in[i], i);
        return dfs(0, pre.length - 1, 0, in.length - 1);
    }

    public TreeNode dfs(int pl, int pr, int il, int ir) {
        if (pl > pr)
            return null;
        else {
            TreeNode root = new TreeNode(pre[pl]);
//            int position = hash.get(pre[pl]);
            int positon = hash.get(root.val);
//            dfs（子树在前序数组中的左边界，子树在前序数组的右边界（=左边界+子树长度，长度可由中序数组推出），子树在中序数组中的左边界，子树在中序数组的右边界（=左边界+左子树长度，长度可由中序数组推出））
            TreeNode left = dfs(pl+1, pl+positon-il, il, positon-1);
            TreeNode right = dfs(pl+positon-il+1,pr, positon+1,il);
            root.left = left;
            root.right = right;
            return root;
        }
    }
}
