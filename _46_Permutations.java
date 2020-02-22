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
public class _46_Permutations {
    /**
     * Given a collection of distinct numbers, return all possible permutations.

     For example,
     [1,2,3] have the following permutations:
     [
     [1,2,3],
     [1,3,2],
     [2,1,3],
     [2,3,1],
     [3,1,2],
     [3,2,1]
     ]

     time : O(n!)
     space : O(n);

     reference : http://www.1point3acres.com/bbs/thread-117602-1-1.html

     The number of recursive calls, T(n) satisfies the recurrence T(n) = T(n - 1) + T(n - 2) + ... + T(1) + T(0),
     which solves to T(n) = O(2^n). Since we spend O(n) time within a call, the time complexity is O(n2^n);

     * @param nums
     * @return
     */
    // time : O(n! * n) space : O(n);
    //递归的一个难以理解之处就是在其可以放在任何地方 可以放在尾递归 可以放在for语句里面 可以放在if里面 甚至相对位置也是变化的，比如前序中序后序遍历中的递归。递归之前的东西在
    //在递归往下push的时候就被执行 递归栈弹出的时候运行的是递归后面的?
    //递归函数中的return属于返回上一层（说明我们已经触底了）
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(res, new ArrayList<>(), nums);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums) { //递归函数有三个参数 总的res(nested list) res的基本组成单位
        if (list.size() == nums.length) { //触底警告！
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) { //for loop递归 为什么要用for 因为每一层递归我们都要试一遍所有的 将没加过的加进去
            if (list.contains(nums[i])) continue;  // O(n)
            list.add(nums[i]);//前面是一直再加 也就是符合一直在push 进栈
            helper(res, list, nums);
            list.remove(list.size() - 1);//后面的弹出是在出栈，为什么要出栈，是因为我们在这个过程中重复利用了list这个变量
        }
    }

    // time : O(n!) space : O(n);
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper2(res, 0, nums);
        return res;
    }
    public static void helper2(List<List<Integer>> res, int start, int[] nums) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            helper2(res, start + 1, nums);
            swap(nums, start, i);
        }
    }
    public static void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
