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
public class _132_PalindromePartitioningII {
    /**
     * 132. Palindrome Partitioning II
     * Given a string s, partition s such that every substring of the partition is a palindrome.

     Return the minimum cuts needed for a palindrome partitioning of s.

     For example, given s = "aab",
     Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

     [][] isPalindrome
     [] cuts
     i   j
     abcba s.charAt(i) = s.charAt(j) && isPalindrome[i+1][j-1]


     time : O(n^2)
     space : O(n^2)

     * @param s
     * @return
     */

    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] cuts = new int[len];//cut[i]代表如果只有前i个元素的话 最少的cut数
        boolean[][] isPalindrome = new boolean[len][len];//isPalindrome[i][j]代表ij指针中间的是不是回文（包括ij指针指的元素）

        for (int i = 0; i < len; i++) {
            int min = i;//最多切i刀 比如说一个字符串长3 最多切两刀
            for (int j = 0; j <= i; j++) { // ji双指针
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || isPalindrome[j + 1][i - 1])) { //如果ji指针指的元素一样 并且其里面的字符串也是回文
                    isPalindrome[j][i] = true; //ji指针及其内部的字符串是回文的
                    min = j == 0 ? 0 : Math.min(min, cuts[j - 1] + 1); //j==0说明ji可以不用切 0刀就可以。而后面的cuts[j-1]意思是前j-1个字符最小的cut + 多cur一刀（在j-1 j中间cut）
                }
            }
            cuts[i] = min;//记录一下这个min
        }
        return cuts[len - 1];
    }
}

//下面是按照上面的答案默写的 但是怎么样都通不过 一直在说||运算符错误？？？？？

//与之前的131不同的是 求最小的切割数量
//用DP
class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] cut = new int[len];
        int[][] dp = new int[len][len];

        for(int i = 0; i<len; i++){
            int min = i;
            for(int j = 0; j<= i; j++){
                if( s.charAt(j) == s.charAt(i) && (i - j < 2 || dp[j+1][i-1]) ){
                    dp[j][i] = true;
                    min = j == 0 ? 0:Math.min(min, cut[j-1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[len - 1];
    }
}