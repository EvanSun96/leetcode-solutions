package leetcode_1To300;

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
public class _139_WordBreak {

    /**
     * 139. Word Break
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     * You may assume the dictionary does not contain duplicate words.

     For example, given
     s = "leetcode",
     dict = ["leet", "code"].

     Return true because "leetcode" can be segmented as "leet code".

     time : O(n^2) ～ O(n^4);
     space : O(n);

     contains 时间：O(n)
     substring 时间：O(n)

     s = "leetcode",
     dict = ["leet", "code"]


     * @param s
     * @param wordDict
     * @return
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];//这场景有点似曾相识 这不就是之前切割回文字符串吗
        dp[0] = true;//dp[j]代表前j-1个字符是不是可以切割成合法子字符串
        for (int i = 1; i <= s.length(); i++) { //注意这些遍历起止 无论是i还是j都很重要
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) { //如果前j个合法 并且 j~i-1这个单词也包含再wordDict里面
                    dp[i] = true;//那么就认定合法
                    break;
                }
            }
            //i可以接着向前移动一位
        }
        return dp[s.length()];
    }
}
