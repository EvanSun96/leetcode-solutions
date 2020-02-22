package leetcode_1To300;

import java.util.HashMap;
import java.util.HashSet;

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
public class _3_LongestSubstringWithoutRepeatingCharacters {
    /**
     * Examples:

     Given "abcabcbb", the answer is "abc", which the length is 3.

     Given "bbbbb", the answer is "b", with the length of 1.

     Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
     "pwke" is a subsequence and not a substring.


     time : O(n)
     space : O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {//如果包含这个键
                j = Math.max(j, map.get(s.charAt(i)) + 1);//那就把J更新到 当前位置和（当前i之前紧邻的出现charAt(i)的index） 至于为什么要取max?就不清楚了。
            }
            map.put(s.charAt(i), i);//放入的是 char和对应的Index 注意 每一次都放 如果键重复了 也更新为最新的index
            res = Math.max(res, i - j + 1);//res是不断维护的最大值
        }
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        HashSet<Character> set = new HashSet<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {//如果之前包含了这个键
                set.remove(s.charAt(j++));//就remove掉j对应的键
                i--;//j自增1 i自减一 相当于下一轮的时候I的位置还是此处，然后就一直remove到j应该在的位置 这个位置和hashmap做法的位置一样
            } else {//如果是新的键
                set.add(s.charAt(i));//就添加一手
                res = Math.max(res, set.size());//set的size就是不重复的子字符串的长度 不断维护这样一个长度 但是问题来了 如何维护这样一个长度呢？
            }
        }
        return res;
    }
}

//针对用hashmap的 虽然说是双指针 但是实际上只有i指针完整的遍历了一遍 j指针只是跳着走的 因此只有O(n)的复杂度
//而hashmap之所以能够解 完全在于其键值对中的值可以被实时更新为最新的 而且上一次储存的Index数值也可以被利用做j跳的地方。

//而hashset方法 本质上的遍历方式跟hashmap是一样的 不同的是如果set中出现了之前放入的元素 那么ij sliding窗口就往内缩（i的内缩被抵消 j的内缩同时还在set内部移除对应的键） 这个时候res是不断减小的
//如果遍历的时候set中没有这个元素 那么就在set中放入 每放入一次 size增加1
//每次从else切换到if时res的积累长度 才可以作为max pool中的一员 但是显然 上面的代码并没有那么做 而是在每次else执行的时候 维护一下res
