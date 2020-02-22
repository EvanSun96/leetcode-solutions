package leetcode_1To300;

import java.util.*;

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
public class _127_WordLadder {
    /**
     * 127. Word Ladder
     * For example,

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]
     As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     return its length 5.


     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]
     As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",

     time : O(n)
     time : O(n)

     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    //another problem is how can we so sure that we can get the shortest path?
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) { //use set and queue
        HashSet<String> set = new HashSet<>(wordList);//将字典转为hashset
        if (set.contains(beginWord)) { //if this set contains beginword, then remove it from the set
            set.remove(beginWord);
        }
        Queue<String> queue = new LinkedList<>();
        int level = 1; //负责控制路径长度
        int curNum = 1;
        int nextNum = 0;
        queue.offer(beginWord);
        while (!queue.isEmpty()) { //
            String word = queue.poll();
            curNum--;
            for (int i = 0; i < word.length(); i++) { //把这个单词拆成字符数组
                char[] wordUnit = word.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) { //两个for循环 把wordUnit这个数组每一个元素都从a到z替换一下
                    wordUnit[i] = j; //
                    String temp = new String(wordUnit); //把替换过后的字符数组变为字符串
                    if (set.contains(temp)) { //如果set中包含这个字符串
                        if (temp.equals(endWord)) { //如果temp等于endWord
                            return level + 1;//说明找到路径 直接返回其长度即可
                        }
                        nextNum++; //
                        queue.offer(temp);//将temp添加到queue里面
                        set.remove(temp);//从set中remove掉这个单词
                    }
                }
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) { //use HashSet, HashMap, Queue
        HashSet<String> set = new HashSet<>(wordList);
        if (set.contains(beginWord)) {
            set.remove(beginWord);
        }
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int curLevel = map.get(word);
            for (int i = 0; i < word.length(); i++) {
                char[] wordUnit = word.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    wordUnit[i] = j;
                    String temp = new String(wordUnit);
                    if (set.contains(temp)) {
                        if (temp.equals(endWord)) {
                            return curLevel + 1;
                        }
                        map.put(temp, curLevel + 1);
                        queue.offer(temp);
                        set.remove(temp);
                    }
                }
            }
        }
        return 0;
    }
}
