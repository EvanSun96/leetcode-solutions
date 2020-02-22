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
public class _71_SimplifyPath {
    /**
     * 71. Simplify Path
     * Given an absolute path for a file (Unix-style), simplify it.

     For example,
     path = "/home/", => "/home"
     path = "/a/./b/../../c/", => "/c"

     time : O(n)
     space : O(n)


     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();//
        String[] paths = path.split("/+");//匹配一次或者多次/符号
        for (String s : paths) { //对于paths这个数组的每一个元素
            if (s.equals("..")) { //如果有的字符是 ..
                if (!stack.isEmpty()) { //如果栈非空
                    stack.pop(); //那么就pop出上一层即可
                }
            } else if (!s.equals(".") && !s.equals("")) { //如果元素既不是. 也不是“” 那么就说明他是文件夹名字（我们这时候要取反是因为文件夹名太多了）
                stack.push(s);//将其推到栈中
            }//如果元素是.或者是“” 什么操作都不做
        }
        String res = "";
        while (!stack.isEmpty()) { //
            res = "/" + stack.pop() + res;//因为栈弹出来是倒序 所以应该把其逐步往前加
        }
        if (res.length() == 0) { //如果执行完了前面的while语句 res还是空的 就说明stack就是空的
            return "/";//直接返回一个'/'即可
        }
        return res;
    }
}
