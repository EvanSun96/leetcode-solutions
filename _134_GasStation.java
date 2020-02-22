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
public class _134_GasStation {
    /**
     * 134. Gas Station
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

     You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
     You begin the journey with an empty tank at one of the gas stations.

     Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

     非常经典的一道题。可以转换成求最大连续和做，但是有更简单的方法。基于一个数学定理：

     如果一个数组的总和非负，那么一定可以找到一个起始位置，从他开始绕数组一圈，累加和一直都是非负的
     （证明貌似不难，以后有时间再补）


     有了这个定理，判断到底是否存在这样的解非常容易，只需要把全部的油耗情况计算出来看看是否大于等于0即可。

     那么如何求开始位置在哪？

     注意到这样一个现象：

     1. 假如从位置i开始，i+1，i+2...，一路开过来一路油箱都没有空。说明什么？说明从i到i+1，i+2，...肯定是正积累。
     2. 现在突然发现开往位置j时油箱空了。这说明什么？说明从位置i开始没法走完全程(废话)。那么，我们要从位置i+1开始重新尝试吗？不需要！为什么？
     因为前面已经知道，位置i肯定是正积累，那么，如果从位置i+1开始走更加没法走完全程了，因为没有位置i的正积累了。
     同理，也不用从i+2，i+3，...开始尝试。所以我们可以放心地从位置j+1开始尝试。//仔细想一想 还真是这样 这样就会少检查很多地方

     https://www.cnblogs.com/boring09/p/4248482.html

     time : O(n)
     space : O(1)

     * @param gas
     * @param cost
     * @return
     */
    //宏观代码很简单 需要三个辅助变量 然后只需遍历一遍两个数组即可 便利的时候判断并更新最新的start位置即可
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) return -1;
        int total = 0, sum = 0, start = 0;//sum油的总的积累
        for (int i = 0; i < gas.length; i++) { //遍历gas和cost两个数组
            total += (gas[i] - cost[i]); //gas[i] - cost[i] //意思就是说如果在i加满油 到i+1号加油站会剩下多少
            if (sum < 0) { //从某处积累到i-1,积累结果为负
                sum = (gas[i] - cost[i]);//那么就重新开始积累
                start = i;//将start重新定位为i
            } else { //否则接着积累
                sum += (gas[i] - cost[i]);
            }
        }
        //total是游历完一整圈之后 剩的油量（这个油量无论从哪个index转一圈都是一样的） 如果油量为负 那么是怎么样都不可能转一圈的 如果为正 就返回start
        return total < 0 ? -1 : start;
    }
}//我在想 这个start最后停在了某个位置 从这个位置一直到最后的gas station都是正积累的 那么再从最后回来 只要总积累count是正的 就一定能回来。
//可是再一想 既然总积累是正的 那么应该从哪里开始都一样 都能转一圈回来啊？其实不是这样的 因为半路上可能由于没油了抵达不了下一个加油站了
