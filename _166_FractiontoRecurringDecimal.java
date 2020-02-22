package leetcode_1To300;

import java.util.HashMap;

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
public class _166_FractiontoRecurringDecimal {

    /**
     * 166. Fraction to Recurring Decimal
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

     If the fractional part is repeating, enclose the repeating part in parentheses.

     For example,

     Given numerator = 1, denominator = 2, return "0.5".
     Given numerator = 2, denominator = 1, return "2".
     Given numerator = 2, denominator = 3, return "0.(6)".

     0.2(34)

     1, 0 + - 越界
     2, 整数
     3, 小数 -> 循环

     time : O(logn)
     space : O(n)

     * @param numerator
     * @param denominator
     * @return
     */
    //很简明的一道题
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) { //如果分子为0 直接返回0
            return "0";
        }
        StringBuilder res = new StringBuilder();
        res.append(((numerator > 0) ^ (denominator > 0) ? "-" : ""));//异或运算符 相同为0 不同为1
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);//又变成long型数字了

        res.append(num / den); //计算整数部分
        num %= den;//num更新为余数部分
        if (num == 0) {
            return res.toString();//如果余数为0 正好整除 直接将res转换为String
        }

        res.append(".");//如果余数不为0 就要进入小数阶段 先在res上加个小数点就可以了
        HashMap<Long, Integer> map = new HashMap<>(); //用hashMap来判断是不是 存在无线循环的数
        map.put(num, res.length());

        while (num != 0) { //当余数不为0
            num *= 10; //自乘以10 不自乘的话num/den将会一直为0了
            res.append(num / den);//
            num %= den; //然后num更新一下
            if (map.containsKey(num)) { //检查map中是不是有这个key 如果有 就说明之前出现过这个数
                int index = map.get(num);//采取加括号的方式
                res.insert(index, "(");
                res.append(")");
                break;
            } else { //如果没有这个键 就作为一个新的键放进去
                map.put(num, res.length());//不需要append 因为之后会自然append
            }
        }
        return res.toString();//最后一定不要忘记将stringBuilder转化为String
    }
}
