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
public class _68_TextJustification {
    /**
     * 68. Text Justification
     * Given an array of words and a length L, format the text such that each line has exactly
     * L characters and is fully (left and right) justified.

     You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
     Pad extra spaces ' ' when necessary so that each line has exactly L characters.

     Extra spaces between words should be distributed as evenly as possible. If the number of spaces on
     a line do not divide evenly between words, the empty slots on the left will be assigned more spaces
     than the slots on the right.

     For the last line of text, it should be left justified and no extra space is inserted between words.

     For example,
     words: ["This", "is", "an", "example", "of", "text", "justification."]
     L: 16.

     Return the formatted lines as:
     [
     "This    is    an",
     "example  of text",
     "justification.  "
     ]

     time : O(n)
     space : O(n)

     * @param words
     * @param L
     * @return
     */
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<>();
        //因为变量太多 所以最好搞明白每个变量都是什么用
        int index = 0;//控制遍历到哪个单词对应的Index
        while (index < words.length) {
            int count = words[index].length();//index所对应的单词的长度
            int last = index + 1;//last变量控制当前行的后边界
            while (last < words.length) { //首先要确定结果中每行有多少个单词，我们一行一行确定 确定了本行之后立即构建
                if (words[last].length() + count + 1 > L) break;//当当前的长度+1+之前累计的长度大于maxWidth,就break
                count += 1 + words[last].length();//count负责统计如果每个单词之间只有一个空格 在L的范围内能盛几个单词
                last++;//while循环结束 last指向当前行最后一个单词在words中对应的index
            }
            StringBuilder builder = new StringBuilder();//我们开始构建起这一行
            builder.append(words[index]);//先加上第一个单词 因为第一个单词之前不空格
            int diff = last - index - 1;//表示此行含有的单词的个数
            if (last == words.length || diff == 0) {//如果last指针已经指到了最后 或者当前含有单词数量为0
                for (int i = index + 1; i < last; i++) {//先一个空格 一个单词的append
                    builder.append(" ");
                    builder.append(words[i]);
                }
                for (int i = builder.length(); i < L; i++) {//最后不够L的全部用空格补齐。
                    builder.append(" ");
                }
            } else {//else对一般情况的处理 问题给出的指示是让单词之间的空格尽可能平均，如果平均不了 左边的空格数要比右边的空格数大
                int spaces = (L - count) / diff;//这个spaces变量指的是平均空格数
                int r = (L - count) % diff;//这个r指的是多出来的空格数
                for (int i = index + 1; i < last; i++) {
                    for (int k = spaces; k > 0; k--) {//针对每一个单词 我们都先一视同仁的添加spaces个空格
                        builder.append(" ");
                    }
                    if (r > 0) {//刚开始r>0 所以会多给一个空格 知道最后没有多给空格 都是spaces个空格了
                        builder.append(" ");
                        r--;
                    }
                    builder.append(" ");//由于L-count只是多出来的空格数 因为count考虑了每个单词附赠一个空格 因此我们在构建当前行的时候 还是要在每个单词处附加一个空格
                    builder.append(words[i]);
                }
            }
            res.add(builder.toString());//把stringbUilder转化为String之后 作为结果的一部分添加到res中
            index = last;//index更新
        }
        return res;
    }
}
