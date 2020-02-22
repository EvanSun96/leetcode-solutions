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
public class _126_WordLadderII {
    /**
     * 126. Word Ladder II
     * Given two words (beginWord and endWord), and a dictionary's word list,
     * find all shortest transformation sequence(s) from beginWord to endWord, such that:

     Only one letter can be changed at a time
     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     For example,

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]

     Return
     [
     ["hit","hot","dot","dog","cog"],
     ["hit","hot","lot","log","cog"]
     ]

     BFS + DFS

     无向图 -> BFS -> 树 -> DFS -> 结果

     hit -> hot -> dot -> dog - cog
                -> lot -> log - cog

     //只改变一个字符 那么每个只差一个字符的单词会组成一个无向图，我们对这个无向图做BFS
     map : hot (hit)
           dot (hot)
           lot (hot)
           dog (dot)
           log (lot)
           cog (dog,log)

     time : O(V + E) * wordList(max(length))  不确定
            O(n ^ 2)
     space : O(n)

     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */

    //use DFS

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList.size() == 0) return res;

        int curNum = 1;
        int nextNum = 0;
        boolean found = false;

        Queue<String> queue = new LinkedList<>(); //use queue for BFS
        HashSet<String> unvisited = new HashSet<>(wordList); // use two HashSet
        HashSet<String> visited = new HashSet<>();

        HashMap<String, List<String>> map = new HashMap<>(); //use a hashmap, where key is string and value is List<String> which represents
        //the word and all the words that is one step far from

        queue.offer(beginWord);
        while (!queue.isEmpty()) { //everything in this while are for one goal: build the hashMap which can represents the graph
            String word = queue.poll();
            curNum--;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder builder = new StringBuilder(word); //use stringbuilder
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    builder.setCharAt(i, ch); //change one char in word
                    String newWord = builder.toString(); //get the new word
                    if (unvisited.contains(newWord)) { //if unvisited contains this new word
                        if (newWord.equals(endWord)) { //if we reach the end
                            found = true; //then we set found as true
                        } //but this time, we are not rush to return
                        if (!visited.contains(newWord)) { //this statement is to check if we have passed newWord or not, if it didn't,
                            visited.add(newWord);//we add to visisted
                            nextNum++; //and update nextNum
                            queue.offer(newWord); //offer queue a new element
                        }
                        if (map.containsKey(newWord)) { //if map contains this newword key
                            map.get(newWord).add(word); //add current word to this newword
                        } else { //if didn't contain
                            List<String> list = new ArrayList<>();
                            list.add(word);
                            map.put(newWord, list); //add a new k-v pair in map
                        } //this if else is to construct HashMap

                    }
                }
            }
            if (curNum == 0) {
                if (found) break; //if we found it, break the whole while
                curNum = nextNum;
                nextNum = 0;
                unvisited.removeAll(visited); //remove all visited from unvisited
                visited.clear(); //and clear visited
            }
        }
        dfs(res, new ArrayList<>(), map, endWord, beginWord); // res is our final results, arraylist is the element of res, map is the hashmap we build, and endword and begin word
        return res;
    }

    private void dfs(List<List<String>> res, List<String> list, HashMap<String, List<String>> map, String word, String start) { //dfs from end to start, so that we don have to change start in this recursion function
        if (word.equals(start)) { //if word reaches start word
            list.add(0, start); //list will add start at it head
            res.add(new ArrayList<>(list));//and res will add list in it
            // list.remove(list.size - 1)
            list.remove(0); //list will remove the head of list
            return;// and return
        }

        list.add(0, word); //add word to head of list
        if (map.get(word) != null) {
            for (String s : map.get(word)) {
                dfs(res, list, map, s, start);
            }
        }
        list.remove(0);//remove the head
    }
}
