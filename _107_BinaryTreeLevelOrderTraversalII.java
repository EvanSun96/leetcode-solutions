package leetcode_1To300;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
public class _107_BinaryTreeLevelOrderTraversalII {

    /**
     * 107. Binary Tree Level Order Traversal II  (102 follow up)
     For example:
     Given binary tree [3,9,20,null,null,15,7],
          3
         / \
        9  20
      /  \
     15   7
     [
     [15,7],
     [9,20],
     [3]
     ]

     time : O(n)
     space : O(n)

     * @param root
     * @return
     */

    //跟之前的普通的层次遍历并没有什么不同
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();//这个因该是笔误 应该就是new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                list.add(cur.val);//这里之前的zigzag输出曾经变化过 但是在这个题目中没有任何变化
            }
            res.add(0, list);//只是添加顺序不一样而已
        }
        return res;
    }

    public static List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) return;
        if (level >= res.size()) {
            res.add(0, new ArrayList<>());
        }
        res.get(res.size() - level - 1).add(root.val);//在这个get里面的参数选择方面一直出错 还是对这个res是如何写的有一些误解
        helper(res, root.left, level + 1);
        helper(res, root.right, level + 1);
    }//res应该是一层一层的写入的 所以get里面不能是个fixed value,而是与层数有关？
    //之前正序读树的时候 res.get(level) 说明同一个level的 写在了一起
    //但是现在倒序读树的时候 为什么用res.get(res.size() - level - 1)? res.size()难道不是一直在变化的吗？？？
}

//如何理解双递归？就像是DFS 所有在双递归前面的东西都可以理解成前序遍历 第一个递归可以理解成一直往左走 当走不下去了 再考虑往右走 就算是往右走之后也要优先考虑到其最深的左子节点
//在双递归中间的东西可以理解成中序遍历
