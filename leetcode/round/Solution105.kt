package leetcode.round


/**
 * Anything that can go wrong will go wrong
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2025-12-23
 */
class Solution105 {

    // 看官方题解的动画！
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty()) return null
        val length = preorder.size
        val inOrderMap = inorder.indices.associateBy { inorder[it] }
        return dfs(preorder, 0, length - 1, inOrderMap, 0, length - 1)
    }

    fun dfs(
        preorder: IntArray,
        preLeft: Int,
        preRight: Int,
        inOrderMap: Map<Int, Int>,
        inLeft: Int,
        inRight: Int,
    ): TreeNode? {
        if (preLeft > preRight || inLeft > inRight) return null
        val value = preorder[preLeft + 1]
        val root = TreeNode(value)
        val pRootIndex = inOrderMap[value]!!
        root.left = dfs(
            preorder,
            preLeft + 1,
            pRootIndex - inLeft + preLeft,
            inOrderMap,
            inLeft,
            pRootIndex - 1
        )
        root.right = dfs(
            preorder,
            pRootIndex - inLeft + preLeft + 1,
            preRight,
            inOrderMap,
            pRootIndex + 1,
            inRight
        )
        return root
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}