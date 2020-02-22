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
public class _77_Combinations {

    /**
     * 77. Combinations
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

     For example,
     If n = 4 and k = 2, a solution is:

     [
     [2,4],
     [3,4],
     [2,3],
     [1,2],
     [1,3],
     [1,4],
     ]
     [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]

     time : O(n^min{k,n-k})
     space : O(n);
     http://stackoverflow.com/questions/31120402/complexity-when-generating-all-combinations
     * @param n
     * @param k
     * @return
     */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int n, int k, int start) {
        if (k == 0) {
            res.add(new ArrayList<>(list));//因为规定了list是个List 所以要转换一下？list为什么不可以直接定义ArrayList呢？
            return;
        }
        for (int i = start; i <= n; i++) { //之前的排列 这儿每次都是从1开始到n 然后在内部剪掉已经存在的枝，现在的组合因为是按照顺序来的 因此每一次都要进行不一样的剪枝
            list.add(i);
            helper(res, list, n, k - 1, i + 1);
            list.remove(list.size() - 1);//返回上一层
        }
    }
}
