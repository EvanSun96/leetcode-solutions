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
public class _108_ConvertSortedArraytoBinarySearchTree {
    /**
     * 108. Convert Sorted Array to Binary Search Tree

     [1,2,3,4,5]

     time : O(n);
     space : O(n);
     * @param nums
     * @return
     */
    //this is the problem about how to construct a BST using given sorted array.
    //according to the solution provided by leetcode, if we have a tree and we use in order tranverse to get an array.
    //the array must be sorted. but if we want to transfer it back without knowing the original tree, it is not unique.
    //even if we add additional restriction: the tree must be high balanced, the solution is still not unique.
    //however, if the array is made by pre or post order, then it's correponding tree is unique.

    //so for this problem, it doesn't matter if we use left middle or right middle as the root.
    //as we can foreseen, this problem might can be sloved in recursion.
    //so can sortedArrayToBST can be used as recursion function? which means, can we divide nums into left and right part, if each part
    //construct a BST. but how? we don't have nums.left or nums.right like tree.
    // so we choose to use helper(), helper() returns TreeNode, its parameters are nums, left pointer and right pointer.

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {  // space : O(logn); //helper(nums, left, right) means the tree formed by the range between(left, right) index of nums
        if (left > right) return null; //its the same as while(left <= right)
        int mid = (right - left) / 2 + left; //left middle
        TreeNode node = new TreeNode(nums[mid]);//this node is for later return and recursion
        node.left = helper(nums, left, mid - 1); //like merge sort, which is also two way recursion, each time range cur half
        node.right = helper(nums, mid + 1, right);
        return node;
    }
}
// apparently, if helper(nums, left, right) == root, then root.left = helper(nums, left, mid - 1), and root.right = helper(nums, mid + 1, right)