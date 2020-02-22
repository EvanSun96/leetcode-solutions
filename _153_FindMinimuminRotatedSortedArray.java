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
public class _153_FindMinimuminRotatedSortedArray {
    /**
     * 153. Find Minimum in Rotated Sorted Array
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     4 5 6 7 0 1 2

     4 5 6 0 1 2 3

     2 1

     * time : O(logn)
     * space : O(1);
     * @param nums
     * @return
     */
    //这道题就是找断点
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;//这样写真的可以吗？这么确定nums里面没有负数？
        int start = 0;
        int end = nums.length - 1; //紧边界
        while (start + 1 < end) {//当start和end邻居的时候停止
            int mid = (end - start) / 2 + start;
            if (nums[mid] < nums[end]) {  //说明后半部分是有序的 降低右边界
                end = mid;
            } else { //否则前半部分是有序的 提升左边界
                start = mid + 1; //为什么start=mid+1?是因为else的缘故吗？可是题目说没有重复的元素啊
            }
        }
        if (nums[start] < nums[end]) return nums[start];//如果start end仍然有序 说明断点出现在start右边
        else return nums[end];//否则断点出现在start end中间
    }
}

//下面是自己写的 不敢相信这道题有这么简单 但是内心深处知道这道题可以用更好的方法 二分：
class Solution {
    public int findMin(int[] nums) {
        for(int i = 1; i<nums.length; i++){
            if(nums[i] < nums[i-1]){
                return nums[i];
            }
        }
        return nums[0];
    }
}