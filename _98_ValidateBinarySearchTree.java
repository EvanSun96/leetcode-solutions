package leetcode_1To300;

/**
 * 本代码来自 Cspiration，由 @Cspiration 提供
 * 题目来源：http://leetcode.com
 * - Cspiration 致力于在 CS 领域内帮助中国人找到工作，让更多海外国人受益
 * - 现有课程：Leetcode Java 版本视频讲解（1-900题）（上）（中）（下）三部
 * - 算法基础知识（上）（下）两部；题型技巧讲解（上）（下）两部
 * - 节省刷题时间，效率提高2-3倍，初学者轻松一天10题，入门者轻松一天20题
 * - 讲师：Edward Shi
 * - 官方网站：https://cspiration.com
 * - 版权所有，转发请注明出处
 */
public class _98_ValidateBinarySearchTree {

    /**
     * 98. Validate Binary Search Tree
     * Given a binary tree, determine if it is a valid binary search tree (BST).

     Assume a BST is defined as follows:

     The left subtree of a node contains only nodes with keys less than the node's key.
     The right subtree of a node contains only nodes with keys greater than the node's key.
     Both the left and right subtrees must also be binary search trees.

     time : O(n)
     space : O(n)
     * @param root
     * @return
     */

    public static boolean isValidBST(TreeNode root) {

        if (root == null) return true;
        return helper(root, null, null);
    }

    public static boolean helper(TreeNode root, Integer min, Integer max) {//为什么要用包装类？因为包装类可以为null
        //我们把左子节点的最大值设为其父节点值 左子节点不能大于此值
        //我们把右子节点的最小值设为其父节点值 右子节点不能小于此值 （至于为什么不规定右子节点的最大值 因为我们是从顶层往下递归的）
        if (root == null) return true;//说明到这一枝的底部了
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;//为什么还需要max!=null?因为==null就没法比较了吧

        return helper(root.left, min, root.val) && helper(root.right, root.val, max);//双尾递归 用的&&连接 说明只有全部是true才是true
    }//这两个尾递归的意思是：必须都为true才返回true,有任何一个为false,helper函数最终一定返回false
}//把root.left作为root, 上一次的min作为min,当前root值作为max,表示新的root不能超过max,一旦超过 立即返回false
