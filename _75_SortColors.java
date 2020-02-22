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
public class _75_SortColors {
    /**
     * 75. Sort Colors
     * Given an array with n objects colored red, white or blue,
     * sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

     Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

     time : O(n)
     space : O(1)

     * @param nums
     */
    //常数空间复杂度的解 虽然我之前也想到了swap,但是没想明白就匆匆用了hashmap来得到答案
    //其实下面的代码非常简单
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        //我之前想用两个指针就完成解答 但是根据答案显示 这似乎不够 需要三个指针
        int left = 0; //负责0
        int right = nums.length - 1;//负责2
        int index = 0;//负责1
        while (index <= right) {//当1指针尾遇到2指针头 说明已经遍历完毕
            if (nums[index] == 0) {//当index指针对应着0的时候 将之换到前面去
                swap(nums, index++, left++);//注意 swap并不是指针调换 而是指针对应的值调换
            } else if (nums[index] == 1) {//当为1 不做处理 index移动一位
                index++;
            } else {//等于2的时候
                swap(nums, index, right--);//与后面的换
            }
        }
    }//最后left指针和index会分别停在0和1的结尾 right指针会停在2的前面

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
