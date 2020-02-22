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
public class _73_SetMatrixZeroes {
    /**
     73. Set Matrix Zeroes
     [
      [1, 0, 1, 1],
      [1, 1, 0, 0],
      [0, 1, 1, 1],
      [1, 1, 1, 1]
     ]

     time : O(n * m)
     space : O(1)

     * @param matrix
     */
    //宏观上来说 答案大致上是这个意思：
    //将此行或者此列有0的这个信息投影到matrix的第一行和第一列上 就跟我自己写的row和col两个额外的数组一样
    //但是这会带来一个问题 就是之前的第一行和第一列的信息被覆盖了
    //所以用row和col两个变量来标记第一行或者第一列中含有0的情况 一旦含有 整行或者整列都要变成0
    //更宏观一点就是先扫描matrix,然后先管 非第一行或者非第一列，然后再管第一行和第一列 分为五个部分写
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean row = false;
        boolean col = false;
        for (int i = 0; i < m; i++) {//扫描Matrix
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {//如果某元素为0
                    matrix[0][j] = 0;//那么把对应的第一行此列
                    matrix[i][0] = 0;//把对应的此行第一列 都设为0
                    if (i == 0) row = true; //如果此时就是第一行 说明这一行要被全部变成0
                    if (j == 0) col = true;//或者如果此时就是第一列 那么就分别设row或者col为true
                }//也就是说 如果第一行或者第一列有0 那么就row 和col就保持true
            }
        }
        for (int i = 1; i < m; i++) {//从第二行开始扫描 如果此行有0 那么全变为0
            if (matrix[i][0] == 0) {//如果每行开头是0 那么说明这一行中含有0
                for (int j = 1; j < n; j++) {//从第二列开始扫描
                    matrix[i][j] = 0;//
                }
            }
        }
        for (int j = 1; j < n; j++) {//从第二列开始扫描 如果此列有0 就全变为0
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (row) {//如果第一行含有0 那么就把第一行全部变成0
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col) {//同理
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

//自己写的 空间复杂度为O(m+n) 不如答案常数复杂度好
class Solution {//又是一遍过 感觉心情十分愉悦
    public void setZeroes(int[][] matrix) {
        //do it in place不代表不能用额外的空间 而是说直接在输入上修改 返回值为void
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];//只用了O(m+n)的复杂度
        boolean[] col = new boolean[n];

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(matrix[i][j] == 0){
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for(int i = 0; i<m;i++){
            for(int j = 0; j<n; j++){
                if(row[i] || col[j]){//this col has a 0, we should set all col as 0
                    matrix[i][j] = 0;
                }
            }
        }

    }
}
