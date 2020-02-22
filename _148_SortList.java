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
public class _148_SortList {
    /**
     * 148. Sort List
     * Sort a linked list in O(n log n) time using constant space complexity.
     *
     * time : O(nlogn)
     * space : O(n)
     *
     * @param head
     * @return
     */
//仔细看了一下下面代码的架构 就三部分 没啥难的
    public ListNode sortList(ListNode head) { //
        if (head == null || head.next == null) return head;
        //=====divide part======
        ListNode middle = getMiddle(head);//middle是前一半的最后？
        ListNode next = middle.next;
        middle.next = null;

        //=====merge part=======
        return merge(sortList(head), sortList(next));//尾递归 所以只用到线性空间？
    }

    private ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {//注意这儿换了 fast提前一个停止 因此slow也提前一个停止
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;//返回的是前一半的最后
    }
    private ListNode merge(ListNode a, ListNode b) { //merge
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        if (a == null) cur.next = b;
        else cur.next = a;
        return dummy.next;
    }
}
