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
//之前类似的题我们做过Maximum subarray, maximum sum subarray
public class _152_MaximumProductSubarray {
    /**
     * 152. Maximum Product Subarray
     * For example, given the array [2,3,-2,4],
     the contiguous subarray [2,3] has the largest product = 6.

     time : O(n)
     space : O(1)
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);//注意到max中用的max和Min都是上一次的
            min = Math.min(Math.min(min * nums[i], temp * nums[i]), nums[i]);//这个语句中用到的max和min也是上一次的 这也是为什么最开始用temp存了一个副本
            res = Math.max(res, max);
        }
        return res;
    }//但是对于为什么要这样去写 还是不太明白
}

//下面是自己写的暴力解 虽然一遍就过了 但是属实很low
//给定一个子数组 找出连续子数组 使得其乘积为最大
//注意 数组中包含负数
class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int len = nums.length;

        for(int i = 0; i < len; i++){
            int multi = nums[i];
            for(int j = i; j < len; j++){
                if(i == j) {
                    max = Math.max(max, nums[i]);
                } else{
                    multi = multi * nums[j];
                    max = Math.max(max, multi);
                }
            }
        }
        return max;
    }
}
