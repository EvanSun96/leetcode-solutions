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
public class _15_ThreeSum {
    /**
     *
     For example, given array S = [-1, 0, 1, 2, -1, -4],
     A solution set is:
     [
     [-1, 0, 1],
     [-1, -1, 2]
     ]

     [-4, -1, -1, 0, 1, 2]

     time : O(n^2);
     space : O(n^2);
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;//别看这短短的一句 任一个都不能改变 仔细体会
            int low = i + 1, high = nums.length - 1;
            int sum = 0 - nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == sum) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < sum) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return res;
    }
}
//这种要求结果中不重复 可以按照本题思考方式 将输入排个序 然后指针从后向前走 只要邻位相同 就不予考虑 一直向后走到第一个不与之前的重复处。

//而且本题的时间复杂度也很高 接近n^3 在一定程度上也算是暴力解 只是这个暴力解没有那么暴力 什么都不处理上来就三个指针 把所有情况都列一遍 只取加和为0的组合 边往res里面添加边去重（去重也总共花费n^2复杂度）
//但是我们可以看到在这道题中蕴藏的很多技巧 比如说为了防止溢出而采用双向双指针 什么情况下左指针右移 什么情况下右指针左移 而且这些移动巧妙地与提前sort联系到了一起 不能说不巧妙
