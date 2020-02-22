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
public class _204_CountPrimes {
    /**
     * 204. Count Primes
     * 厄拉多塞筛法，求一组质数，时间复杂度仅有O(nloglogn)
     * 如果从1到n-1分别判断质数，时间复杂度为O(nsqrt(n))）
     * 1   2  3  4  5  6  7  8  9 10
     * 11 12 13 14 15 16 17 18 18 20
     *
     * @param n
     * @return
     */
    //暴力解这个问题的话就从1到n-1挨个判断一遍是否是素数 判断i是不是素数只要检验其不能被2到sqrt(i)整除即可
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];//空间换时间的一个典型
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) { //当此位上是false时才执行
                res++;//当为false 即是素数的时候才会统计
                for (int j = 2; i * j < n; j++) { //利用i指针作为基数来统计其倍数（即其后面的那些成倍数的位置）
                    notPrime[i * j] = true;//true代表此位不是素数
                } //在这整个过程中 素数的倍数被一个一个的踢掉了 想一想i=2,3,4,5序列就明白了
            }
        }
        return res;
    }
}
