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
public class _102_BinaryTreeLevelOrderTraversal {

    /**
     * 102. Binary Tree Level Order Traversal
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

     For example:
     Given binary tree [3,9,20,null,null,15,7],
          3
         / \
        9  20
      /  \
     15   7
     [
     [3],
     [9,20],
     [15,7]
     ]
     time : O(n);
     space : O(n);
     * @param root
     * @return
     */

    //非递归写法 宏观的说就是用queue接口 实际上是个linkedlist
    //外层while 控制queue是否为空
    //内层for poll出当前queue头部 ，将此node的左右子节点分别offer到queue尾部 然后将poll出的节点的值添加成为res的基本组成元素的一部分
    //每一次for结束 说明一层结束 将list添加到res中
    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);//添加到queue中 初始化他 使之开始运转
        while (!queue.isEmpty()) {
            int size = queue.size();//正常来说 这里每次size是按照1，2，4，8...来的 这里的size是对上一次for循环结束后的queue size做一次备份 这个备份是必须的
            List<Integer> list = new ArrayList<>();//基本组成单位
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();//cur指针 每次从queue中poll出一个
                if (cur.left != null) queue.offer(cur.left);//先将left加入queue
                if (cur.right != null) queue.offer(cur.right);//再将right加入queue
                list.add(cur.val); //
            }
            res.add(list);
        }

        return res;
    }

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, TreeNode root, int level) {//有一个控制层次的东西 注意这个递归函数中没有res的基本组成单元
        if (root == null) return;
        if (level >= res.size()) {//由于一层是一个list,因此每下一层就要拓展一个新的空list
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);//借鉴前序遍历, why? because the example shows that we have to show the level from top to bottom, the top is level 0
        helper(res, root.left, level + 1);
        helper(res, root.right, level + 1);
    }
}
