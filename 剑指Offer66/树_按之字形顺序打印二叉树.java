/*
* 题目描述
请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
*题解:
* 本题与 树_把二叉树打印成多行 十分类似
* 只要在每层的时候判断一下是否对该层结果逆序放到最终的结果中去
* */
public class 树_按之字形顺序打印二叉树 {
    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(pRoot);
        queue.offer(null);
        boolean zigzag = false;
        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            if(tmp == null){
                if(arr.isEmpty()) break;
                //res.add(arr)这样是错误的! 后面arr会变！
                if(zigzag)
                    Collections.reverse(arr);
                res.add(new ArrayList<>(arr));
                arr.clear();
                queue.offer(null);
                zigzag = !zigzag;
            }else{
                arr.add(tmp.val);
                if(tmp.left != null)
                    queue.offer(tmp.left);
                if(tmp.right != null)
                    queue.offer(tmp.right);
            }
        }
        return res;
    }
}
