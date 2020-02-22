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
public class _87_ScrambleString {

    /**
     * 87. Scramble String

     time : O(n!)
     space : O(n)

     * @param s1
     * @param s2
     * @return
     */

    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.equals(s2)) return true;

        int[] letters = new int[26];
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) return false;
        }
        for (int i = 1; i < len; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) return true;//s1前i和s2前i个作比较 以及 s1剩下的和s2剩下的比较
            if (isScramble(s1.substring(0, i), s2.substring(len - i))
                    && isScramble(s1.substring(i), s2.substring(0, len - i))) return true;}//s1前i个和s2后i个作比较 以及 剩下的之间比较  我们不用担心长度不一致的问题 因为走到这一步长度肯定一致
        return false;
    }
}
//下面是自己背下来 然后默写的 可是怎么样改时间都超限
//问题出在第61行
class Solution {
    public boolean isScramble(String s1, String s2) {
        //tree related problem, but don't know where to start
        if(s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if(s1.equals(s2)) return true;

        int[] letters = new int[26];
        for(int i = 0;i<s1.length();i++){
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for(int i = 0; i < s1.length(); i++){//不应该是i<s1.length() 而是小于26才对！
            if(letters[i] != 0) {
                return false;
            }
        }
        for(int i = 0; i < s1.length(); i++) { //i应该从1开始！！！！
            if(isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i))){
                return true;
            }
            if(isScramble(s1.substring(0,i), s2.substring(s2.length() - i))&& isScramble(s1.substring(i), s2.substring(0, s2.length() - i))){
                return true;
            }
        }
        return false;
    }
}
