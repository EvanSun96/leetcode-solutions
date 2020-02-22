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
public class _84_LargestRectangleinHistogram {
    /**
     * 84. Largest Rectangle in Histogram
     * For example,
     Given heights = [2,1,5,6,2,3],
     return 10.

     stack : 1，升序，2，小于，计算
     也就是说 如果height一直是升序的话 就把其放入stack
     如果height有降低的话 进行面积的计算

     根据视频的讲解 存stack的时候 是从左往右存
     计算面积的时候 是从右往左每个都计算一手
      0,1,2,3,4,5,6
     [2,1,5,6,2,3,0]

     stack : 1
     2 : push
     1 : height = 2 start = -1 res = 2
     5 : push
     6 : push
     2 : height = 6 start = 2 area = 6 res = 6 //
         height = 5 start = 1 area = 10 res = 10//由于从6到5是下降的 因此pop出6 对5的计算没有影响 因为我知道这面积必定是5乘以某个数
     3 push
     0 : height = 2 start = 1 area = 8
         height = 1 start = -1 area = 6

     res = 10

     time : O(n)
     space : O(n)


     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();//use stack
        int res = 0;//
        for (int i = 0; i <= heights.length; i++) {//iterate heights array
            int h = i == heights.length ? 0 : heights[i];//如果i是height.length 就设h为0 否则设为height[i]
            while (!stack.isEmpty() && h < heights[stack.peek()]) {//当stack非空 并且  h < height[stack.peek()]
                int height = heights[stack.pop()];//如果遇到小的值 就将之Pop出来
                int start = stack.isEmpty() ? -1 : stack.peek(); //计算的起始index
                int area = height * (i - start - 1);//计算面积 高*宽
                res = Math.max(res, area);//维护最大值
            }
            stack.push(i);//stack里面存的是Index
        }
        return res;
    }
}
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }

        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i<=heights.length; i++){
            int h = i == heights.length ? 0 : heights[i];
            while(!stack.isEmpty() && h < heights[stack.peek()]){
                int height = heights[stack.pop()];
                int start = stack.isEmpty() ? -1 : stack.peek();
                int area = height * (i - start - 1);
                res = Math.max(res, area);
            }
            stack.push(h);//之所以一直通不过 是因为这里我Push的是h 而实际应该Push i
        }
        return res;
    }
}
