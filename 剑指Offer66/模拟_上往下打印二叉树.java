/*
*题目描述
从上往下打印出二叉树的每个节点，同层节点从左至右打印。
* */

/*
*宽搜
* */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 模拟_上往下打印二叉树 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            if(tmp!=null){
                res.add(tmp.val);
                queue.offer(tmp.left);
                queue.offer(tmp.right);
            }
        }
        return res;
    }
}
