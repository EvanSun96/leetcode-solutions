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
public class _53_MaximumSubarray {
    /**
     * 53. Maximum Subarray
     *
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

     For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
     the contiguous subarray [4,-1,2,1] has the largest sum = 6.

     * @param nums
     * @return
     */

    // time : O(n) space : O(n);
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];//res不能被随便初始化为0了 仔细想一下res初始化的含义是什么 还不是代表了最大子串和？
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);//意思就是dp[i-1]<0就重新开始 大于0就延续前值
            res = Math.max(res, dp[i]);//res是从dp数组中找最大值
        }
        return res;
    }

    // time : O(n) space : O(1);
    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);//虽然没写dp 但是也是dp思想，因为如果上一个sum<0的话 Max取的就肯定nums[i]了 也是从头开始
            res = Math.max(res, sum);
        }
        return res;
    }
}
