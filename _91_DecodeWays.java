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
public class _91_DecodeWays {
    /**
     * 91. Decode Ways
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:

     'A' -> 1
     'B' -> 2
     ...
     'Z' -> 26
     Given an encoded message containing digits, determine the total number of ways to decode it.

     For example,
     Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

     The number of ways decoding "12" is 2.


     time : O(n)


     * @param s
     * @return
     */

    // space : O(n)
    //宏观的来说 dp[i]对应的是s中的前i-1
    //比如 dp[1]对应的是s的第一个字符可能出现的情况
    //dp[2]对应的是s中前两个字符可能出现的情况
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;//非基本类型都要判断一下是不是null
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1; //为什么dp[0]=1这个不他明白
        dp[1] = s.charAt(0) != '0' ? 1 : 0; //判断第一个数字是否为0 如果不为 则设为1 如果是0 则设为0
        for (int i = 2; i <= len; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));//直接用valueOf进行字符串和数字之间的转化 first代表i-1位置的那个
            int second = Integer.valueOf(s.substring(i - 2, i));//second代表i-2~i-1位置的那个数字
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];//如果前一个单个数字在1~9之间
            }
            if (second >= 10 && second <= 26) {//如果组成的数字在10到26之间
                dp[i] += dp[i - 2];//那么也是合法的
            }
            //注意 上面的两个if是并行的 意味着都有可能出现 这也是为什么"+="符号要用到
        }
        return dp[len];
    }

    //space : O(1)
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int c1 = 1;
        int c2 = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                c1 = 0;
            }
            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                c1 = c1 + c2;
                c2 = c1 - c2;
            } else {
                c2 = c1;
            }
        }
        return c1;
    }
}
//下面是自己写的 特殊情况太多 完全乱了
//想法倒没错 只是DP写的太过混乱
class Solution {
    public int numDecodings(String s) {
        //值得警醒的是 是让你求共有多少种方法 而不是输出所有的解
        //所有的一位数都是合法的 所有的二位数小于等于26都是合法的
        //i know that we should use DP due to subproblem has impact on cur problem
        if(s.length() == 0) return 0;

        int n = s.length();

        int[] dp = new int[n];
        for(int i = 0; i<n; i++){
            if(i == 0) {
                if(s.charAt(0) == '0'){
                    dp[0] = 0;
                } else {
                    dp[0] = 1;
                }
                continue;
            }

            String sub = s.substring(i-1, i+1);
            int num = (sub.charAt(0) - '0')*10 + sub.charAt(1) - '0';


            if(i == 1){
                if(num > 26){
                    dp[1] = dp[0];
                } else {
                    if(s.charAt(1) == '0'){
                        dp[1] = dp[0];
                    } else {
                        dp[1] = 2;
                    }
                }
                continue;
            }
            if(num > 26){
                dp[i] = dp[i-1];
            } else {
                if(s.charAt(i) == '0'){
                    dp[i] = dp[i-2];
                } else {
                    dp[i] = dp[i - 1] + dp[i - 2];
                }

            }
        }
        return dp[n-1];
    }
}
