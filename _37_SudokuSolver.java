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
public class _37_SudokuSolver {

    // time : 不知道 space : 不知道

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) { //iterate the board
                if (board[i][j] == '.') { //
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) { //use c from '1' to '9' to check if it is valid for c to be here, if it is valid
                            board[i][j] = c;
                            if (solve(board)) return true; //if we 沿着当前的路走直到底层，every level contains at least one returns true
                            else board[i][j] = '.'; //if not, means some level is corrupt, so we have to go back here and choose another c
                        }
                    }
                    return false; //if we tries every c but any of that didn't work, then we return false to show this way is dead, we have to find another way at previous level
                }
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int row, int col, char c) { //check if char c placed in board[row][col] is valid
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false; //if same col has this char c already
            if (board[row][i] == c) return false; //if same row has this char c already
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
                    && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false; //
            }
        }
        return true;
    }
}
