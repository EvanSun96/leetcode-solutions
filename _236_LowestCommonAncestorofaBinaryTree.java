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
public class _236_LowestCommonAncestorofaBinaryTree {
    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

     According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v
     and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

     time: O(n);
     space : O(n);

     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {//return null only when p,q's LCA is not null
        if (root == null || root == p || root == q) return root;// if any of these happens, then it indiactes when root==null, then LCA of p q is null
        //root == p, then LCA of p q is p, root == q, then LCA of p q is q. all three of them are base cases

        TreeNode left = lowestCommonAncestor(root.left, p, q);//
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //pay attention to next few statement: why do we not check if left and right both null?
        if (left != null && right != null) { //left is the  p q's LCA && right is the p q's LCA(how can this happend? it can only happens then p,q,root are the same node)
            return root; //then we can say root is pq's LCA
        }
        return left == null ? right : left; //return one of left and right who is not null
    }
}
