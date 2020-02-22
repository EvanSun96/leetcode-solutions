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
public class _31_NextPermutation {
    /**
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     1,2,3 → 1,3,2
     3,2,1 → 1,2,3
     1,1,5 → 1,5,1

     // 1　　2　　7　　4　　3　　1
             ^
     // 1　　2　　7　　4　　3　　1
                          ^
     // 1　　3　　7　　4　　2　　1
             ^            ^
     // 1　　3　　1　　2　　4　　7
                 ^   ^    ^   ^

     7 4 3 2 1 1

     time : O(n);
     space : O(1);
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int firstSmall = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstSmall = i;//从后往前 找第一个下降的数的Index
                break;
            }
        }

        if (firstSmall == -1) {//如果一直是上升
            reverse(nums, 0, nums.length - 1);//直接反转即可
            return;
        }

        //如果firstsmall是其他的数 则开始从后往前找第一个比first small 大的数（这思路跟自己的一摸一样）
        int firstLarge = -1;
        for (int i = nums.length - 1; i > firstSmall; i--) {
            if (nums[i] > nums[firstSmall]) {
                firstLarge = i;
                break;
            }
        }
        swap(nums, firstSmall, firstLarge);//先把firstsmall和first large进行交换
        reverse(nums, firstSmall + 1, nums.length - 1);//然后把firstsmall到最后进行交换 这个思路也是跟自己写的一摸一样。
        return;
    }

    public void swap(int[] nums, int i, int j) {//void可以甚至不用return;
        int temp = nums[i];
        nums[i++] = nums[j];
        nums[j--] = temp;//
    }

    public void reverse(int[] nums, int i, int j) {//利用对称着swap来完成全部数组的reverse
        while (i < j) {
            swap(nums, i++, j--);
        }
    }


}
//自己写的 刚开始没想清楚一些问题 直到提交出来wrong answer意识到一些东西需要修改
class Solution {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length == 0) return;

        //from end to start, find last two elements who are increment.
        for(int end = nums.length - 1;end>0; end--){
            if(nums[end - 1] >= nums[end] ){//if large or equal, just keep going
                continue;
            } else{
                for(int i = nums.length - 1; i >= end; i--){//iterate from last to end pointer, find the first one who is larger than nums[end - 1]
                    if(nums[i] > nums[end - 1]){
                        swap(nums, end - 1, i);//if find, then swap it
                        break;
                    }
                }

                Arrays.sort(nums, end, nums.length);
                return;
            }
        }
        Arrays.sort(nums);
        return;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

        return;
    }
}
