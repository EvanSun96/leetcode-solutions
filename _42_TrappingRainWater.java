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
public class _42_TrappingRainWater {
    /**
     *

     0,1,0,2,1,0,1,3,2,1,2,1

                   *
           *       * *   *
       *   * *   * * * * * *
     0 1 2 3 4 5 6 7 8 9 0 1
                 l r

     time : O(n)
     space : O(1)

     *
     * @param height
     * @return
     */
    //注意 本题是求总的接水的体积
    //刚开始毫无思路是因为理解题意理解成了能盛水最大的坑了
    //而且总是想着要从某个地方想两边拓展（因为之前对答案有一些印象 知道是双指针 所以陷入了思维定势。）
    public int trap(int[] height) {
        int left = 0; //left指针和right指针的作用是标识 如果所有的坑都盛满，当前位置处的水的体积
        int right = height.length - 1;
        int res = 0;
        int leftMax = 0;//不是位置 只是初始化为value=0
        int rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) { //谁小就以谁那边的Max作为基准 即如果左边的小 那么左指针处盛的最大水量就只与leftMax有关 与left右边的任何一个墙的高度无关，即墙如果低，left指针上最终也会是这个高度，墙如果高 left指针最终也会是这样一个高度
               leftMax = Math.max(height[left], leftMax); //当leftMax有必要更新的时候再更新
                res += leftMax - height[left]; //隐形乘以了1
                left++; //左指针右移一位
            } else {
                rightMax = Math.max(height[right], rightMax);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}

//自己写的代码如下 思路参照
//youtube.com/watch?v=8BHqSdwyODs
//讲的真的是太他娘的好了
class Solution {
    public int trap(int[] height) {

        int res = 0;

        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int leftMax = 0;
        int rightMax = 0;
        //int[] water = new int[height.length];

        for (int i = 0; i<height.length;i++){
            leftMax = Math.max(height[i], leftMax);
            left[i] = leftMax;
        }
        for (int j = height.length - 1; j >= 0; j--){
            rightMax = Math.max(height[j], rightMax);
            right[j] = rightMax;
        }
        for(int k = 0; k<height.length;k++){
            int temp = Math.min(left[k], right[k]);
            res += temp - height[k];
        }
        return res;
    }
}
