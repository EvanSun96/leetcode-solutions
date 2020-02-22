package leetcode_1To300;

import java.util.ArrayList;
import java.util.List;

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
public class _113_PathSumII {
    /**
     * 113. Path Sum II
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

     For example:
     Given the below binary tree and sum = 22,
           5
          / \
         4   8
        /   / \
       11  13  4
      /  \    / \
     7    2  5   1
     [
     [5,4,11,2],
     [5,8,4,5]
     ]
     time : O(n);
     space : O(n);
     * @param root
     * @param sum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, new ArrayList<>(), root, sum);
        return res;
    }
    public static void helper(List<List<Integer>> res, List<Integer> list, TreeNode root, int sum) {
        if (root == null) return;

        //仔细观察下面的代码 还是先add 再递归 最后remove 之所以之前用for是因为每个节点的子节点个数不定（指的是排列组合时候用到的想像树） 现在可以确定是两个 一个 或者0个 因此双递归就完事了
        list.add(root.val);
        if (root.left == null && root.right == null) { //如果当前root是叶子节点
            if (sum == root.val) { //check if root.val equals remaining sum
                res.add(new ArrayList<>(list));//then we can add this list as one of the results in res//but why are we have
            }
        }
        helper(res, list, root.left, sum - root.val); //双递归能够DFS遍历的走遍树的每一条路
        helper(res, list, root.right, sum - root.val);
        list.remove(list.size() - 1);//列出所有的排列组合时所用的套路 check每一条路
    }
}
