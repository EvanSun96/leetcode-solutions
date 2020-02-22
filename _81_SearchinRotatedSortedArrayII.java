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
public class _81_SearchinRotatedSortedArrayII {

    /**
     * 81. Search in Rotated Sorted Array II
     * Follow up for "Search in Rotated Sorted Array":
     What if duplicates are allowed?

     1 1 1 3 1

     time : O(logn) (worst : O(n))
     space : O(1);
     * @param nums
     * @param target
     * @return
     */

    //我们对Bianry search做一个总结：
    //普通无重复元素的二分搜索
    //普通有重复元素的二分搜索
    //单个断点无重复元素的二分搜索
    //单个断点有重复元素的二分搜索
    //感觉下面的答案相对于之前的无重复元素 还是改了很多的 至于为什么要这样改 就不知道了？？？
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;//不用判断target的特殊情况
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {//当start与end相邻的时候while不执行
            int mid = (end - start) / 2 + start;
            if (nums[mid] == target) return true;
            if (nums[start] == nums[mid] && nums[mid] == nums[end]) {//如果[start,end]区间都是等于mid，即start end 区间为非递减区间
                start++;
                end--;//两个都往里缩
            } else if (nums[start] <= nums[mid]) {//当[start] <= [mid] 说明start到mid区间是不递减区间 即要么一马平川，要么递增
                if (nums[start] <= target && target <= nums[mid]) end = mid;//如果target在[start][mid]中 将end调低
                else start = mid;//否则start调高
            } else {//其他情况就是mid到end是不递减区间
                if (nums[mid] <= target && target <= nums[end]) start = mid;
                else end = mid;
            }
        }
        //因为最后while结束的时候start和end紧邻 因此还要判断他们两个的情况
        if (nums[start] == target) return true;
        if (nums[end] == target) return true;
        return false;
    }
}
