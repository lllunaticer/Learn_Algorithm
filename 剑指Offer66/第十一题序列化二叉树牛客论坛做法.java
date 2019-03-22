//来自https://www.nowcoder.com/questionTerminal/cf7e25aa97c04cc1a68c8f040e71fb84
//本题在牛课上AC
public class 第十一题序列化二叉树牛客论坛做法 {
    static String Serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        if (root == null)
            return res.toString();
        else {
            dfs_s(root, res);
            return res.toString();
        }
    }

    static void dfs_s(TreeNode node, StringBuilder res) {
        if (node == null) {
            res.append("#,");
            return;
        } else {
            res.append(node.val + ",");
            dfs_s(node.left, res);
            dfs_s(node.right, res);
        }
    }

    static int index;
    static TreeNode Deserialize(String str){
        if(str.equals(""))
            return null;
        index = -1;
        String[] strr = str.split(",");
        return dfs_d(strr);
    }

    static TreeNode dfs_d(String[] strr){
        index++;
        TreeNode node = null;
        if(!strr[index].equals("#")){
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.left = dfs_d(strr);
            node.right = dfs_d(strr);
        }
        return node;
    }
}
