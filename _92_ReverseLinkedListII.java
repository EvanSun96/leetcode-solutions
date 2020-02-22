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
public class _92_ReverseLinkedListII {
    /**
     * 92. Reverse Linked List II
     * For example:
     Given 1->2->3->4->5->NULL, m = 2 and n = 4,

     return 1->4->3->2->5->NULL.

     1->2->3->4->5
     p  c  t


     time : O(n);
     space : O(1);

     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        for (int i = 1; i < m; i++) { //走了m-1步
            cur = cur.next;//cur走到了m前面
            pre = pre.next;//pre走到了m处
        }
        for (int i = 0; i < n - m; i++) { //走n-m步
            ListNode temp = cur.next;//用一个临时指针temp 和两个指针完成这种翻转 三个指针的顺序为pre cur temp,一次loop之后的顺序为pre temp cur
            cur.next = temp.next;//其实在整个过程中 pre的位置一直没有在变 而是cur的位置一直在变，temp只是个临时变量 只要在最开始的时候放在cur后面即可
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }
}
