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
public class _151_ReverseWordsinaString {
    /**
     * 151. Reverse Words in a String
     * For example,
     Given s = "the sky is blue",
     return "blue is sky the".

     * @param s
     * @return
     */

    //从下面给出的几个方法来看 实际上方法太多了
    //time : O(n), space : O(n)
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        String[] words = s.trim().split("\\s+");//匹配任何空格字符（包括制表符 换页符 空格等等 这个可以说是很精确了）
        for (int i = words.length - 1; i >=0; i--) { //
            sb.append(words[i] + " ");
        }
        return sb.toString().trim();//最后stringBuilder别忘了还要toString() 而且最后多一个空格不怕 在这里trim()一下就好了
    }

    //这第二种方法有点罗里吧嗦的
    // time : O(n) space : O(n)
    public String reverseWords2(String s) {
        if (s == null || s.length() == 0) return s;
        char[] ch = s.toCharArray();
        reverse(ch, 0, s.length() - 1);//先将全部的字符都进行翻转 就是整个s被倒序
        reverseWord(ch, s.length());//然后将单词再翻转成正序的
        return cleanSpaces(ch, s.length());//将各种不合规的空格清除（包括前后的空格 单词之间多余的空格）
    }

    private void reverse(char[] ch, int i, int j) {
        while (i < j) {
            char temp = ch[i];
            ch[i++] = ch[j];
            ch[j--] = temp;
        }
    }
    private void reverseWord(char[] ch, int len) {
        int i = 0;
        int j = 0;
        while (i < len) {
            while (i < j || i < len && ch[i] == ' ') i++;
            while (j < i || j < len && ch[j] != ' ') j++;
            reverse(ch, i, j - 1);//用了reverse函数
        }
    }
    private String cleanSpaces(char[] ch, int len) {
        int i = 0;
        int j = 0;
        while (j < len) {
            while (j < len && ch[j] == ' ') j++;
            while (j < len && ch[j] != ' ') ch[i++] = ch[j++];
            while (j < len && ch[j] == ' ') j++;
            if (j < len) ch[i++] = ' ';
        }
        return String.valueOf(ch).substring(0, i);
    }
}

//下面是自己写的代码 很多特殊情况没有考虑到 只是在报错的时候才考虑到
//将一个字符串里的每个单词都倒着重组成另外一个字符串
//感觉还是可以用栈（刚开始是这么想的 但是写的时候发现完全不需要栈）
class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0) return s;
        String res = "";

        s = s.trim();//这个语句最开始并没有考虑到

        String[] list = s.split(" +");//单词之间出现多个空格最开始也没有考虑到 后面才加上+这个正则匹配的

        for(int i = 0; i<list.length; i++){

            res = list[i] + (i==0? "":" ") + res; //最开始最后一个单词后面有空格 属于没想清楚就急急的写了
        }

        return res;
    }
}
