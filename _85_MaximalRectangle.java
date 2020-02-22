package leetcode_1To300;

import java.util.Arrays;
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
public class _85_MaximalRectangle {
    /**
     * 85. Maximal Rectangle
     * Given a 2D binary matrix filled with 0's and 1's,
     * find the largest rectangle containing only 1's and return its area.

     For example, given the following matrix:

     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     Return 6.


     left[] ：从左到右，出现连续‘1’的string的第一个座标
     right[] ：从右到左, 出现连续‘1’的string的起始的最后一个座标，
     height[] ： 从上到下的高度。
     res ： (right[j] - left[j]) * heights[j]
     
     height:
     1 0 1 0 0
     2 0 2 1 1
     3 1 3 2 2
     4 0 0 3 0

     left:
     0 0 2 0 0
     0 0 2 2 2
     0 0 2 2 2
     0 0 0 3 0

     right:
     1 5 3 5 5
     1 5 3 5 5
     1 5 3 5 5
     1 5 5 4 5

     time : O(m * n)
     space : O(n)

     * @param matrix
     * @return
     */

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (matrix == null || m == 0) return 0;
        int n = matrix[0].length;
        int res = 0;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);//我们需要显式将right初始化 起始height,left已经被隐性初始化为0了

        //由inner loop可知 所有的数组都是一行一行的来写的 这一轮写完就用
        for (int i = 0; i < m; i++) {
            int curLeft = 0, curRight = n;//curLeft和curright分别标定当前这个正方形的左右边界，就好比是curLeft对left数组的作用是作为某个阶段fixed 左指针，j为右指针尽量向右拓展
            ////同理 curRight作为暂时fixed右指针 j作为左指针不断尽可能延伸
            for (int j = 0; j < n; j++) {//构建height数组
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            for (int j = 0; j < n; j++) {//构建left数组
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(curLeft, left[j]);//注意 left数组中存的是Index
                } else { //matrix此位置是0 那么这个位置就没有连续的1
                    left[j] = 0;//这个else内部的设定是什么含义
                    curLeft = j + 1;//
                }
            }
            for (int j = n - 1; j >= 0; j--) {//构建right数组
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(curRight, right[j]);//从后往前走 不断取最小值
                } else {
                    right[j] = n;//
                    curRight = j;
                }
            }
            for (int j = 0; j < n; j++) { //res也是一层一层的 比如说当i进行到4，所有的数列搜集的都是第四行以上的数据
                res = Math.max(res, (right[j] - left[j]) * height[j]);//这里就能看出来为什么right[j]要比实际Index大1了 因为这里就不用加1了啊
            }
        }
        return res;
    }
    //宏观的来说 对于第i次loop, j 位置对应的left[j] right[j] height[i] 分别意味着这个点左边最远可以延伸到哪里 这个点右边最远可以延伸到哪里 上面到此地方的1最远可以延伸到哪里。 注意 当原矩阵此处为0的视乎 height直接等于0 即面积直接为0
    //更宏观的来说 就是外层一个for loop 负责控制一行一行地走
    //然后内层四个平行loop,前三个负责写height, left, right数组 最后一个负责loop负责维护面积最大值
    //之前说的对 每个坐标都要计算一下其能拓展的最大面积

    /**
     time : O(m * n)
     space : O(n)

     * @param matrix
     * @return
     */

    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] height = new int[n + 1];//只用了一个height数组
        height[n] = 0;
        int res = 0;

        for (int row = 0; row < matrix.length; row++) {//行
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n + 1; i++) {//
                if (i < n) { //如果i在matrix里面作为列数是合法的
                    if (matrix[row][i] == '1') {
                        height[i]++;
                    } else height[i] = 0;//构建height数组的方式跟上个方法一样
                }
                if (stack.isEmpty() || height[stack.peek()] <= height[i]) {//stack存的是index 比上一个大 就Push进去
                    stack.push(i);
                } else {
                    while (!stack.isEmpty() && height[i] < height[stack.peek()]) {//比上一个小 就while操作一下
                        int cur = height[stack.pop()] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                        res = Math.max(res, cur);
                    }
                    stack.push(i);//最后不要忘了还是要把当前i push进去
                }
            }
        }
        return res;
    }
}
