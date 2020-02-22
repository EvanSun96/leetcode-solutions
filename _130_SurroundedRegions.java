package leetcode_1To300;

import java.util.LinkedList;
import java.util.Queue;

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
public class _130_SurroundedRegions {

    /**
     * 130. Surrounded Regions
     * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

     A region is captured by flipping all 'O's into 'X's in that surrounded region.

     For example,
     X X X X
     X O O X
     X X O X
     X O X X

     After running your function, the board should be:

     X X X X
     X X X X
     X X X X
     X O X X

     time : O(m * n)
     space : O(n)

     * @param board
     */

    //宏观来说 第一种方法就是 把所有的边界上的O进行DFS 将这些O变为1 最终这个棋盘分为三部分：1 O 和X 这个时候 其实剩余的O就是我们所要的改变的成X的O
    //然后对整个棋盘进行遍历 把所有的O变为X 把所有的1变回O
    public void solve(char[][] board){
        if (board == null || board.length == 0 || board[0].length == 0) return;

        int m = board.length - 1;
        int n = board[0].length - 1;

        for (int i = 0; i <= m; i++) { //按行遍历
            if (board[i][0] == 'O') { //如果当前行开头是O
                dfs(board, i, 0);//就从这个点进行DFS DFS会将从当前点出发 将其所能达到的O都改成1
            }
            if (board[i][n] == 'O') {//如果当前行最后一个元素是O
                dfs(board, i, n);
            }
        }
        for (int i = 0; i <= n; i++) {
            if (board[0][i] == 'O') { //如果当前列开头是O
                dfs(board, 0, i);
            }
            if (board[m][i] == 'O') {//如果当前行结尾是O
                dfs(board, m, i);
            }
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) { //遍历整个棋盘
                if (board[i][j] == 'O') { //某个位置为O
                    board[i][j] = 'X';//换成X
                } else if (board[i][j] == '1') {//某个位置为1
                    board[i][j] = 'O';//换成O
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) { //对全部棋盘进行DFS
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') { //如果超出界限并且当前位置不为O（之前写的board[i][j] == 'X'一直无法通过 一直在报错栈溢出。我知道调用DFS的时候 已经存在1和OX了，因此遇到1的话也要停止递）
            return;
        }
        board[i][j] = '1';//没有超出边界并且ij处是O 将其改为1这个字符
        dfs(board, i, j + 1); //四路递归 向四个方向
        dfs(board, i, j - 1);
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
    }

    class Point {
        int x;
        int y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public void solve2(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        int m = board.length - 1;
        int n = board[0].length - 1;
        Queue<Point> queue = new LinkedList<>();

        //get all 'O's on the edge first
        for (int r = 0; r <= m; r++) {
            if (board[r][0] == 'O') {
                board[r][0] = '1';
                queue.add(new Point(r, 0));
            }
            if (board[r][n] == 'O') {
                board[r][n] = '1';
                queue.add(new Point(r, n));
            }
        }

        for (int i = 0; i < n; i++){
            if (board[0][i] == 'O') {
                board[0][i] = '1';
                queue.add(new Point(0, i));
            }
            if (board[m][i] == 'O') {
                board[m][i] = '1';
                queue.add(new Point(m, i));
            }
        }

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int row = p.x;
            int col = p.y;
            if (row - 1 >= 0 && board[row - 1][col] == 'O') {
                board[row - 1][col] = '1';
                queue.add(new Point(row - 1, col));
            }
            if (row + 1 < m && board[row + 1][col] == 'O') {
                board[row + 1][col] = '1'; queue.add(new Point(row + 1, col));
            }
            if (col - 1 >= 0 && board[row][col - 1] == 'O') {
                board[row][col - 1] = '1';
                queue.add(new Point(row, col - 1));
            }
            if (col + 1 < n && board[row][col + 1] == 'O') {
                board[row][col + 1] = '1';
                queue.add(new Point(row, col + 1));
            }
        }
        
        for (int i = 0; i<= m; i++){
            for (int j=0; j<= n; j++){
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '1') board[i][j] = 'O';
            }
        }
    }

}
