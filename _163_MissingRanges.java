package leetcode_1To300;

import java.util.ArrayList;
import java.util.List;

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
public class _163_MissingRanges {
    /**
     * 163. Missing Ranges
     * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper],
     * return its missing ranges.

     For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

     [2147483647] 0 2147483647
     ["0->2147483646"]
     ["0->2147483646","-2147483648->2147483647"]

     time : O(n)
     space : O(n)

     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    //宏观的思路很简单 就是把lower和upper当作是两个指针 lower指针不断上移 直到抵达upper指针位置 upper指针在整个过程中没变
    //而且大多数时候要分成两种情况添加到res中 一种是alower与num相邻，这样的话只用添加一个数字到res即可 一种是不相邻 这个时候要添加成区间形式
    //而且还有一个要注意：就是溢出的问题 这个没太搞懂
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        long alower = (long)lower, aupper = (long)upper;//怕溢出就long化
        for (int num : nums) {
            if (num == alower) {  //if num == alower
                alower++; //then we can update the lower bound
            } else if (alower < num) { //if num > alwower, then we have a space between lower bound and num
                if (alower + 1 == num) { //如果alower与num之间就差了1
                    res.add(String.valueOf(alower));//那么就加上alower这个数即可
                } else { //如果差了不止一个
                    res.add(alower + "->" + (num - 1));//那么就要加一个区间
                }
                alower = (long)num + 1;//将alower区间往上提 注意：这里为什么要将num前面加个(long)呢？而且不加的话还没法通过
            }
        }//注意 每一次alower指针停止的位置都是num+1的位置
        if (alower == aupper) res.add(String.valueOf(alower));//如果最后alower指针停止的位置和aupper指针重合（这时候只用添加进去一个数即可） 将alower指针数转换为String
        else if (alower < aupper) res.add(alower + "->" + aupper);//如果alower还没到达auuper的高度  就添加进去一个区间
        return res;
    }
}
