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
public class _10_RegularExpressionMatching {
    /**
     * Implement regular expression matching with support for '.' and '*'.
     * <p>
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * <p>
     * The matching should cover the entire input string (not partial).
     * <p>
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     * <p>
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "a*") → true
     * isMatch("aa", ".*") → true
     * isMatch("ab", ".*") → true
     * isMatch("aab", "c*a*b") → true
     * <p>
     * "aa", ".*"
     * "ab", ".*"
     * "aab", "c*a*b"
     * <p>
     * 首先我们一定要理解这两个正则表达式符号的含义：
     * a* 表示可以有0个或者多个a （我们要把a*当作一个整体来读吗？还是说从左往右读）
     * a*b 表示b前面可以匹配任意数量的a 只要是a就行
     * ca*b 表示c和b之间可以匹配任意数量的a
     * .表示一定要有一个任意字符
     * 特殊一点 就是这两个表达式相遇：.*  表示这个地方可以匹配0个或者多个任意字符？那意思是不是就是说可以直接匹配剩下的所有的了？如果.*之后还有其他字符怎么办？
     * <p>
     * <p>
     * boolean dp[i][j]的含义是s[0-i] 与 p[0-j]是否匹配。
     * <p>
     * c* = empty
     * <p>
     * 1，p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]
     * 2，If p.charAt(j) == ‘.’ : dp[i][j] = dp[i-1][j-1];
     * 3，If p.charAt(j) == ‘*’:
     * here are two sub conditions:
     * 1，if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty
     * 2，if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == ‘.’:
     * dp[i][j] = dp[i][j-1] // in this case, a* counts as single a
     * dp[i][j] = dp[i-1][j] //in this case, a* counts as multiple a
     * dp[i][j] = dp[i][j-2] // in this case, a* counts as empty
     * <p>
     * "aab", "c*aab"
     * <p>
     * i = 1 dp[0][2] = true
     * <p>
     * time : O(m * n)
     * space : O(m * n)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];//初始化为(s.len+1)*(p.len+1)型矩阵 注意矩阵初始化就默认为false
        dp[0][0] = true;//初始化左上角元素
        for (int i = 0; i < p.length(); i++) {//初始化第一行元素 //第一行初始化真没看懂
            if (p.charAt(i) == '*' && dp[0][i - 1]) {//如果此位等于*并且前一位等于true 我们完全不用担心出现第一个字符就是*的情况 因为不可能 所以i>=1才会执行这个if 因此就不可能出现dp[0][-1]
                dp[0][i + 1] = true;//那么后一位等于true(当前i位没有赋值？非也 当前i位保持false)
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {//ij两指针分别在s上和在p上 然后有三种情况：
                if (p.charAt(j) == s.charAt(i)) {//ij指针指向元素对应相同
                    dp[i + 1][j + 1] = dp[i][j];//那么表示s[0~i+1]和p[0~j+1]的匹配与否跟s[0~i]和p[0~j]的匹配相关
                }
                if (p.charAt(j) == '.') {//如果j指针对应‘.’ 那么不管i对应什么元素
                    dp[i + 1][j + 1] = dp[i][j];//s[0~i+1]和p[0~j+1]的匹配都与s[0~i]和p[0~j]的匹配相关
                }
                if (p.charAt(j) == '*') {//如果j指针对应‘*’ 那么有以下两种情况：
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {//j前面一个元素不等于i对应的 并且 j前面这个元素还不等于‘.’
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }



    //https://www.youtube.com/watch?v=DqhPJ8MzDKM
    //下面的方法出自上面的视频 视频讲解非常清楚
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true;
        //i is the index of matrix, for charAt() we should use i-1 这个很重要
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }//只用初始化当s不存在的情况 如果p含有 *这个字符 那么 *(i) 与 i-2位置真假值相同
        //为什么不用初始化当p不存在的情况？因为p不存在 只要s存在 就必定不匹配 为false 而初始化矩阵的时候所有的值默认为false
        for (int i = 1; i <= s.length(); i++) {//
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];//满足if 斜着过来
                } else if (p.charAt(j - 1) == '*') { //如果此处是*
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {//如果*只匹配0个或者是.*的情况
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];//那么只要竖着两格或者横着一格为true 此处就为true
                    } else {//如果匹配多个
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}

//关于“那么只要竖着两格或者横着一格为true 此处就为true”的详细解释：
//p.charAt(j - 2) == s.charAt(i - 1)：如果a*中的a 与 s此指针对应的相同 说明至少匹配了一个（因此情况可以分为匹配了一个或者0个 而匹配了多个我们不用去管 毕竟都是递推出来的）
//dp[i][j] = dp[i][j - 2]意思是 a*匹配了零个 因此可以不用去管
//dp[i][j] = dp[i - 1][j] 意思是a*匹配了一个对应的当前的a 因此只需要看i除去a 是不是还跟j匹配就行了
//而dp[i][j]选择哪个都行 因为你不知道是匹配0个还是1个 只要匹配的上就行
//p.charAt(j - 2) == '.'：意思是现在的情况是.* 也就是说可以匹配0个甚至多个任意字符 因此他的处理和p.charAt(j - 2) == s.charAt(i - 1)的处理一样

//而针对最后一个else 是说在p当前指向* 而又不是上述两种情况的时候（即既不相等 又不可以任意匹配）
//那我们就假定p指向的这个a 匹配0个 因此只用看j去除掉'a*'两个之后 还跟不跟i匹配了