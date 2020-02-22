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
public class _131_PalindromePartitioning {
    /**
     * 131. Palindrome Partitioning
     * Given a string s, partition s such that every substring of the partition is a palindrome.

     Return all possible palindrome partitioning of s.

     For example, given s = "aab",
     Return

     [
     ["aa","b"],
     ["a","a","b"]
     ]

     time: O(2^n) space O(n)

     * @param s
     * @return
     */

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        helper(res, new ArrayList<>(), s);
        return res;
    }
    public void helper(List<List<String>> res, List<String> list, String s) {
        if (s.length() == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s.substring(0, i + 1))) { //前i个字符
                list.add(s.substring(0, i + 1));
                helper(res, list, s.substring(i + 1));
                list.remove(list.size() - 1);
            }
        }
    }
    public boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}

//下面是自己写的 大体思想是对的 只是根本跑不了
//给定一个String 现在要将这个String进行分割 使得其分割的所有string都是回文字符串 当然 可能存在多种分割
//把所有的分割都展示出来

//思考：就像一个tree一样 但是每个节点的子节点数量可以有很多个 而且个数不确定
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length() == 0) return res;

        helper(s, res, new ArrayList<>());
        return res;
    }

    public void helper(String s, List<List<String>> res, List<String> list){
        if(s.length() == 0) {
            res.add(new ArrayList<>(list));
            return;
        }



        for(int i = 0; i<s.length(); i++){
            if(palindrome(s.substring(i))){
                helper(s.substring(i+1), res, list.add(s.substring(i,i+1)));
            } else {
                continue;
            }
        }
    }

    public boolean palindrome(String p){
        if(p == null || p.length() == 0) return false;
        int i = 0;
        int j = p.length() - 1;
        while(i<=j){
            if(p.charAt(i) == p.charAt(j)){
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
