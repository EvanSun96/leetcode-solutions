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
public class _79_WordSearch {
    /**
     * 79. Word Search
     * For example,
     Given board =

     [
     ['A','B','C','E'],
     ['S','F','C','S'],
     ['A','D','E','E']
     ]
     word = "ABCCED", -> returns true,
     word = "SEE", -> returns true,
     word = "ABCB", -> returns false.


     time : 不知道
     space : 不知道

     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {//每一个位置都要试一下
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, i, j, word, 0)) {//在每一个点都有一个递归函数
                    return true;
                }
            }
        }
        return false;
    }
    private boolean exist(char[][] board, int i, int j, String word, int start) {//
        if (start >= word.length()) return true;//如果start成功走到大于等于word.length层 那么必然是找到了
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;//超过边界返回false 注意这里的边界等于号
        if (board[i][j] == word.charAt(start++)) {//注意到start是在不断递增的
            char c = board[i][j];//暂存
            board[i][j] = '#';//因为不能重复用 所以要先把这个地方锁掉？
            boolean res = exist(board, i + 1, j, word, start) ||
                    exist(board, i - 1, j, word, start) ||
                    exist(board, i, j + 1, word, start) ||
                    exist(board, i, j - 1, word, start);//if语句中的四路递归 任何一路走得通
            board[i][j] = c;//恢复
            return res;
        }
        return false;
    }
}
