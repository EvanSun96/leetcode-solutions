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
public class _234_PalindromeLinkedList {
    /**
     * 234. Palindrome Linked List

     * time : O(n)
     * space : O(1)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode middle = findMiddle(head);
        middle.next = reverse(middle.next);//reversed from middle.next is the right choice

        ListNode p = head;
        ListNode q = middle.next;//point to reverse starting position
        while (p != null && q != null) {
            if (p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; //if the initial position of fast is head.next, then slow will stopped at the posistion of left half or middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null; //
        while (head != null) {
            ListNode temp = head.next;//preserve all nodes behind head
            head.next = prev;//move head to the end
            prev = head;//updated reverse part by reassign prev with head
            head = temp;//reassign head with preserved linkedlist
        }
        return prev;//after while is done, prev pointer is moved to the head of total reversed linkedlist
    }
}
