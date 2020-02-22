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
public class _50_Pow {
    /**
     * Implement pow(x, n).


     eg. 2^2 = 2^1 * 2^1 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) = (1 * 1 * 2) * (1 * 1 * 2) = 4

     eg. 2^3 = 2^1 * 2^1 * 2 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) * 2 = (1 * 1 * 2) * (1 * 1 * 2) * 2 = 8

     time : O(logn);
     space : O(logn)/O(1)

     * @param x
     * @param n
     * @return
     */
    public static double myPow1(double x, int n) {
        if (n > 0) {
            return pow(x, n);
        } else {
            return  1.0 / pow(x, n);
        }
    }
    public static double pow (double x, int n) { //正负分离 不用考虑唯一的溢出情况 n为负的也照样去计算pow(x,n)
        if (n == 0) {
            return 1;
        }
        double y = pow(x, n / 2);//递归写法 递归语句前后都有return 语句
        if (n % 2 == 0) { //能被2整除
            return y * y;
        } else { //
            return y * y * x;
        }
    }
    //递归大概就是这个意思：
    //eg. 2^2 = 2^1 * 2^1 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) = (1 * 1 * 2) * (1 * 1 * 2) = 4
    //
    //eg. 2^3 = 2^1 * 2^1 * 2 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) * 2 = (1 * 1 * 2) * (1 * 1 * 2) * 2 = 8

    public static double myPow2(double x, int n) {
        if (n == 0) return 1;
        double res = 1;
        // int : -6.. ~ +6..  -2^32 ~ 2 ^32-1 Integer.MIN_VALUE
        long abs = Math.abs((long)n);//虽然是n是int 但是其绝对值有可能溢出(也就是说 如果取绝对值的话 必须考虑那个唯一的溢出情况)
        while (abs > 0) {
            if (abs % 2 != 0) { //他是将abs暂时不能被2整除
                res *= x; //那么就res就自乘以上一次的x
            }
            x *= x; ///x自身累乘
            abs /= 2;//
        }
        if (n < 0) {
            return 1.0 / res;
        }
        return res;
    }
}
