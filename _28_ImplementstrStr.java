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
public class _28_ImplementstrStr {
    /**
     * time : O(m * n)
     * space : O(1)
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        for (int i = 0; i < haystack.length(); i++) {
             if (i + needle.length() > haystack.length()) {
                 break;
             }
            for (int j = 0; j < needle.length(); j++) {
                 if (haystack.charAt(i + j) != needle.charAt(j)) {
                     break;
                 }
                 if (j == needle.length() - 1) {
                     return i;
                 }
            }
        }

        return -1;
    }
}

//自己写的代码
class Solution {
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0) return 0;
        if(haystack == null || haystack.length() == 0) return -1;//第一遍提交的时候是错误的 错误在于应该先判断needle再判断haystack,因为needle是要求当其为空 无论haystack怎么样 都返回0，这种细节一定要注意。


        //boolean flag = false;
        int res = 0;

        for(int left = 0; left < haystack.length() - needle .length() + 1; left++ ){
            int right = left + needle.length();
            if(haystack.substring(left,right).equals(needle)) {
                return left;
            }
        }
        return -1;

    }
}
