package leetcode_1To300;

import java.util.LinkedList;
import java.util.Queue;

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
public class _226_InvertBinaryTree {
    /**
     * 226. Invert Binary Tree
     * Invert a binary tree.

          4
        /   \
       2     7
      / \   / \
     1   3 6   9

     to
         4
        /   \
       7     2
      / \   / \
     9   6 3   1

     time : O(n)
     space : O(n);
     * @param root
     * @return
     */

    //递归就是能看懂 但是要自己去想就懵逼了
    public TreeNode invertTree(TreeNode root) {
       if (root == null) return root;
       TreeNode left = invertTree(root.left);// 我们要先到最底层 然后再调换并回溯 对于这个特殊的问题 是先走左路到最底层
       TreeNode right = invertTree(root.right);
       root.left = right;
       root.right = left;
       return root;
    }

    //tree often has recurion and iteration way to solve the problem, if we can't understand how to implement in recursion, then we can try for iteration
    //first we need to see the alg is implemented by queue which means it is a BST, its different than level order tranverse, because in level order tranverse,
    //we use parameter size of current queue to extract all elements on this level
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode temp = cur.left; //将cur node的左右子节点交换？没错！因为这个iteration是从root到下面 换了
            cur.left = cur.right;
            cur.right = temp;
            if (cur.left != null) queue.offer(cur.left); //pay attention, you have to swap before you offer cur.left and cur.right into queue
            if (cur.right != null) queue.offer(cur.right); //
        }
        return root;
    }
}
