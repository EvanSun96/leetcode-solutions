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
public class _99_RecoverBinarySearchTree {
    /**
     * 99. Recover Binary Search Tree
     * Two elements of a binary search tree (BST) are swapped by mistake.

     Recover the tree without changing its structure.
          6
         / \
        8  1
      /  \
     0   3
        / \
       2  5

     time : O(n)
     space : O(n)


     */

    //我们要知道 输入的TreeNode 就是一个树 并不是数组 我们可以把这颗树按照自己想要的方式进行实现，比如说前序中序后序层次遍历，但是还是需要自己手动实现
            //至于这道题 因为一个BST树的中序遍历输出应该是一个递增的序列，因此我们把BST中序遍历一遍 检查是否其输出为递增，不为递增的就记录一下，留着日后recover
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        if (root == null) return;//
        helper(root);
        int temp = first.val;//调换的不是treeNode,而是treeNode里面对应的值
        first.val = second.val;
        second.val = temp;
    }
    public void helper(TreeNode root) {
        if (root == null) return;//到底了就返回上一层
        //接下来中序遍历
        helper(root.left);//双路递归之左递归
        if (prev != null && prev.val >= root.val) { //这里的root已经是下一个root了 但是pre还是之前的root
            if (first == null) first = prev;//记录一下这两个位置不对的节点pair 但是为什么first == null 才执行first = prev的操作呢? because we only record first once
            second = root;
        }
        prev = root;//pre是root的前一个遍历的节点 pre的值正常来讲是要比root值小的
        helper(root.right);//双路递归之右递归
    }
//iteration version code and recursion code ideas are exactly the same
    public void recoverTree2(TreeNode root) {
        if (root == null) return;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        TreeNode cur = root;//cur pointer
        Stack<TreeNode> stack = new Stack<>();//DFS，use stack
        while (!stack.isEmpty() || cur != null) { //这一段核心代码本质上和上面的递归一样
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;//DFS core
            } else { //when cur == null. which means it is time to pop
                cur = stack.pop();//pop出来 也是个Tree Node
                if (prev != null && cur.val <= prev.val) {
                    if (first == null) first = prev;
                    second = cur;
                }
                prev = cur;//保存一下现在的cur,用于下次的比较
                cur = cur.right;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
