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
public class _161_OneEditDistance {
    /**
     * 161. One Edit Distance
     * Given two strings S and T, determine if they are both one edit distance apart.

     1, abcre abere
     2, abdc abc
     3, abc abdc

     abc
     abcd

     time : O(n^2)
     space : O(1)

     * @param s
     * @param t
     * @return
     */
    //这个答案宏观来看层次非常清晰 时间空间复杂度都非常好
    public static boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) { //i<Math.min是防止越任何一个字符串的界
            if (s.charAt(i) != t.charAt(i)) {//当i处 s t的字符不一样时
                if (s.length() == t.length()) { //如果s t长度相同
                    return s.substring(i + 1).equals(t.substring(i + 1));//如果i处字符不一样 那么就是1个edit distance了 只要判断st剩余的字符串是不是一样
                } else if (s.length() > t.length()) { //如果s长一些
                    return s.substring(i + 1).equals(t.substring(i)); //如果这个语句返回true 那一定是t正好缺s在i处的这个字符 但是后面的补上了，如果返回false,那就不止一个edit distance了
                } else {//如果t 长一些
                    return t.substring(i + 1).equals(s.substring(i)); //跟上面同理
                }
            }//如果安全的度过了所有的i而没出现返回的情况 那么说明s t的前Math.min(s.length(), t.length())是一模一样的，因此只要看s的总长度与t的总长度是不是差了1 如果是的话 也是返回true;
        }
        return Math.abs(s.length() - t.length()) == 1;
    }
}

//下面的代码是自己写的 这个时间空间复杂度真的是糟糕透顶 但是总归是通过了
//判断是否两个单词只是一个edit distance
class Solution {
    public boolean isOneEditDistance(String s, String t) {

        //if(s.length() == 0 && t.length() == 0) return false;
        //if(s.length() == 0 || t.length() == 0) return false;
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m+1][n+1];

        dp[0][0] = 0;//if they both length of 0, that's false?
        for(int i = 1; i<=m; i++){
            dp[i][0] = i;
        }

        for(int i = 1; i<=n; i++){
            dp[0][i] = i;
        }

        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else{
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }

            }
        }
        return dp[m][n] == 1? true:false;

    }
}
