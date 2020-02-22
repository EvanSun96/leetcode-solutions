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
public class _104_MaximumDepthofBinaryTree {
    /**
     * 104. Maximum Depth of Binary Tree
     * Given a binary tree, find its maximum depth.

     The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

     time : O(n);
     space : O(n);
     * @param root
     * @return
     */

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int l = maxDepth2(root.left) + 1;//有个疑问就是l和r不会每次都覆盖吗 经过检验这种并不会覆盖
        int r = maxDepth2(root.right) + 1;
        return Math.max(l, r);
    }
}
