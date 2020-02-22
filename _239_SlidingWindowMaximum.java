package leetcode_1To300;

import java.util.Deque;
import java.util.LinkedList;

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
public class _239_SlidingWindowMaximum {
    /**
     * 239. Sliding Window Maximum
     * For example,
     Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

     Window position                Max
     ---------------               -----
     [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
     Therefore, return the max sliding window as [3,3,5,5,6,7].

     Deque : 存的是index 从大到小排序

     time : O(n)
     space : O(n)

     * @param nums
     * @param k
     * @return
     */

    //https://www.youtube.com/watch?v=G70qltIBF40 这个讲的更加清楚
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>(); //why should we use deque? not only we should to manipulate head and tail in many ways
        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k) { //if dequeue is not empty and the length of deque is k without add nums[i]
                deque.poll(); //poll head
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) { //while deque is not empty and the value corresponding to deque is  not descending
                deque.removeLast(); //then we remove tail until deque is decending(for corresponding value)
            }
            deque.offerLast(i); // add i to tail of deque
            if ((i + 1) >= k) { //if i reached to >= k -1, means we can add value to res
                res[i + 1 - k] = nums[deque.peek()];//res index start from 0
            }
        }
        return res;
    }
}
//in general, deque only accepts descending order, if something is ascending, then we remove the tail of deque until the tail of deque is larger than this ascending number
//1 6 -1 -3 5 9
//the deque will be:
// 1
// 6
// 6  -1
// 6 -1 -3 (5)
// 6 -1(5)
// 6 5
//...

//if we have a sliding window, which is descending, then the head of deque must be largest
//we moves forward, if it continous descending, then always the head of dequeue is largest(and the deque stays with length of k)
//but usually, it did not, the next number could be ascending, in order the maintain descending of this deque,
// we hace to pop tail of deque until add this value is descending (now the length of this deque might not k)
//in the mean time, keep in mind that only when i >= k - 1 can we add head vale of deque to res

//my own version is more easy to understand:
//return the maximum value of each sliding window
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i<n; i++) {
            if(!deque.isEmpty() && deque.peekFirst() == i - k) { //why we can't use deque.size() == k? because we continue to add i to last
                //...position of deque. other statement did not control if the scope is larger than k. scope larger than k is not size larger than
                //k, when size is less than k, the scope might be > k due to the while statement. so if we find that, we have to delete that.
                //the reason why use "if" instead of while is because we only add at most one more on deque, so we only need to remove one as well.
                deque.removeFirst();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) { //everytime remind yourself, deque stores index!!! not value!
                deque.removeLast();
            }
            deque.addLast(i);//for each for loop, we add i to last deque no matter what
            if(i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
//if you understand the process, then the code is very clear, pay attention to the details as well
