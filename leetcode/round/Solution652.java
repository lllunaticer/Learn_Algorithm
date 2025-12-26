package 刷题;

import java.util.*;

public class Solution652 {
/*    算法
            (深度优先遍历，哈希表) O(n)O(n)
    使用 unordered_map 记录每个子树经过哈希后的数量，哈希方法可以用最简单的前序遍历，即 根,左子树,右子树 的方式递归构造。逗号和每个叶子结点下的空结点的位置需要保留。
    若发现当前子树在哈希表第二次出现，则将该结点记入答案列表。
    时间复杂度
    每个结点仅遍历一次，HashMap单次操作的时间复杂度为 O(1)O(1)，故总时间复杂度为 O(n)O(n)。*/

    Map<String, Integer> path;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        path = new HashMap<>();
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }

    String dfs(TreeNode node) {
        if (node == null)
            return "";
        StringBuilder cur = new StringBuilder("");
        cur.append(node.val).append(",");
        cur.append(dfs(node.left)).append(",");
        cur.append(dfs(node.right)).append(",");
        String cur_s = cur.toString();
        if (!path.containsKey(cur_s))
            path.put(cur_s, 1);
        else
            path.put(cur_s, path.get(cur_s) + 1);
        if (path.get(cur_s) == 2)
            ans.add(node);
        return cur_s;
    }

}
