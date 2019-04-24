/*
* 题目描述
从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
*题解:
* 每次遍历到该层最后一个位置的时候人为的添加一个null节点来标记下一层的所有节点都已经添加进队列
* 取出的时候如果碰到null节点就做分行处理
* */
public class 树_把二叉树打印成多行 {
    public static ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(pRoot);
        queue.offer(null);
        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            if(tmp == null){
                if(arr.isEmpty()) break;
                //res.add(arr)这样是错误的! 后面arr会变！
                res.add(new ArrayList<>(arr));
                arr.clear();
                queue.offer(null);
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

    public static void main(String[] args) {
        TreeNode head = new TreeNode(8);
        head.left = new TreeNode(6);
        head.right = new TreeNode(10);
        head.left.left = new TreeNode(5);
        head.left.right = new TreeNode(7);
        head.right.left = new TreeNode(9);
        head.right.right = new TreeNode(11);
        ArrayList<ArrayList<Integer>> res =Print(head);
        System.out.println();
    }
}
