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
public class _121_BestTimetoBuyandSellStock {
    /**
     * 121. Best Time to Buy and Sell Stock
     * Say you have an array for which the ith element is the price of a given stock on day i.

     If you were only permitted to complete at most one transaction (ie,
     buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     Input: [7, 1, 5, 3, 6, 4]
     Output: 5

     max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)

     time : O(n);
     space : O(1);

     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int min = prices[0];
        int profit = 0;
        for (int price : prices) {
            min = Math.min(min, price);//当前位置的min 是当前price和之前所有的price中最小的
            profit = Math.max(profit, price - min);//维护一个最大的profit 用当前的price-之前出现过的最小的来做比较
        }
        return profit;
    }
}
//下面是自己写的 复杂度非常差 但是跑出来了
//给定一个数组 里面是每一天股票的价格
//题目的意思罗里吧嗦 就是找出 最大的递增的ij指针 看看第一个例子就知道了
class Solution {
    public int maxProfit(int[] prices) {
        int res = Integer.MIN_VALUE;
        for(int i = 0; i<prices.length - 1; i++){
            for(int j = i + 1; j<prices.length; j++){
                res = Math.max(res, prices[j] - prices[i]);
            }
        }
        if(res <= 0){
            return 0;
        }
        return res;
    }
}
