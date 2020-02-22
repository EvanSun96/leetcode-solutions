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
public class _9_PalindromeNumber {

    /**
     time : O(logn) space : O(1)
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || x != 0 && x % 10 == 0) return false;
        int palind = x;//一定要保存一份副本 因为后面的x改变了 但是副本的值不会改变 因为基础类型是值传递
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + x % 10;//经典的反转数字算法 甚至回文序列的判断也是这样判断的
            x /= 10;
        }
        return palind == rev;
    }
}