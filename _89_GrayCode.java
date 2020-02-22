package leetcode_1To300;

import java.util.ArrayList;
import java.util.List;

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
public class _89_GrayCode {
    /**
     * 89. Gray Code
     * The gray code is a binary numeral system where two successive values differ in only one bit.

     Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
     A gray code sequence must begin with 0.

     For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

     00 - 0
     01 - 1
     11 - 3
     10 - 2

     G(i) = i ^ (i/2)

     time : O(1 << n) / O(n)
     space : O(1 << n) / O(n)

     * @param n
     * @return
     */
    //这代码刚开始看到的时候傻了...这也太短了8
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {//不用把n转化成二进制 直接用位运算符 i代表走到了第几步
            res.add(i ^ i >> 1);//移位优先级比异或高 所以先移位 这个的意思是 得出第i步的格雷码 （i从0开始走）
        }
        return res;
    }
}

