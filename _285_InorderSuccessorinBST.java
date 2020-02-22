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
public class _285_InorderSuccessorinBST {
    /**
     * 285. Inorder Successor in BST
     * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

     Note: If the given node has no in-order successor in the tree, return null.

     time : O(n);
     space : O(h);
     * @param root
     * @param p
     * @return
     */

    //non recusrion method, but the idea is the same with recusrion version
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) { //we don;t have to compare if rwo nodes are equal, we just have to know if the relative of their value, because
        //left are strictly<root<right
        TreeNode res = null;
        while (root != null) {
            if (root.val <= p.val) { //if current root.val <= p.val, which means p is in root's right subtree
                root = root.right; //we need to move root pointer to right
            } else { //else if >, but we can't say that p's in order successor is root.
                res = root; // we use res to point the original root
                root = root.left;//and root moves to left
            }
        }//after the whole which, root reaches the bottom of the tree, whereas res stay in the closest possible which val>p.val
        return res;//
    }

    //don't ever use linear thought to thinking recursion questions!
    public TreeNode successor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.val <= p.val) { //pre order tranverse, check
            return successor(root.right, p);
        } else { //
            TreeNode temp = successor(root.left, p);//I don't understand this statement
            return (temp != null) ? temp : root; //
        }
    }

    //use the same idea above to find a predecessor
    public TreeNode predecessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val >= p.val) { //root.val >= p.val, due to we need to find a node which is less but closest to p.val
            return predecessor(root.left, p); //we can only find it in curnode's left subtree
        } else {
            TreeNode right = predecessor(root.right, p); //everytime we find a node.val < p.val, we store it, and this right node can be updated if in next few level
            // we find that curnode.val < p.val
            return (right != null) ? right : root;
        }
    }
}
