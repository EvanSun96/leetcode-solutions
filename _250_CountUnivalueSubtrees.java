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
public class _250_CountUnivalueSubtrees {
    /**
     * 250. Count Univalue Subtrees
     * Given a binary tree, count the number of uni-value subtrees.

     A Uni-value subtree means all nodes of the subtree have the same value.

     For example:
     Given binary tree,
         5
        / \
       1   5
      / \   \
     5   5   5
     return 4.

     root = 5 res = 2
     root = 1
     root = 5 res = 3
     root = 5 res = 4

     time : O(n)
     space : O(n)


     */

    int res; // we can just declare res instead of initial it as 0;
    //we have to set this res as the global variable instead of set it as the parameter of recursion function.
    //we have tried it, but return the wrong answer.
    //so the problem is much like a LC124, which also uses a global variable to get the answer

    public int countUnivalSubtrees(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }

    //helper function returns whether current subtree is a unival subtree or not.
    public boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = helper(root.left);
        boolean right = helper(root.right); //take actions on its child nodes before on cur node

        if (left && right) { //if both of it's child are true
            if (root.left != null && root.val != root.left.val) { //check if the val of left is not the same as val of root
                return false;
            }
            if (root.right != null && root.val != root.right.val) { //check if the val of left is not the same as val of root
                return false;
            } //why we didn't combined previous two ifs together, because it's abinary tree, so we can't be sure that if this node has only left child or right child or other situation,
            //so we have to check them individually
            res++; //if nothing goes wrong, then we have a unival bianry tree
            return true;
        }
        return false; //if one of its child is false, which means not a unival binary tree, then cur is not a unival as well, just return false
    }

}
