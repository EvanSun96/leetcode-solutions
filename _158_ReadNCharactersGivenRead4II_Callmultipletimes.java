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
public class _158_ReadNCharactersGivenRead4II_Callmultipletimes {
    /**
     * 158. Read N Characters Given Read4 II - Call multiple times
     case :
     abcd efgh igk 11
     case 1 : n = 8 first time read 4 next count == 4 index == n
     case 2 : n = 7 first time read 4 next count == 3 index == n

     abcd efgh igk 11
     n = 2 count = 4  buf[ab] pointer = 2 temp[abcd]
     n = 3 index = 3  buf[cd] pointer = 0
            temp[efgh] count = 4 buf[cde] pointer = 1


     time : O(n);
     space : O(1);


     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */

    //建议不要去看屎老师的讲解 因为讲的真的跟屎一样 可以参照https://www.youtube.com/watch?v=w3ke3MQTEJ8&t=610s 开理解 能对这个问题有一个宏观上的理解 而且代码也很相似

    private int count = 0; //count is the return of read4(temp)
    private int pointer = 0; //index of temp
    private char[] temp = new char[4];

    public int read(char[] buf, int n) {
        int index = 0; //index is the pointer of n
        while (index < n) {
            if (pointer == 0) { //如果上次没读完的的已经读完了
                count = read4(temp);//就新request四个字符
            }
            if (count == 0) break;//如果count==0 代表已经没有新的数据可以去读了 都已经读完了
            while (index < n && pointer < count) { //if index is no finished and pointer is not reaching the end of count
                buf[index++] = temp[pointer++]; //wrtie temp[point] to buf[index] till while is end
            }
            if (pointer == count) pointer = 0;//if pointer reach the end of temp, the we set pointer as 0, means to start another line(request a new read4(temp));
        }
        return index;
    }

    //辅助函数，正常不是这么写
    public int read4(char[] temp) {
        char[] res = new char[10];
        char[] ret = new char[4];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            if (index < 4){
                ret[index++] = temp[i];
            }
        }
        return index;
    }
}
