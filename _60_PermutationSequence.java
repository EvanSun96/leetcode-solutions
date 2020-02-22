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
public class _60_PermutationSequence {
    /**
     * 60. Permutation Sequence
     * The set [1,2,3,…,n] contains a total of n! unique permutations.

     By listing and labeling all of the permutations in order,
     We get the following sequence (ie, for n = 3):

     "123"
     "132"
     "213"
     "231"
     "312"
     "321"
     Given n and k, return the kth permutation sequence.

     Note: Given n will be between 1 and 9 inclusive.

     1， 2， 3， 4:

     1 + {2, 3, 4}
     2 + {1, 3, 4}
     3 + {1, 2, 4}
     4 + {1, 2, 3}

     18 : 3421

     res  :
     fact : 1 1 2 6

     k = 17

     i = 4    index = 17 / 6 = 2 k = 17 % 6 = 5
     i = 3    index = 5 / 2 = 2 k = 5 % 2 = 1
     i = 2    index = 1 / 1 = 1 k = 1 % 1 = 0

     4 3 2 1
     3 4 2 1

     time : O(n^2)
     space : O(n)

     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            res.add(i);//构建其一个从元素为1~n的arrayList
        }
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = i * fact[i - 1];//用fact数组去记录当n为i的时候 有多少个排列
        }
        k = k - 1; //why do we have to make k = k-1??
        StringBuilder sb = new StringBuilder(); //
        for (int i = n; i > 0; i--) {
            int index = k / fact[i - 1]; //
            k = k % fact[i - 1]; // this k is what we gonna
            sb.append(res.get(index));//we have to sb.append(res.get(index)) instead of sb.append(index). think carefully about it
            res.remove(index);//we remove index as a real index, think carefully
        }
        return sb.toString();
    }
}
//the first step of for loop, is trying to find the num in first posistion, which is res.get(index)
//k will be updated to k%fact[i-1], because the next round, we will need to find the k-fact[i-1] th permutation of
//the remaining res, which is res.remove(index) from last step. why we need to remove that?
// because after we use that num, we can't use that anymore, and after we remove that, the res still in a ascending order


//so the general view of this code will be:
//construct an arraylist res contains 1~n.
//constract an array which fact[i] indicates the total permutation number contains of 1~i
//iterate from n to 1, find the number we should use in this round(and append to our results as a part, and remove this index from res to avoid replica use) and update the k we need to use in the next round.
