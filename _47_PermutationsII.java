package leetcode_1To300;

import java.util.ArrayList;
import java.util.Arrays;
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
public class _47_PermutationsII {
    /**
     * For example,
     [1,1,2] have the following unique permutations:
     [
     [1,1,2],
     [1,2,1],
     [2,1,1]
     ]


     * @param nums
     * @return
     */
    // time : O(n!) space : O(n);
    //输入有两种可能 一种是没有重复的 就是说完全不用担心
    //另外一种可能是有重复的元素 重复的话 我们可以进行预排序 但是如果限定时间复杂度一定要小于nlogn,那么就不可以进行预排序了
    //这个时候就需要用空间来换时间了 我们需要借助hashset等数据结构 或者用简单的数据结构记录重复的状态（true/false,或者其他有限的状态集1/2/3...等等）

    //广义的来看 如果题目限制了时间复杂度 实际上是在提醒我们 可以用空间来换取时间
    //限制了空间复杂度 大多数是限制为常数 这也就意味这是in place的操作，我们可以通过一步一步从前往后或者从后往前（比如说与后面的swap,比如说一维的DP） 渐构建起解

    //有的时候我们不是不用重复的数字或者字符 而是虽然数字或者字符一样 但是其本质是不一样的 能够表示他们不同的 可以像下面的解答一样，创建一个used的数组keep 用过的相同值的
    //或者可以用每一个元素的index来唯一标记这个元素，先将所有的元素按照全部不同进行处理 然后特殊处理那些元素值一样的情况
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return  res;
        Arrays.sort(nums);//it seems duplicate related problems needs to presort?
        helper(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;//&&比||优先级高 so it means if(i is used OR i is not start and i-1 is not used or nums[i]==num[i-1]
            //pay attention: we have seen "i > 0 && nums[i] == nums[i - 1]" before in coombination sum2
            used[i] = true;//添加前标记一下
            list.add(nums[i]);
            helper(res, list, nums, used);
            used[i] = false; //backtrack and recover i
            list.remove(list.size() - 1);
        }
    }

    // time : O(n!) space : O(n);
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return  res;
        Arrays.sort(nums);
        helper2(res, nums, 0);
        return res;
    }
    public void helper2(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (isUsed(nums,start, i)) continue;
            swap(nums, start, i);
            helper2(res, nums, start + 1);
            swap(nums, start, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public boolean isUsed(int[] nums, int i, int j) {
        for (int x = i; x < j; x++) {
            if (nums[x] == nums[j]) return true;
        }
        return false;
    }
}
