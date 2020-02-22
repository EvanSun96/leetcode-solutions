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
public class _66_PlusOne {
    /**
     * 66. Plus One
     * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

     You may assume the integer do not contain any leading zero, except the number 0 itself.

     The digits are stored such that the most significant digit is at the head of the list.

     case1 : 1011 1012
     case2 : 1099 1100
     case3 : 9999 10000

     time : O(n);
     space : O(n);
     * @param digits
     * @return
     */
    //之前在自己的写法中 将999此类的情况进行了特殊的单独处理 但是这样要搞得先对每个字符进行判断他是不是9 如果不是每个字符都是9的话 那么就正常进位
    //这样做是考虑array没有很好的空间延展性
    public static int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) { //
                digits[i]++;//小于9的直接加1就行
                return digits;//直接返回digits数组
            } else {
                digits[i] = 0;//否则此位变成0
            }
        } //如果前面整个for都没有返回 说明我们遇到了999+1的情况
        int[] res = new int[digits.length + 1];//直接新建一个长度+1的数组 里面所有的元素都被隐形初始化为0
        res[0] = 1;//只要改变第一个数即可

        return res;
    }
}
//上面的答案思路非常清晰 自己那种直来直去的方法属实不太行
//思想还没有转化成计算机思想
