//BiliBili大雪菜做法
//自测可以通过，牛客上不AC
public class 树_序列化二叉树 {
    static String Serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        dfs_s(root, res);
        return res.toString();
    }

    static void dfs_s(TreeNode node, StringBuilder res) {
        if (node == null) {
            res.append("null ");
            return;
        }
        res.append(node.val + " ");
        dfs_s(node.left, res);
        dfs_s(node.right, res);
    }

    private static int u;

    static TreeNode Deserialize(String str) {
        u = 0;
        return dfs_d(str);
    }

    static TreeNode dfs_d(String str) {
        if (u == str.length()) return null;
        int k = u;
        while (str.charAt(k) != ' ') k++;
        if (str.charAt(u) == 'n') {
            u = k + 1;
            return null;
        }
        int val = 0;
        for (int i = u; i < k; i++)
            val = 10 * val + str.charAt(i) - '0';//0的ascii是48, 也可写作val = 10*val+str.charAt(i)-48
        u = k + 1;
        TreeNode root = new TreeNode(val);
        root.left = dfs_d(str);
        root.right = dfs_d(str);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        String s = Serialize(root);
        System.out.println(s);
        System.out.println(Serialize(Deserialize(s)));
    }
}
