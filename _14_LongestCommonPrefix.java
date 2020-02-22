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
public class _14_LongestCommonPrefix {
    /**
     * time : O(m * n)
     * space : O(1);
     *
     * @param strs
     * @return
     */

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        for (int i = 0; i < strs[0].length(); i++) {//与我自己写的代码不同 他是用任意一个作为基准 但是我们i代表的含义都相同
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {//也使用了双for 因此复杂度也没好到哪里去
                if (i == strs[j].length() || strs[j].charAt(i) != c) {//i == strs[j].length()说明有那种很短的 程序可以立即终止 或者此处不等于对应字符 程序也可以终止
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }
}
//和我自己写的本质上是一样的
