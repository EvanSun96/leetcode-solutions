package leetcode_1To300;

import java.util.Arrays;

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
public class _135_Candy {
    /**
     * 135. Candy
     * There are N children standing in a line. Each child is assigned a rating value.

     You are giving candies to these children subjected to the following requirements:

     Each child must have at least one candy.
     Children with a higher rating get more candies than their neighbors.
     What is the minimum candies you must give?

     ratings:     [4, 5, 1, 1, 3, 7]
     candies:     [1, 1, 1, 1, 1, 1]

     ratings:     [4, 5, 1, 1, 3, 7]
     candies:     [1, 2, 1, 1, 2, 3]

     ratings:     [4, 5, 1, 1, 3, 7]
     candies:     [1, 2, 1, 1, 2, 3]

     time : O(n)
     space : O(n)


     * @param ratings
     * @return
     */
    //就是一个简单的DP candy[i]代表第i个人至少应该有多少个糖果
    //为什么说这道题是个DP 是因为candy[i]受candy[i-1]和candy[i+1]的影响
    //但是一般来说 DP总是前面的影响后面的 所以我们从后往前遍历。但是现在candy[i]受前后都影响 所以答案想到了从前到后 和 从后往前 遍历两遍

    //宏观来说 就是正向遍历一遍 反向遍历一遍 将candy这个数组补全调整好
    //
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1); //首先都分配1 因为至少为1

        for (int i = 1; i < candies.length; i++) { //从前往后遍历一遍 如果后一个大于前一个
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;//就加1
            }
        }
        for (int i = candies.length - 2; i >= 0; i --) { //从后往前遍历 如果前一个大于后一个 说明前一个分的糖果一定比后一个多，但是我们并不知道candies[i]和candies[i+1]+1哪个更大
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int res = 0;
        for (int candy : candies) {
            res += candy;
        }
        return res;
    }
}
