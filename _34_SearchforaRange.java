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
public class _34_SearchforaRange {
    /**
     * Given an array of integers sorted in ascending order, find the starting and ending
     * position of a given target value.

     Your algorithm's runtime complexity must be in the order of O(log n).

     If the target is not found in the array, return [-1, -1].

     For example,
     Given [5, 7, 7, 8, 8, 10] and target value 8,
     return [3, 4].

     time : O(logn)
     space : O(1);
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) { //将代码拆分成小的函数
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = findFirst(nums, target);
        if (start == -1) {
            return new int[]{-1, -1};
        }
        int end = findLast(nums, target);
        return new int[]{start, end};
    }

    public int findFirst(int[] nums, int target) { //仔细观察这个find first和find last的不同
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {//结束的时候start end 中间最多紧靠或者相等
            int mid = (end - start) / 2 + start;
            if (nums[mid] < target) {//区别体现在你是先假设target大，不可能跨到左半区
                start = mid;
            } else { //注意等号存在于这个else条件下 即左半区，可以跨到右半区，这样左右相遇 while结束的时候start和end必定在这个target的左边
                end = mid;
            }
        }
        if (nums[start] == target) return start;//因此最后先判断start,如果start是target那么就直接返回
        if (nums[end] == target) return end;
        return -1;
    }

    public int findLast(int[] nums, int target) {//find last也同理
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] > target) {
                end = mid;
            } else { //关键就在于这个等号是在什么情况下取
                start = mid;
            }
        }
        if (nums[end] == target) return end;
        if (nums[start] == target) return start;
        return -1;
    }
}

//自己写的代码
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1,-1};

        //int[] res = new int[];

        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                int i = mid;
                int j = mid;
                while(i >= 0 && nums[i] == target) {
                    i--;
                }
                while(j <= nums.length -1 && nums[j] == target) {
                    j++;
                }
                return new int[]{i+1, j-1};
            }
            if(nums[mid] > target) {
                right = mid - 1;
            }
            if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        return new int[]{-1,-1};
    }
}
