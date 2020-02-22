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
public class _24_SwapNodesinPairs {
    /**
     * Given a linked list, swap every two adjacent nodes and return its head.

     For example,
     Given 1->2->3->4, you should return the list as 2->1->4->3.


     time : O(n);
     space : O(1);
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode l1 = dummy;
        ListNode l2 = head;
        while (l2 != null && l2.next != null) {
            ListNode nextStart = l2.next.next;
            l1.next = l2.next;
            l2.next.next = l2;
            l2.next = nextStart;
            l1 = l2;
            l2 = l2.next;
        }
        return dummy.next;
    }
}
//自己写的代码 通过了 但是在while的判断语句那边卡了很久
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode left = dummy;
        ListNode right = head;

        while(right != null && right.next != null){//这面的结束很tricky,每次循环结束 right都是指向最新的位置 为了保证下一轮运转整个过程中的合法性，我们注意观察：我们swap的必然是两个值节点 因此right.next!=null 而且最后停的位置是null,表示结束了即right != null.这两个条件缺一不可
            left.next = right.next;//由于链表的不可逆性 因此我们改变指向的时候一定要按结点的顺序走
            right.next = right.next.next;
            left.next.next = right;

            left = left.next.next;//可以试着把这两句提到前面三句前面，这样while的判断条件又要发生变化
            right = right.next;
        }
        return dummy.next;
    }
}
//right != null不一定right.next!=null
//right.next != null 一定可以保证right != null
//while里面的东西是要检视上一轮的结束是否越界和下一轮里 是否会出现不合法现象
//但是还是很难判断
