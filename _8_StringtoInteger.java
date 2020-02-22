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
public class _8_StringtoInteger {
    /**
     * time : O(n)
     * space : O(1)
     * @param str
     * @return
     */
    //这个program分为两部分：第一部分 提取一个字符串当中的第一个数字型字符串（符号也要提取）
    //然后把这个数字型字符串转化为十进制的数字
    //实际上这个代码很straight forward

    //这道题要考虑的情况众多 比如正负号，溢出
    public int myAtoi(String str) {
        str = str.trim();
        if (str == null || str.length() == 0) return 0;
        char firstChar = str.charAt(0);//仔细看题意 第一位必须是符号位 不然就直接返回0
        int sign = 1;
        int start = 0;
        long res = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) { //一旦不是digits
                return (int) res * sign;//就返回
            }
            res = res * 10 + str.charAt(i) - '0'; //数字与字符串转化器
            if (sign == 1 && res > Integer.MAX_VALUE) return  Integer.MAX_VALUE;//每次都要立即判断其是否溢出 res只是表示数字部分
            if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;//
        }
        return (int) res * sign;
    }
}
