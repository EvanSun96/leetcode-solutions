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
public class _30_SubstringwithConcatenationofAllWords {
    /**
     * You are given a string, s, and a list of words, words, that are all of the same length.
     * Find all starting indices of substring(s) in s that is a concatenation of each word
     * in words exactly once and without any intervening characters.

     For example, given:
     s: "barfoothefoobarman"
     words: ["foo", "bar"]

     You should return the indices: [0,9].
     (order does not matter).

     time : O(n ^ 2)
     space : O(n)

     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null || words.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        int n = words.length;
        int m = words[0].length();
        HashMap<String, Integer> map = new HashMap<>();//利用hashmap

        for (String str : words) {//针对每个单词
            map.put(str, map.getOrDefault(str, 0) + 1);//以单词为键 以单词出现的次数为value
        }//这个for 用于构建map

        for (int i = 0; i <= s.length() - n * m; i++) {
            HashMap<String, Integer> copy = new HashMap<>(map);//副本
            int k = n;//计单词的个数
            int j = i;
            while (k > 0) {
                String str = s.substring(j, j + m);//取出来这个单词
                if (!copy.containsKey(str) || copy.get(str) < 1) {//如果不存在这个key||这个key的数量为0（为什么加这个？难道不是只要存在这个key就至少是1吗）
                    break;//一旦没有这个key，立刻换i
                }
                copy.put(str, copy.get(str) - 1);//如果有 就将此key对应的value-1 （这就是为什么上面必须要加上copy.get(str) < 1）
                k--;
                j += m;//j移动m到下一个可能出现的单词的起点
            }
            if (k == 0) res.add(i);//如果全部匹配，就将i添加进去。这里之所以要添加if(k==0)的判断，是因为之前while可能提前跳出，但是跳出也只是跳出while,后面的还是要正常执行
        }
        return res;
    }
}//统计出来一个hashmap, 然后匹配阶段再慢慢消耗掉这个hashnamp.
