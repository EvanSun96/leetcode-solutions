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
public class _93_RestoreIPAddresses {
    /**
     * 93. Restore IP Addresses
     * Given a string containing only digits, restore it by returning all possible valid s address combinations.

     For example:
     Given "25525511135",

     return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

     time : O(3^4) => O(1) => O(3^n)
     space : O(n)
     
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(res, s, 0, "", 0);
        return res;
    }
    public void helper(List<String> res, String s, int index, String ret, int count) {//参数:最后的结果res, s, s上的指针index, res的基本组成单位ret, 控制层数count
        if (count > 4) return;//用count控制层数 大于4层直接不看
        if (count == 4 && index == s.length()) {//如果恰好等于四层 并且index指针恰好遍历完s
            res.add(ret);//就说明合法
            return;
        }
        for (int i = 1; i < 4; i++) { //每个节点都有三个子节点
            if (index + i > s.length()) break;//说明index已经超出s.length()了
            String temp = s.substring(index, index + i);//如果没有超出 那么就取这段字符串
            if ((temp.startsWith("0") && temp.length() > 1) || (i == 3 && Integer.parseInt(temp) >= 256)) continue;//如果是个（从0开始但是又不是个位数）或者（是个三位数并且大于256）直接剪枝
            helper(res, s, index + i, ret + temp + (count == 3 ? "" : "."), count + 1);//
        }
    }
}
