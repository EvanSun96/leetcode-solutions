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
public class _22_GenerateParentheses {
    /**
     * 22. Generate Parentheses
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

     For example, given n = 3, a solution set is:
     [
     "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
     ]


     time : O(n!) (2^n)
     space : O(n)

     卡特兰数：
     (0,n-1) (1,n-2) (2,n-3) ... (n-1,0)
     //但是没看出来卡塔兰数和这个解法有什么联系

     * @param n
     * @return
     */

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        helper(res, "", n, n);//right和left表示左右括号剩下的个数
        return res;
    }
    //我们的限制条件是 剩余的左括号个数只有在小于右括号的时候，左右括号都能用
    //剩余的左括号个数在等于右括号的时候，只能用左括号
    //剩余的左括号个数在大于右括号的时候，这种情况永远不要出现 因为不合法
    public static void helper(List<String> res, String s, int left, int right) {
        if (left > right) { //每一层递归上来就先检查左括号是否大于右括号 如果左括号剩下的比右括号多 不合法 直接返回上一层
            return;
        }
        if (left == 0 && right == 0) {//如果两者都剩下0
            res.add(s);//说明完成了一种组合 添加到结果之中
            return;
        }
        if (left > 0) { //选择双递归 //优先走左路 左路走不下去了 再考虑走右路
            helper(res, s + "(", left - 1, right);//就排一个左边 进入下一层递归
        }
        if (right > 0) {
            helper(res, s + ")", left, right - 1);
        }
    }
    //对于递归每一层究竟返回什么 还是很难理解
    //因此我们可以试着看出来我们把所有的情况都以二叉树的形式列了出来 然后用第一个if排除掉所有不合法的情况（如何排除？即不将结果添加到结果集里面）
    //我们这个二叉树除了顶层之外共有6层 我们每次成功走到最底层才可以添加至结果 left和right负责实时统计左右括号的实际使用情况 出现left>right的分支被立刻舍弃掉（即立刻return 空） 根本就不等其到达最底层
    //所以综上考虑 用DFS 所以先添加走到最底 然后添加全路径（注意 虽然代码看上去是先添加路径 再往下走 而前序遍历好像也是这样
    //其实不是 前序遍历每次是先添加 但是添加的是节点 而不是全路径 想要得到全路径 必须边走边为全路径添砖加瓦 最后如果走到底 就得到了一条全路径 添加在路径集合中即可
}
