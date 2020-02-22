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
public class _110_BalancedBinaryTree {

    /**
     * 110. Balanced Binary Tree
     * Given a binary tree, determine if it is height-balanced.

     For this problem, a height-balanced binary tree is defined as a binary tree
     in which the depth of the two subtrees of every node never differ by more than 1.
         1  -- 3
        / \
       2   3  -- 1
      / \
     4   5  -- 1

         1
        / \
       2   3  2 --> 3  3 --> 1
      / \
     4   5  -- 2
          \
          9  -- 1

     time : O(n);
     space : O(n);
     * @param root
     * @return
     */

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return helper(root) != -1;
    }

    public int helper(TreeNode root) {//高度差这个东西 可以是任何数值 但是我们只需要关注是否有大于1的情况发生
        if (root == null) return 0;//高度差为0
        int l = helper(root.left); //helper()函数每一层都返回每个左叶节点的高度 l相当于一个被维护的最大值
        int r = helper(root.right);//返回每个右叶结点的盖度 r相当于一个被维护的最大值
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) { //后序遍历 对叶节点进行操作 首先要检查上一层的L或者r的值是不是-1，如果是说明出现高度差大于1了，然后随着之后l r返回上一层都是-1不在会变了 最后Helper函数就是返回-1 说明不是balanced
            return -1;
        }
        return Math.max(l, r) + 1;//最后还有个return
    }
}
