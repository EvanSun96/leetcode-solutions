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
public class _149_MaxPointsonaLine {
    /**
     * 149. Max Points on a Line
     * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

     1，在x轴
     2，相同点
     3，精度问题(GCD) 1/3 1/3

     * time : O(n^2);
     * space : O(n);
     * @param points
     * @return
     */

    //leetcode上提交的代码是完全按照这个答案背下来的
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) return 0;
        if (points.length < 2) return points.length;//如果只有一个点 那么就是这一个点组成的线
        int res = 0;
        for (int i = 0; i < points.length; i++) { //
            HashMap<String, Integer> map = new HashMap<>(); //hashmap应该在此时创建 因为对应一个固定的i 其他的j点共同组成了这个hashmap,换一个i需要重新构建一个hashmap
            int samePoint = 0;
            int sameXAxis = 1;
            for (int j = 0; j < points.length; j++) { //ij指针 每个指针代表一个点
                if (i != j) { //如果ij不是同一个点
                    if (points[i].x == points[j].x && points[i].y == points[j].y) { //如果ij两个点是完全一致的
                        samePoint++;
                    }
                    if (points[i].x == points[j].x) { //如果两个点x坐标一样
                        sameXAxis++; //
                        continue; //可以重新选择一个j点了 因为如果不这样的话的话下面的分母就为0了
                    }
                    int numerator = points[i].y - points[j].y; //分子
                    int denominator = points[i].x - points[j].x; //分母
                    int gcd = gcd(numerator, denominator); //求这两者的最大公约数
                    String hashStr = (numerator / gcd) + "/" + (denominator / gcd); //这个是斜率
                    map.put(hashStr, map.getOrDefault(hashStr, 1) + 1);//如果j满足上述总总要求 那么斜率是各个hashStr的点又多了一个（即如果是新开辟的 就添加2个点 如果不是新开辟的 就在之前的基础上加1）
                    res = Math.max(res, map.get(hashStr) + samePoint);//维护一个以这个i为起始点 j为其他所有点的（包括i点）
                }
            }
            res = Math.max(res, sameXAxis);//检查与i共线但是斜率为无穷的的点的个数
        }
        return res;
    }
    private int gcd(int a, int b) { //求ab的最大公约数 这个小函数一定要记住
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
