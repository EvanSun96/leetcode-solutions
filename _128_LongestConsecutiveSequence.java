package leetcode_1To300;

import java.util.HashSet;

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
public class _128_LongestConsecutiveSequence {
    /**
     * 128. Longest Consecutive Sequence
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

     For example,
     Given [100, 4, 200, 1, 3, 2],
     The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

     Your algorithm should run in O(n) complexity.

     time : O(n)
     space : O(n)

     * @param nums
     * @return
     */
    //下面的代码宏观来看的话 很简单
    //就是先将输入传到hashset中
    //然后对数组中的每一个数 看他最多可以向前和向后拓展成多大的长度 维护一个最大的长度 即可
    //代码的架构大致如下：传入hashset要用一个for 然后每个字符的检查也需要一个for 这个for里面包含两个while用于向前向后延伸
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            set.add(num); //把每个元素都储存在hashset中
        }
        for (int i = 0; i < nums.length; i++) {
            int down = nums[i] - 1; //当前元素的理论前一个数
            while (set.contains(down)) { //当set包括前一个数的时候 注意用的是while
                set.remove(down); //就从set中移除掉这个数（为什么要移除？不会影响后面的使用吗？答案是不会的 如果数连在一起 如果不删除的话 后面还要重复的再来一遍 这样时间复杂度就会出问题了）
                down--;
            }
            int up = nums[i] + 1;//同样的 如果set包括后一个数 也一样处理
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }
            res = Math.max(res, up - down - 1); //维护一个最大的长度 至于这里为什么是up-down-1 想一个最简单的例子就行了
        }
        return res;
    }
}
