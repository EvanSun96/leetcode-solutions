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
public class _154_FindMinimuminRotatedSortedArrayII {
    /**
     * 154. Find Minimum in Rotated Sorted Array II
     *
     *
     * 6 7 8 9 1 3 5;
     * 1 1 1 1 2 1
     *
     * time : O(logn)  worst: O(n);
     * space : O(1);
     * @param nums
     * @return
     */
    //模板都是一样的
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] < nums[end]) {
                end = mid;
            } else if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {  //跟之前相比 多了一个else 即如果相等的话 end指针就往前退一格 下一轮就重新选择mid然后再进行比较
                end--;
            }
        }
        if (nums[start] < nums[end]) return nums[start];
        else return nums[end];
    }
}
