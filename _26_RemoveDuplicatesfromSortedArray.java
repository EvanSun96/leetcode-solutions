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
public class _26_RemoveDuplicatesfromSortedArray {
    /**
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

     Do not allocate extra space for another array, you must do this in place with constant memory.

     For example,
     Given input array nums = [1,1,2],

     Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

     case : [1,1,2,2,3,4,5,6]
             1,2,3,4,5,6
                 c
                     i
     result : [1,2,3,4,5,6]

     time : O(n);
     space : O(1);

     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;//就是两种普通的特殊情况 比自己写的好多了
        int count = 1;
        for (int i = 1; i < nums.length; i++) {//从第二个元素向后遍历
            if (nums[i - 1] != nums[i]) {//遇到前一个不等于当前的，
                nums[count++] = nums[i];//就将当前的元素向前堆积 就是说把不重复元素向前堆积 至于nums[i]被换成什么样子 没人关心
            }//这方法比自己之前那差劲的做法不知道好了多少倍...
        }
        return count;
    }
}

//自己写的方法 花了很大劲才debug好 对了是对了 但是时间和空间复杂度都很差
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 1) return 1;//现在写特殊情况变得十分冗余，记住，当特殊情况多了也就不叫特殊情况了
        if(nums.length == 2) {
            if(nums[0] == nums[1]) {
                return 1;
            } else {
                return 2;
            }
        }
        //return (nums.length == 2 && nums[0] == nums[1]) ? 1 : 2;

        //int left = 0;
        //int cur = left + 1;
        int count = 0; //total number of duplicates
        for(int left = 0; left<nums.length - count;left++){//left pointer
            int cur = left + 1;//cur pointer initial
            //if(cur == nums.length) {
            //    break;
            //}
            while(nums[left] == nums[cur]){//when left position == cur location，
                if(cur == nums.length - count) {
                    break;
                }//we have to 判断cur here instead of in outer loop becasue cur is 相对运动
                int temp = nums[left];//store nums[left]

                for(int i = left + 2; i < nums.length - count;i++){//move remaining sequence
                    nums[i - 1] = nums[i];//move ahead
                }
                count = count + 1;//duplicate counter
                nums[nums.length - count] = temp; //place nums[left] to tail(but not last position)
                //cur = cur + 1;//every time we move cur ahead to see if nums[left] == nums[cur] still good
            }
        }

        return nums.length - count;

    }
}