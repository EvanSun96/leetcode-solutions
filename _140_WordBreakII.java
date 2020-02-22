package leetcode_1To300;

import java.util.ArrayList;
import java.util.HashMap;
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
public class _140_WordBreakII {
    /**
     * 140. Word Break II
     * For example, given
     s = "catsanddog",
     dict = ["cat", "cats", "and", "sand", "dog"].

     A solution is ["cats and dog", "cat sand dog"].

     time : O(n^3)
     space : O(n^3)

     * @param s
     * @param wordDict
     * @return
     */
    //这个题目还是很重要的 可以当作很多问题的模板
            //而且这道题并没有完全理解代码 只是跑的例子头头是道 看起来像是这么一回事
    //用HashMap + DFS？？？这个真的没想到
    HashMap<Integer, List<String>> map = new HashMap<>();
    //这个代码很难去线性理解 因此只能强行背下来了
    // DFS
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }

    public List<String> dfs(String s, List<String> wordDict, int start) { //
        if (map.containsKey(start)) { //如果有以start为key
            return map.get(start);//直接返回其值即可
        } //有这个单词了 才会get
        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = dfs(s, wordDict, end);//recursion
                for (String temp : list) {
                    res.add(s.substring(start, end) + (temp.equals("") ? "" : " ") + temp);
                }
            }
        }
        map.put(start, res);//以start为键 以res为值
        return res;
    }
}
