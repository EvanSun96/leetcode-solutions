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
public class _33_SearchinRotatedSortedArray {

    /**
     *
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

     (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

     You are given a target value to search. If found in the array return its index, otherwise return -1.

     You may assume no duplicate exists in the array.

     4 5 6 7 0 1 2

     4 5 6 0 1 2 3

     time : O(logn);
     space : O(1);
     * @param nums
     * @param target
     * @return
     */

    //这道题不算难 但是一开始想复杂了，知道是用二分法 但是只知道用递归写 但是递归又很难去写 所以心态崩了。
    //然后看了一下答案 知道了可以用迭代去写 因此 写了出来 但是结果总是不对 因为在边界条件等的处理的时候 没有理解很清楚 很模糊 不知道哪些
    //地方可以用大于小于号 哪些地方必须要加等于号 所以心态又一次崩了
    public int search2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]){//如果左边界小于Mid,说明左边是sorted
                if (target < nums[mid] && target >= nums[start])//
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if (nums[mid] <= nums[end]){ //if right is sorted
                if (target > nums[mid] && target <= nums[end]) //and target is within the range
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
