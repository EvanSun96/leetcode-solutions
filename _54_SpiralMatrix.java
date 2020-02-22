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
public class _54_SpiralMatrix {
    /**
     * 54. Spiral Matrix
     * For example,
     Given the following matrix:

     [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
     ]
     You should return [1,2,3,6,9,8,7,4,5].

     time : O(n * m)     n * m : 总元素个数
     space : O(n * m)

     * @param matrix
     * @return
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }
        int rowBegin = 0;//用了四个指针控制上下左右边界
        int rowEnd = matrix.length - 1; //左右都是闭区间 因此后面都是大于等于或者小于等于号
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) { //当指针
            for (int i = colBegin; i <= colEnd; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++; //rowBegin指针+1

            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--; //colEnd指针-1

            if (rowBegin <= rowEnd) { //为什么还要再加一个if? 因为前面对rowBegin做了更改 我们要时时刻刻注意更改后的指针位置是不是合法 而不是每一次都完整的走过4个for 每一次while都完整的走过4个for是在矩阵为正方形的时候才会出现
                for (int i = colEnd; i >= colBegin; i--) {//这个for和下面的一个for是选择性的
                    res.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;//但是每一遍rowEnd指针和colBegin指针是必须要移动的

            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res.add(matrix[i][colBegin]);
                }
            }
            colBegin++;
        }

        return res;
    }
}
