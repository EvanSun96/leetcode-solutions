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
public class _118_PascalTriangle {
    /**
     * 118. Pascal's _120_Triangle
     * For example, given numRows = 5,
     Return

     [
         [1],
        [1,1],
       [1,2,1],
      [1,3,3,1],
     [1,4,6,4,1]
     ]


     time : O(n^2)
     space : O(n)

     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(0, 1);//每次再每个List头部都加上1 注意List每次不断被重写
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));//注意set在这里用的是对的
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }//感觉不太对 但是跑出来是对的
}

//下面是自己写的 虽然非常low还是还是写出来了
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();


        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            if(i == 0){
                res.add(new ArrayList<>());
                res.get(0).add(1);
                continue;
            }
            if(i == 1){
                res.add(new ArrayList<>());
                res.get(1).add(1);
                res.get(1).add(1);
                continue;
            }
            res.add(new ArrayList<>());
            for(int j = 0; j <= i; j++ ){

                if(j == 0 || j == i){
                    res.get(i).add(j,1);
                } else{
                    res.get(i).add(j,res.get(i-1).get(j-1) + res.get(i-1).get(j));
                }
            }
        }
        return res;
    }
}
