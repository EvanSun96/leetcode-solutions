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
public class _117_PopulatingNextRightPointersinEachNodeII {
    /**
     * 117. Populating Next Right Pointers in Each Node II
     * For example,
     Given the following binary tree,
          1
        /  \
       2    3
      / \    \
     4   5    7
     After calling your function, the tree should look like:
          1 -> NULL
        /  \
       2 -> 3 -> NULL
      / \    \
     4-> 5 -> 7 -> NULL

     time : O(n);
     space : O(1);

     * @param root
     */
    //之前一直还不太明白 上一题目中每个答案中的两个if为什么会那样判断 现在想一下完全二叉树和普通二叉树的区别就知道为什么了
    //但是这道题属实不太明白
    public void connect(TreeLinkNode root) {
        TreeLinkNode head = null;
        TreeLinkNode pre = null;
        TreeLinkNode cur = root;
        while (cur != null) {
            while (cur != null) {
                //下面的两个if大概是这个意思：每次从左到右构建链表 那么就从cur的下一行从左到右遍历 哪里有节点就把哪里连上
                //就是说 如果只有左子节点 就只运行第一个if 如果只有右子节点 就运行第二个if,运行任何一个if都会将pre放到合适的位置上
                if (cur.left != null) { //如果cur左子节点不为空
                    if (pre != null) {
                        pre.next = cur.left;//pre就指向cur.left
                    } else head = cur.left; //如果为空 说明这一行才刚刚开始 把head标记为cur.left
                    pre = cur.left;//if cur.left exists, per keeps moving with cur.left
                }
                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    } else head = cur.right;
                    pre = cur.right;//if cur.right exists, per keeps moving with cur.right
                }
                cur = cur.next;//内部循环中cur横移
            }
            cur = head;//外部循环从cur每次向下移动一行
            pre = null;//pre与head置空
            head = null;
        }
    }
}
