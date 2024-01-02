/*
*题目描述
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
* */

/*
*题解：
*算法1
(后序遍历，递归) O(n)O(n)
后序遍历，数组的最后一个值是二叉树的根节点

所以前面的值一部分小于根节点，一部分大于根节点，因为是二叉搜索树

找到比根节点小的，则为左子树

比根节点大的为右子树

然后递归整个数组即可
* */
//本题是week4的题目
public class 循环和递归_二叉搜索树的后序遍历序列 {
    int[] seq;
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0 ) return false;
        seq = sequence;
        return dfs(0, sequence.length-1);
    }

    boolean dfs(int l, int r){
        if(l >= r) return true;//如果子树的长度为0，则遍历到叶子节点，为真
        int root = seq[r]; //后序遍历的最后一个值是搜索二叉树的根节点
        int k = l;
        while(k<r && seq[k]<root)
            k++;//找到以root为根的左子树序列的位置（由于二叉搜索树的属性，其左子树都比根节点要小）
        for(int i = k; i < r; i++){
            if(seq[i]<root){
                return false;//从左子树的右边开始是右子树，根据二叉搜索树的性质，根节点的右子树
                             //都比根节点要大，如果出现了比根节点小的节点，则说明这棵树不合定义，返回false
            }
        }
        return dfs(l, k-1) && dfs(k+1,r);//递归检查左右子树
    }
}
