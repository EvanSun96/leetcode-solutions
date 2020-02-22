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
public class _157_ReadNCharactersGivenRead4 {
    /**
     * 157. Read N Characters Given Read4
     case :
     abcd efgh igk 11
     case 1 : n = 8 first time read 4 next count == 4 index == n
     case 2 : n = 7 first time read 4 next count == 3 index == n

     case :
     abc 3
     case 1 : n = 4 count = 3 count < 4

     time : O(n);
     space : O(1);


     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    //本题在fackbook面经中出现过 还是挺重要的
    //代码很好理解 背下来即可
    public int read(char[] buf, int n) {
        char[] temp = new char[4];//不明白着temp是干嘛用的 我将4改成3或者三以上的任何数字 都能通过 但是一旦改成2 就通不过
        int index = 0;
        while (true) {
            int count = read4(temp); //count统计read4中的字符数 最多为4 但是也可能为3 2 1
            count = Math.min(count, n - index);//如果取了count 说明还有大于等于4的量 否则就是只有不足4的量
            for (int i = 0; i < count; i++) { //运行count次
                buf[index++] = temp[i]; //每一次都从temp中读出前四个放入buf数组中，index指针一直在增长
            }
            //如果n取得小于4 那么就直接count = 3/2/1 直接往下走 最后输出就是3 2 1
            //如果n取得大于4 比如说是6 那么第一遍循环 count取满取到4 然后经过for index到达4 后面也不return. 第二遍循环 count取不满 只能取2 index到达6 返回index
            //如果n取得等于4 那么count取满为4 index到达4 直接返回即可
            if (index == n || count < 4) return index; //如果index到达了n 或者 count<4
        }
    }

    /**
     * abcdefghijk
     * char[] temp = new char[4]; temp : ijk 3
     *
     * @param temp
     * @return
     */

    //辅助函数，正常不是这么写
    public int read4(char[] temp) {
        char[] res = new char[10];
        char[] ret = new char[4];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            if (index < 4){
                ret[index++] = temp[i];//每次最多只能读4个
            }
        }
        return index;//返回停止的index 最多为4
    }
}
