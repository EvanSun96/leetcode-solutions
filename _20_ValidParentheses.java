package leetcode_1To300;

import java.util.Stack;

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
public class _20_ValidParentheses {
    /**
     * 20. Valid Parentheses
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

     The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

     case1 : ()[]{}
     stack :

     case2 : ([)]
     stack :

     case3 : }
     stack :

     time : O(n);
     space : O(n);
     * @param s
     * @return
     */
    //在做这道题的时候 思路还是正确的 就是说用栈 但是因为不知道如何初始化 因此只好作罢。
    //至于 自己想的 先找到第一个右括号 匹配前面的对应的左括号，匹配成功 即可弹出 匹配不成功（即括号不对应）即返回false
    //如果能顺利匹配 就一直匹配下去 如果匹配完最后一个右括号 栈为空 那么返回true.
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (Character ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    //自己写的版本：
    class Solution {
        public boolean isValid(String s) {
            if(s == null) return false;
            if(s.length() == 0) return true;

            Stack<Character> stack = new Stack<Character>();

            for(int i = 0; i<s.length();i++){ //可以考虑一下遍历元素的版本 而不是遍历index 因为这里面跟元素所在的Index没有很多联系
                if(s.charAt(i) == '('){
                    stack.push(')');
                } else if(s.charAt(i) == '['){
                    stack.push(']');
                } else if (s.charAt(i) == '{'){
                    stack.push('}');
                } else {
                    if(!stack.empty() && ((stack.peek() == ')' && s.charAt(i) == ')') ||(stack.peek() == ']' && s.charAt(i) == ']') || (stack.peek() == '}' && s.charAt(i) == '}'))) {
                        stack.pop();
                    } else { //其实这儿也可以做一些改动 既然正向说的那么麻烦 那么不妨可以反向去说
                        return false;
                    }

                }
            }

            return stack.empty();
        }
    }
}

