package leetcode_1To300;

import java.util.PriorityQueue;

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
public class _295_FindMedianfromDataStream {
    /**
     * 295. Find Median from Data Stream
     * For example:

     addNum(1)
     addNum(2)
     findMedian() -> 1.5
     addNum(3)
     findMedian() -> 2

     1 2 -3 4
     -3 1 2 4

     small : 3 -1
     large : 2 4

     time : O(logn)
     space : O(n)
     */

    //PQ is implemented in heap in java source code, so its add and poll will cost logn
    private PriorityQueue<Long> small;
    private PriorityQueue<Long> large;

    public _295_FindMedianfromDataStream() {  // MedianFinder()
        small = new PriorityQueue<>();
        large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        large.add((long)num); //this (long) is not necessary
        small.add(-large.poll());
        if (large.size() < small.size()) {
            large.add(-small.poll());
        }
    }

    public double findMedian() {
        return large.size() > small.size() ? large.peek() : (large.peek() - small.peek()) / 2.0;
    }
}

//a detailed comments solution, small is the heap with smaller element, but it's a maxheap. large is the heap with larger elements, but it's a minheap
public class _295_FindMedianFromDataStream {

    private PriorityQueue<Integer> small;
    private PriorityQueue<Integer> large;//维护两个优先级队列 小的每次取最小值 大的每次取最大值 每次成对拿出来一个最大最小的 最后一定是median

    public _295_FindMedianFromDataStream() {
        this.small = new PriorityQueue<>((a, b) -> (b - a));//从大到小排 大的优先级高 //最大的放在root
        this.large = new PriorityQueue<>();//从小到大进行排序 小的优先级高 最小的放在root 所以large实际上是最小堆，但是是大的那一堆
    }

    public void addNum(int num) {//add数的过程实际上是一个维护两个PQ的过程
        large.offer(num);//large先存了这个num 表明这是个larger one，但是也因此挤掉了large中的一个名额
        small.offer(large.poll());//所以要把large中最小的（即root）给放入small中
        if (large.size() < small.size()) {//这个if语句使得最终large中的元素数量不可能比small少 因为如果少了 那么small中root就会立刻被晋升为large那一堆
            large.offer(small.poll());//
        }
    }

    public double findMedian() {
        return large.size() > small.size() ? large.peek() : (large.peek() + small.peek()) / 2.0;//if large has a larger size than small, then total number of data is odd, so we just need to find the root of larger. or total number of data is even, which we have to calculate the average of two roots.
    }//pay attention! we have to use peek instead of in this method, because it;s a data straucture design problem, we don't just call findMedian once!
    //and we have to devide by 2.0 instead of 2! or there will be lose in precision!
}

