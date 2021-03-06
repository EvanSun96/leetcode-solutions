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
public class _11_ContainerWithMostWater {

    /**
     * time : O(n)
     * space : O(1)
     * @param height
     * @return
     */

    public int maxArea(int[] height) {
        int res = 0;//还要注意 这里因为高度都是正值 所以初始res取0即可 但是若有负值 那么初始res就要取Integer.MIN_VALUE了
        int l = 0, r = height.length - 1;
        while (l < r) {
            res = Math.max(res, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {//感觉不太对劲 这只是把通过改变限制条件 来获得可能的更大值
                l++;
            } else r--;
        }
        return res;
    }
}
