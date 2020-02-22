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
public class _136_SingleNumber {
    /**
     * 136. Single Number
     * Given an array of integers, every element appears twice except for one. Find that single one.
     *
     * ^ : 异或 : 相同为0，不同为1
     * 1 1 : 0
     * 0 0 : 0
     * 1 0 : 1
     * 0 1 : 1
     *
     * time : O(n) space : O(1)
     *
     * @param nums
     * @return
     */
    //这个方法属实巧妙
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];//异或不管位置的 但是只要有相同的 就为0 不同的 就还是原本的值
        }
        return res;
    }
}
//下面是自己写的狗屎代码 写完之后才发现set里面的key取不出来
class Solution {
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;

        HashSet<Integer> set = new HashSet<>();
        for(int num: nums){
            if(set.contains(num)){
                set.remove(num);
                break;
            }
            set.add(num);
        }
        //取不出来set里面的键值
    }
}
