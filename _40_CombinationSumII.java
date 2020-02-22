package leetcode_1To300;

import java.util.ArrayList;
import java.util.Arrays;
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
public class _40_CombinationSumII {

    /**
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

     Each number in C may only be used once in the combination.

     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
     A solution set is:
     [
     [1, 7],
     [1, 2, 5],
     [2, 6],
     [1, 1, 6]
     ]

     [1,1,2,5,6,7,10]
     [[1,1,6],[1,2,5],[1,7],[2,6]]

     time : O(2^n)
     space : O(n) 或者说是 O(n^2)，根据结果而不同
     * @param candidates
     * @param target
     * @return
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates); //先对candiate sort一下 这是第一处不同
        helper(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i != start && candidates[i] == candidates[i - 1]) continue;` `//如果candidate当前和之前的不同 那么直接continue 这是第二处不同
            list.add(candidates[i]);
            helper(res, list, candidates, target - candidates[i], i + 1); //最后一个参数每次多加了1 这是第三处不同
            list.remove(list.size() - 1);
        }
    }
}
//根据youtube相关视频所讲 https://www.youtube.com/watch?v=RSatA4uVBDQ
//如果需要最后去重 那么只需要将List<List<Integer>>转化成 HashSet<List<Integer>> 每次插进去新的列表的时候会自动去重。
//如果不是最后去重 我们需要用DFS 在当前层中不能用与上一层一样的数，但是再下一层可以（？）
//好像这意思就是说[1,1,7,8] 我们认为[1,7] [1,7]是一样的---妈的还是没懂
