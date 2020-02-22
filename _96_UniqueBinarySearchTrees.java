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
public class _96_UniqueBinarySearchTrees {
    /**
     * 96. Unique Binary Search Trees
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

     For example,
     Given n = 3, there are a total of 5 unique BST's.

     1         3     3      2      1
      \       /     /      / \      \
       3     2     1      1   3      2
      /     /       \                 \
     2     1         2                 3

     n = 3
     root : 1   left : 0 right : 2   f(0) * f(2); //当root是1的时候 左边不能有（其组合情况为f(0)） 右边可以有两个节点（其组合情况为f(2)）
     root : 2   left : 1 right : 1   f(1) * f(1); //当root是2的时候 左边要有一个（其组合情况为f(1)） 右边有一个节点（其组合情况为f(1)）
     root : 3   left : 2 right : 0   f(2) * f(0); //当root是3的时候 左边有两个（其组合情况为f(2)） 右边不能有（其组合情况为f(0)）

     f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)
     其DP方程如上：n个节点可以分成 左0右n 左一右n-1...
     因此n个节点可组成BST的数量 = 所有前面的方法数头乘以尾 注意我们既有f(0)*f(n-1) 也有f(n-1)*f(0)是因为左子树为0和右子树为0是两种不同的情况

     time : O(n);
     space : O(n);
     * @param n
     * @return
     */

    public int numTrees(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;//0个节点只有一种排列方式 之所以这还算一种排列方式是因为其他所有的res[i]的计算都用得到
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {//双指针控制头尾 每遍历一遍j得到一个res[i]
                res[i] += res[j] * res[i - j - 1];//j与i-j-1是关于（i-1）/2对称的，想象成i不动，j走一遍
            }
        }
        return res[n];//
    }
}
