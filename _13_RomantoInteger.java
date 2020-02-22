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
public class _13_RomantoInteger {
    /**
     * Given a roman numeral, convert it to an integer.

     Input is guaranteed to be within the range from 1 to 3999.

     规律：左边的数字如果小于右边的数字 = 右 - 左

     time : O(n);
     space : O(1);
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = toNumber(s.charAt(0));//想一想为什么res没有初始化为0
        for (int i = 1; i < s.length(); i++) {//为什么是从1开始的？因为你要对s从前往后一个不落的检索 而且还要比较相邻两个之间的大小关系 你不可能i从0开始 然后比较i和i+1的 因为这样会要么检索不全 要么最后溢出 因此采用 这种计算出第一个值 从第二个值开始处进行遍历 i和i-1进行比较的组合最佳
            if (toNumber(s.charAt(i)) > toNumber(s.charAt(i - 1))) {
                res += toNumber(s.charAt(i)) - 2 * toNumber(s.charAt(i - 1));
            } else {
                res += toNumber(s.charAt(i));
            }
        }
        return res;
    }

    public static int toNumber(char c) {//学会把这个抽象出来 不然堆堆叠叠会很繁琐 这是一种很好的枚举k-v数据类型的方法 没有用hashmap等数据结构 很好
        int res = 0;
        switch (c) {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
        }
        return res;
    }
}
