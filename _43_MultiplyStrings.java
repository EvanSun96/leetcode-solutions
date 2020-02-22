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
public class _43_MultiplyStrings {
    /**
     * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

     Note:

     The length of both num1 and num2 is < 110.
     Both num1 and num2 contains only digits 0-9.
     Both num1 and num2 does not contain any leading zero.
     You must not use any built-in BigInteger library or convert the inputs to integer directly.

     time : O(n * m)
     space : O(n + m)

     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return "0";
        int[] digits = new int[num1.length() + num2.length()];//最多的位数就是这么长 不信的话用四位乘以四位 得出的最大的数最多八位长度
        //如果是在看不懂 可以试着跑一跑case 比如23*45
        for (int i = num1.length() - 1; i >= 0; i--) { //真的就已经是乘法的底层实现了
            for (int j = num2.length() - 1; j >= 0; j--) { //
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); //得出当前位置上的相乘结果
                int p1 = i + j, p2 = i + j + 1; //
                int sum = product + digits[p2]; //
                digits[p1] += sum / 10; //
                digits[p2] = sum % 10;
            }
        } //每时每刻p1 p2的位置上的值 都是当前计算的结果位的数字，并不是某一次遍历（即i固定j遍历）的结果。看看下面的stringBuilder直接构建出最后的结果就知道怎么回事了
        StringBuilder res = new StringBuilder();
        for (int digit : digits) {
            if (!(digit == 0 && res.length() == 0)) {
                res.append(digit);
            }
        }
        return res.length() == 0 ? "0" : res.toString();
    }
}
