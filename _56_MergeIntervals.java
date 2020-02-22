package leetcode_1To300;

import java.util.ArrayList;
import java.util.Collections;
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
public class _56_MergeIntervals {
    /**
     * 56. Merge Intervals
     * For example,
     Given [1,3],[2,6],[8,10],[15,18],
     return [1,6],[8,10],[15,18].

                sta     end
     |___|       |____|
       |_____|       |___|

     time : O(nlogn) space : O(n)

     * @param intervals
     * @return
     */
    //输入的形式已经改变 因此这个答案不太对了 但是定义比较器还是有用的
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        Collections.sort(intervals, (a, b) -> a.start - b.start);//按照开始时间进行排序
        int start = intervals.get(0).start;
        int end = intervals.get(0).end; //初始化start指针和end指针
        List<Interval> res = new ArrayList<>();
        for (Interval interval : intervals) {  //只用一个for就可 遍历每个元素
            if (interval.start <= end) { //如果新的start小于当前end指针
                end = Math.max(end, interval.end);//就认为是overlapping,然后直接更新end指针即可
            } else { //否则 开辟一个新的区间
                res.add(new Interval(start, end));
                start = interval.start; //start和end指针重定位
                end = interval.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }
    //上面的思想比自己写的那狗屎代码好多了
}
