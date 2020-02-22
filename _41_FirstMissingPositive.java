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
public class _41_FirstMissingPositive {
    /**
     * Given an unsorted integer array, find the first missing positive integer.

     For example,
     Given [1,2,0] return 3,
     and [3,4,-1,1] return 2.

     Your algorithm should run in O(n) time and uses constant space.

     time : O(n)
     space : O(1)

     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) { //如果什么都没有
            return 1; //那么第一个miss的正数是1
        }
        for (int i = 0; i < nums.length; i++) {//下面的while加nums[i] <= nums.length的意义在于 如果不满足 说明当前数值已经超了很多了
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) { //nums[nums[i] - 1] != nums[i] 表示如果在index=i位置上的不是i+1（因为我们只放正数）
                int temp = nums[nums[i] - 1];//就对两者进行交换 将nums[i]换到应该存在的位置上
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        //前面的for全部走完 nums已经被改造成了 所有的正数在其合适的位置上 负数都被移动到后面去了 唯一一个缺失的正数的位置上待着一个负数 就像是[1,2,3,-2,5,6,7,-1,-5,-9,-2]
        for (int i = 0; i < nums.length; i++) { //从前往后遍历 第一个不对应的数就应该被返回index+1,这就是缺失的那个正数
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;//如果没有缺失 说明在正数的最后一个缺失 就像是[1,2,3,4,5,-2,-4,-1,-8],缺失的是数字6
    }
}
