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
public class _29_DivideTwoIntegers {
    /**
     * Divide two integers without using multiplication, division and mod operator.
     * 1, + -
     * 2, 越界
     * 3, = 0 3/5
     * 4, 正常

     time : O(logn * logn)
     space : O(logn)


     * @param dividend
     * @param divisor
     * @return
     */

    //注意 Intger整数的除法只有一种情况可能是越界的 就是-2^31 / -1,两个数都是在整数范围内 但是出出来的数确实越界的 其余的只管除好了 是不可能越界的
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = -1;
        long ldividend = Math.abs((long)dividend);//为什么要提前转化为Long？因为有一个特殊情况Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE 所以我们要对这种情况下的这个数先转换成long,再abs.其他的数不受任何影响
        long ldivisor = Math.abs((long)divisor);//我们这样就确保了无论dividend或者divisor无论是integer中的任何一个数 都能转换成其abs
        if (ldividend < ldivisor || ldividend == 0) return 0;//两种特殊情况的处理
        long lres = divide(ldividend, ldivisor); //
        int res = 0;
        if (lres > Integer.MAX_VALUE) { // 为什么要这样呢？
            res = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else res = (int)(sign * lres);
        return res;
    }

    public long divide(long ldividend, long ldivisor) { // 核心函数 采用尾递归 不太容易看懂 需要视频
        if (ldividend < ldivisor) return 0;
        long sum = ldivisor; //divisor
        long multiple = 1;
        while ((sum + sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        return multiple + divide(ldividend - sum, ldivisor); //尾递归
    }//tail recurion: divide(dividend, divisior) = part quotient(in the format of 2^x) + divide(remaining part, divisot)
//the general idea of this algorithm is use 2^x can formed any integer.
}
