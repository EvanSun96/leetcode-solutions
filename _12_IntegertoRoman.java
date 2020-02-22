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
public class _12_IntegertoRoman {
    /**
     *
     time : O(n)
     space : O(n)
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }
}
//最初的想法是根据规则 判断各种条件来实现两种数字系统的转换
//而且最初还想用hashmap把value和strings进行一一对应

//但是答案很明显 就是解析了题干所定的规则 因为就两种情况 要么如4，9，40，90...这类数是一种处理 其他的情况就是正常的累加情况
//即只要剩余的num >= values[i] 就一直累加
//这就是数字转罗马数字还是什么别的计数系统的精髓。

//情况也不多 只要穷举 而且规则对应载体也没有选择键值对类型的 比如hashmap等等 而是用了两个数组
//值得深思
