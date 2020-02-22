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
public class _19_RemoveNthNodeFromEndofList {
    /**
     *  Given linked list: 1->2->3->4->5, and n = 2.

     After removing the second node from the end, the linked list becomes 1->2->3->5.

     time : O(n)
     space : O(1)

     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {//尤其注意在链表上用双指针应该怎么用
        ListNode dummy = new ListNode(0);//如果设了dummy node,就完全不用考虑删除的是头节点这个特殊情况
        ListNode slow = dummy;//注意看指针是如何连到一个节点上的
        ListNode fast = dummy;//slow应该是要被删除的那个节点的前一个结点
        dummy.next = head;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;}//因为初始是在dummy 因此fast要走n+1步 slow和fast之间要相隔n个 这样当fase到null的时候 slow到了要删除的那个节点的前一个节点
        while (fast != null) {
            fast = fast.next;//注意看指针是如何移动的
            slow = slow.next;
        }
        slow.next = slow.next.next;//只有这一句话是核心的删除操作
        return dummy.next;
    }
}
//虽然很绕 但是跑例子的话肯定是对的
//注意 在这种情况下 head是存有数据的 不然他不会让你删除一个没存数据的头的 不要钻牛角尖。
