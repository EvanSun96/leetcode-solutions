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
public class _55_JumpGame {
    /**
     * 55. Jump Game
     * For example:
     A = [2,3,1,1,4], return true.

     A = [3,2,1,0,4], return false.

     time : O(n)
     space : O(1)
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int max = 0;//注意一下为什么要把max初始化为0而不是Integer.MIN_VALUE; 这个我想不明白 但是原因显而易见：如果改成MIN_VALUE第一步就直接返回false了，而且这里的max不是一个数字 而是Index，即当前能走到的最远的地方的index
        for (int i = 0; i < nums.length; i++) { //遍历一遍所有的元素
            if (i > max) return false;  //如果当前i比上一步的max都大 说明上一步max根本就没移动
            max = Math.max(nums[i] + i, max);//max每次取大的 说明要么max是肯定>=i的 如果大于i 说明移动了。如果遍历完了一遍还是等于i 说明nums[i]==0
        }//如果在此过程中一直没有返回false,说明它可以一直往下走 那么就说明肯定是可以走到最后的
        return true;
    }
}
