package leetcode_1To300;

import java.util.HashMap;

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
public class _159_LongestSubstringwithAtMostTwoDistinctCharacters {
    /**
     * 159. Longest Substring with At Most Two Distinct Characters
     * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

     For example, Given s = “eceba”,

     T is "ece" which its length is 3.

     sliding window

     “eceba”

     time : O(n)
     space : O(n)

     * @param s
     * @return
     */

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>(); //果然用了hashMap
        int start = 0, end = 0;
        int res = 0;
        while (end < s.length()) { //之前的方法只想到了用for 但是对于这种不是很线性的情况 最好用while 这样更加灵活
            if (map.size() <= 2) { //分两种情况 ap.size()<=2 或者map.size() > 2
                map.put(s.charAt(end), end);
                end++;
            }
            if (map.size() > 2) {
                int leftMost = s.length();
                for (int num : map.values()) { //这个用了另外一种方法来找最左边的那个元素
                    leftMost = Math.min(leftMost, num);
                }
                map.remove(s.charAt(leftMost));
                start = leftMost + 1;//start reset到leftMost+1 跟我之前想的一样
            }
            res = Math.max(res, end - start);
        }
        return res;
    }
}

//下面是自己写的 但是在字符串比较长的时候超时了
//给定一个字符串 找到其最长的子字符串 使得这个子字符串最多有两个distinct characters
//注意 只返回Length就可以

//自己用暴力法去解 时间复杂度为n^3 但是在提交的时候时间超限了
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) return 0;
        int res = Integer.MIN_VALUE;

        for(int i = 0; i<s.length(); i++){
            for(int j = i; j<s.length(); j++){
                if(atMost2(s.substring(i, j+1))){ //if only have at most two distinct
                    res = Math.max(res, j-i+1);
                }
                //System.out.println(res);
            }
        }
        return res;
    }

    public boolean atMost2(String s){
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        for(int i = 0; i<s.length(); i++){
            set.add(s.charAt(i));
            if(set.size() > 2){ //先添加再判断 而不是先判断再添加 如果最后一个字符正好是第三个distinct字符
                return false;
            }
        }
        return true;
    }
}

//第二种方法 虽然也是暴力解 但是减去了一些枝 但是时间复杂度仍然为n^3 还是他妈的超时
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) return 0;
        int res = Integer.MIN_VALUE;

        for(int j = 0; j<s.length(); j++){
            for(int i = 0; i<=j; i++){
                if(atMost2(s.substring(i,j+1))){//if is less than 3, then j can move forward
                    //j++;
                    res = Math.max(res, j-i+1);
                    break;
                } else {//if have three disctinct, j stays where it is, and let i move
                    //res = Math.max(res, j-i+1);
                    //continue;
                }
            }
        }
        return res;
    }

    public boolean atMost2(String s){
        HashSet<Character> set = new HashSet<>();
        //int count = 0;
        for(int i = 0; i<s.length(); i++){
            set.add(s.charAt(i));
            if(set.size() > 2){ //先添加再判断 而不是先判断再添加 如果最后一个字符正好是第三个distinct字符
                return false;
            }
        }
        return true;
    }
}

//试了第三种方法 仍然不行 因此放弃
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) return 0;
        int res = Integer.MIN_VALUE;

        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0, j = 0; j<s.length(); j++){
            if(map.containsKey(s.charAt(j))){ //if existed charAt(j) key
                map.replace(s.charAt(j), j); //then we update it
            } else{ //else we put a new key
                map.put(s.charAt(j), j);
            }
            if(map.size() > 2){//if we now have map size more than 2
                int temp = i;
                i = map.get(s.charAt(i)) + 1; //then then we get its index
                map.remove(s.charAt(temp));

            } else{ //if the map has size no more than 2
                res = Math.max(res, j - i +1);
                //break; //then keeps moving j
            }
        }
        return res;
    }


}