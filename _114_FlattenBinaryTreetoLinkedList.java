package leetcode_1To300;

import java.util.Stack;

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
public class _114_FlattenBinaryTreetoLinkedList {
    /**
     * 114. Flatten Binary Tree to Linked List
     * For example,
     Given

         1
        / \
       2   5
      / \   \
     3   4   6
     The flattened tree should look like:
     1
      \
      2
       \
       3
        \
        4
         \
         5
          \
          6

     time : O(n)
     space : O(n)


     * @param root
     */
    //实在是看不懂了
    private TreeNode prev = null;//

    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);//双路递归
        flatten(root.left);
        //后序遍历
        root.right = prev;//把right指针指向prev
        root.left = null;
        prev = root;//prev指针指向root
    }
    public void flatten2(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();//仍然stack
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
            if (!stack.isEmpty()) {
                cur.right = stack.peek();//set right as the results of peek
            }
            cur.left = null;//and left as null
        }
    }
}
