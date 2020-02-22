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
public class _270_ClosestBinarSearchTreeValue {

    /**
     * 270. Closest Binary Search Tree Value
     * @param root
     * @param target
     * @return
     */

    // time : O(logn) space : O(1)
    //iteration version, didn't use queue or stack. use root as pointer to do self destrcution
    public int closestValue(TreeNode root, double target) {
        int res = root.val;//initiate res;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - res)) {
                res = root.val;// res actually preserves root.val, and it only updates when current root.val is closer to used root.val(which reserved in res)
            }//so which means res updates everytime it needed to update, so finally we return res
            root = root.val > target ? root.left : root.right;//whether res updates or not, we always have to update root to its left or right. if root.val>target, that means closer node have to be at left side. root pointer change to root.left
        }
        return res;
    }


    // time : O(logn) space : O(n)
    //recursion version, use helper() function, its third parameter int val stands for current closest value of target,
    //so this val is like a res in iteration version code
    public int closestValue2(TreeNode root, double target) {
        return helper(root, target, root.val);
    }

    public int helper(TreeNode root, double target, int val) {
            if (root == null) return val;//if reach the bottom, return val
            if (Math.abs(root.val - target) < Math.abs(val - target)) { //needed to update, update it
                val = root.val;
            }
            //why don't we use one line statement? because when root.val == target, we just need to return val.
            if (root.val < target) {
                val = helper(root.right, target, val);
            } else if (root.val > target) {
                val = helper(root.left, target, val);
            }
            return val;
    }
    //in general, helper(root, target, val) stands for under this root, the cloestest value to target is val.
    //and what will be the relations between helper(root, target, val) with (helper(root.left, target, val), helper(root.right, target, val))?
    //well, we certainly can't recusrion for both way becasue the answer is unqiue, so every recursion, we either go left or go right.
    //but how can we know if go left or go right? the relative of root.val and target.
    //pay attention to val, it seems not changing, but it did change, it's not changing in every recursion level but
}
