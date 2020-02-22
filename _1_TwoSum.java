package leetcode_1To300;

import java.util.HashMap;

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
public class _1_TwoSum {

    /**
     * time : O(n)
     * space : O(n)
     * @param nums
     * @param target
     * @return
     */

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) { //特殊情况的处理
            return new int[]{-1, -1};//初始化的时候放值
        }

        int[] res = new int[]{-1, -1};
        HashMap<Integer, Integer> map = new HashMap<>();//利用hashmap初始化

        for (int i = 0; i < nums.length; i++) {//这个想法是反向思维 instead of 加和值为target
            if (map.containsKey(target - nums[i])) {//如果这个hashmap存在两个值 一个是nums[i]另一个是target-nums[i]就可以
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            }
            map.put(nums[i], i);
        }//每一次的for 分成两个阶段 先是验证阶段 验证成功 直接break.第二个阶段是放入阶段。这也与常识相悖，但是仔细一想 当仅仅只有i=0的时候 map里面什么也没有 可定是需要把其先放入 但是其他的就要先验证 再放入了

        return res;
    }
}
