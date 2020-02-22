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
public class _129_SumRoottoLeafNumbers {
    /**
     * 129. Sum Root to Leaf Numbers
     * For example,

       1
      / \
     2   3
     The root-to-leaf path 1->2 represents the number 12.
     The root-to-leaf path 1->3 represents the number 13.

     Return the sum = 12 + 13 = 25.

     time : O(n)
     space : O(n)

     * @param root
     * @return
     */

    //刚开始的时候思维实在是过于局限 因为就只想着一条一条路径的加起来
    //但是答案实际上所有的路径都是同时计算的 当递归函数到达底层的时候 所有的路径都已经被计算好
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int num) {
        if (root == null) return 0;//
        if (root.left == null && root.right == null) { //当root为叶子节点 也就是说当root只有一个子节点的的时候 递归要进行下取 这时候root为null了
            return num * 10 + root.val;//可以返回了
        }
        return helper(root.left, num * 10 + root.val) +
                helper(root.right, num * 10 + root.val); //尾递归 注意这里是 左+右 这里虽然很简洁 但是却很难理解
    }
}
