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
public class _101_SymmetricTree {
    /**
     * 101. Symmetric Tree
     *Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

     For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
         1
        / \
       2   2
      / \ / \
     3  4 4  3
     time : O(n)
     space : O(n)
     * @param root
     * @return
     */
//还是那句话 如果你觉得这道题让你处理的情况过多 那么这种方法一定是多多少少有些问题的
    //下面的代码让我自己的代码显得垃圾的一批
    public static boolean isSymmetric(TreeNode root) {

        if (root == null) return true;
        return helper(root.left, root.right);
    }

    public static boolean helper(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;//都等于null 则为true
        if (p == null || q == null) return false; //只有一个为Null 不太行
        if (p.val != q.val) return false;

        return helper(p.left, q.right) && helper(p.right, q.left);//完全不需要考虑下面情况
        //         1
        //        / \
        //       2   2
        //      / \ / \
        //   null 4 4
    }
}
