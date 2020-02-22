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
public class _78_Subsets {
    /**
     * 78. Subsets
     * Given a set of distinct integers, nums, return all possible subsets.

     Note: The solution set must not contain duplicate subsets.

     For example,
     If nums = [1,2,3], a solution is:
     [
     [3],
     [1],
     [2],
     [1,2,3],
     [1,3],
     [2,3],
     [1,2],
     []
     ]

     test : [1,2,3]

     []
     [1]
     [1, 2]
     [1, 2, 3]
     [1, 3]
     [2]
     [2, 3]
     [3]

     1 — 2 - 3
     |   |
     2   3
     |
     3



     time : O(2^n);
     space : O(n);
     * @param nums
     * @return
     */
    //宏观上来说 第一种方法是一个一个subset构建的 构建完了一个完整的subset才回溯构建下一个
    //第二种方法是所有的subset一起构建的 每次内层遍历一遍就相当于是给每个subset添加了一层，添加的最大层数由外层loop决定
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) { //排列组合算法的核心还保留着
            list.add(nums[i]);
            helper(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> subsets2(int[] nums) {
        int length = nums.length;
        int size = (int) Math.pow(2, length);
        List<List<Integer>> subsets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            subsets.add(new ArrayList<>());//首先初始化一个二维数组subset,初始化长度为2的length次方 因为2的length次方就是subset的个数
        }

        for (int i = 0; i < length; i++) { //
            for (int j = 0; j < size; j++) { //按照顺序写入subset
                if ((j >> i & 1) == 1) {//只有j>>i是奇数 这个if语句才成立 虽然不明白原理 但是跑一下case 是对的
                    subsets.get(j).add(nums[i]);
                }
            }
        }
        return subsets;
    }
}

//想到了跟组合之间的联系 勉强自己写出来了...
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        //与LC77相对应 排列组合问题就像是给定枝数和层数 让你输出所有的结果
        //这道题一眼看上去 我也知道是要用递归
        //仔细一想 这个其实跟组合很像，就是把Cn0, Cn1, Cn2, Cn3....Cnn都给输出来即可
        //这代码属实有点low,但是终归是自己写出来了
        for(int i = 0; i <= nums.length; i++){
            combine(nums, nums.length, i);
        }
        return res;
    }

    public void combine(int[] nums, int n, int k) {
        //List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums, n, k, 1);
        //return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int n, int k, int start) {
        if (k == 0) {
            res.add(new ArrayList<>(list));//因为规定了list是个List 所以要转换一下？list为什么不可以直接定义ArrayList呢？
            return;
        }
        for (int i = start; i <= n; i++) { //之前的排列 这儿每次都是从1开始到n 然后在内部剪掉已经存在的枝，现在的组合因为是按照顺序来的 因此每一次都要进行不一样的剪枝
            list.add(nums[i - 1]);
            helper(res, list, nums, n, k - 1, i + 1);
            list.remove(list.size() - 1);//返回上一层
        }
    }
}
