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
public class _111_MinimumDepthofBinaryTree {
    /**
     * 111. Minimum Depth of Binary Tree
     * Given a binary tree, find its minimum depth.

     The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

     time : O(n);
     space : O(n);

     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) { //
        if (root == null) return 0;
        if (root.left == null || root.right == null) { //如果root是leaf或者root只有一个子节点
            return Math.max(minDepth(root.left), minDepth(root.right)) + 1;//我们就返回上一层，注意 如果root只有一个子节点的话 我们肯定要在这里选择更长的那个 因为那才是这条路径的真实长度
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    } //注意为啥每一层要加1：这个是统计层数的常用手段

    //this method is given by leetcode, which seems like a flatten of previous method
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if ((root.left == null) && (root.right == null)) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth + 1;
    }

    //下面是之前的找寻最大深度的代码
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;//复杂版的尾递归
    }
}
