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
public class _283_MoveZeroes {
    /**
     * 283. Move Zeroes
     * Given an array nums, write a function to move all 0's to the end of it
     * while maintaining the relative order of the non-zero elements.

     For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     Note:
     You must do this in-place without making a copy of the array.
     Minimize the total number of operations.

     [0, 1, 0, 3, 12]
     start = 3
     i = 1 [1, 1, 0, 3, 12]
     i = 3 [1, 3, 0, 3, 12]
     i = 4 [1, 3, 12, 0, 0]

     [0, 1, 0, 3, 12]
     j = 1
     i = 1 [1, 0, 0, 3, 12]
     i = 3 [1, 3, 0, 0, 12]
     i = 4 [1, 3, 12, 0, 0]


     time : O(n);
     space : O(1);
     * @param nums
     */
    // num of operation : nums.length;
    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int start = 0;
        for (int i = 0; i < nums.length; i++) { //from start
            if (nums[i] != 0) { //if this element is not 0, then we should move it ahead
                nums[start++] = nums[i]; //its like seeing 0s as blank,so nums can cllapse forward
            }
        }
        while (start < nums.length) { //and then change the rest last part of values in nums as 0
            nums[start++] = 0;
        }
    }

    // num of operation : 2 * num of non-zero
    // lots of zeros
    public void  moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = 0, j = 0; i < nums.length; i++) { //double pointer. but in same direction, j is the slower one and i is the fast one.
            if (nums[i] != 0) { //if i pointer value is not 0
                int temp = nums[i];//swap the value of fast pointer i and slow pointer j. and move j by j++
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }
}
