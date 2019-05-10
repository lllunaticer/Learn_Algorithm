/*
*题目描述
输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
* */

/*
 * 题解
 *本题就是一个遍历,遍历每条路径,保存符合要求的路径
 * 如何保存并还原现场是一个难点
 * */

import java.util.ArrayList;

/**
 * public class TreeNode {
 * int val = 0;
 * TreeNode left = null;
 * TreeNode right = null;
 * <p>
 * public TreeNode(int val) {
 * this.val = val;
 * <p>
 * }
 * <p>
 * }
 */

public class 循环和递归_二叉树中和为某一值的路径 {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        dfs(root, target);
        return ans;
    }

    void dfs(TreeNode root, int sum) {
        if (root == null)//如果遍历到头(即到了空节点),直接返回
            return;
        path.add(root.val);//如果当前不是空节点,把该节点放进路径链表中
        sum -= root.val;//将目标值减去该节点
        //某条路径符合要求的条件是:1.该节点是叶子节点 (root.left == null && root.right == null)
//                             2. 并且到该节点时路径上所有节点总和为sum (&& sum == 0)
        if (root.left == null && root.right == null && sum == 0)
//            错误写法：
//            ans.add(path); path是变量，如果把path直接存进ans里面，存的是path的引用，而path在之后还会发生变化，所以存进ans里面的答案会随之
//            变化(记住ans.add(path)这样在ans里面存的是path的引用),所以需要拷贝当前path的副本,把副本存到ans里面去,这样后面path的变化不会影响到
//            存到ans里面的路径
            ans.add(new ArrayList<>(path));//将符合要求的路径保存进答案中
        dfs(root.left, sum);//递归遍历左子树
        dfs(root.right, sum);//递归遍历右子树
        //还原现场,因为我们不光遍历一个分支,还要遍历其他分支.所以在遍历完当前子树之后(不论当前路径是否符合要求)
        //要把当前节点造成的影响消除,在path中去掉当前的节点(因为当前节点所在的路径已经被考察过)
        path.remove(path.size()-1);
        //sum不用还原,因为sum传过来的时标量不是地址
    }

    public static void main(String[] args) {
        TreeNode h = new TreeNode(10);
        h.left = new TreeNode(5);
        h.right = new TreeNode(12);
        h.left.left = new TreeNode(4);
        h.left.right = new TreeNode(7);

        循环和递归_二叉树中和为某一值的路径 a = new 循环和递归_二叉树中和为某一值的路径();
        System.out.println(a.FindPath(h,22).toString());
    }
}
