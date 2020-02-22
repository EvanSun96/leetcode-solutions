package leetcode_1To300;

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
public class _120_Triangle {
    /**
     * 120. Triangle
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers
     * on the row below.

     For example, given the following triangle
     [
        [2],
       [3,4],
      [6,5,7],
     [4,1,8,3]
     ]
     The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

     i : j
     i + 1 : j, j + 1

     res = [4, 1, 8, 3, 0]
     res = [7, 6, 10]
     res = [9, 10]
     res = [2]

     time : O(n^2)
     space : O(n)


     * @param triangle
     * @return
     */
    //与之前的DP矩阵相比 这个只有一半 每个点只能由上面的点
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] res = new int[triangle.size() + 1]; //长度为m+1
        for (int i = triangle.size() - 1; i >= 0; i--) { //i指针从后往前遍历 i指针走m次
            for (int j = 0; j < triangle.get(i).size(); j++) { //j指针遍历
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }
        return res[0];//之所以最后只返回res[0]即可 因为他是倒序来的 是一个倒三角形的DP 越缩越小 所以最后只有一个结果 而且这样做还比正三角形节省了不少空间 因为正三角形不能用O(n)因为覆盖的问题
    }

}
//三角形矩阵中的从上到下的最短路径 有点像树里面的最短路径 而且只需要最短路径长即可

//下面是自己写的 但是总是不对 感觉思路上没有任何问题
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();

        int[][] dp = new int[m][m];
        dp[0][0] = triangle.get(0).get(0);

        for(int i = 1; i<m; i++){
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j<i; j++){
                dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
            }
        }
        int res = 0;
        for(int i = 0;i < m-1; i++){
            res = Math.min(dp[m-1][i], dp[m-1][i+1]);
        }

        return res;
    }
}
