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
public class _39_CombinationSum {

    /**
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
     * find all unique combinations in C where the candidate numbers sums to T.

     The same repeated number may be chosen from C unlimited number of times.

     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [2, 3, 6, 7] and target 7,
     A solution set is:
     [
     [7],
     [2, 2, 3]
     ]

     time : O(2^n)
     space : O(n) 或者说是 O(n^2)，根据结果而不同
     * @param candidates
     * @param target
     * @return
     */

    //本体给出的输入数组是没有duplicates 每个元素只能用一次
    //紧跟着的第39题是有duplicates,每个元素只能用一次 也就是相当于每个元素最多只能用给定的次数次 仔细想想有哪些区别呢 处理有什么不一样的地方呢？
    //仔细想了一下 如果第39也用本题的方法 会出现最后的结果nestlist中 有一摸一样的组合list
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        helper(res, new ArrayList<>(), candidates, target, 0);//起始入口
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) { //参数有res, res组成单元（which is a list）（这个是逐渐形成的）, candiate就是输入的列表，target, 和初始index
        if (target < 0) return; //return 就是主动返回上一层
        if (target == 0) {
            res.add(new ArrayList<>(list)); //等于0的时候就将整个数组添加进去 这儿要注意一下 添加的是将list转化成ArrayList之后的结果
            return; //这个return 也是主动返回上一层
        }
        for (int i = start; i < candidates.length; i++) { //从index=start开始，遍历candiate的所有元素
            list.add(candidates[i]);//先添加
            helper(res, list, candidates, target - candidates[i], i);//递归 每次 target变化 start也变化
            list.remove(list.size() - 1); //
        }//注意 helper每一次并不会返回什么 只是在不断更新list ，每次从底层返回上一层的时候 如果其没有主动remove掉最后一个（说明添加进去的candiate[i]不合适，需要删除一手，然后试新的candiate[i]），那就说明执行了之前的两个if
    }
}//这种把递归函数放在for loop中还是很难去理解

//这道题最开始想是用DP 通过递归的方式完成 但是需要提前sort一下candidate,但是给出的答案却没有预先进行sort
