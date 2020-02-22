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
public class _109_ConvertSortedListtoBinarySearchTree {

    /**
     * 109. Convert Sorted List to Binary Search Tree

     time : O(nlogn);
     space : O(n);

     * @param head
     * @return
     */

    //the idea is the same as LC108
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head, null); //using the helper() function using head and tail as new parameter, and we set tail as null
    }

    public TreeNode toBST(ListNode head, ListNode tail) { //
        //pre order process as follows, which is find the mid of head-tail linkedlist
        if (head == tail) return null; //if head pointer meet the tail pointer, then return null. not that head == null return null
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) { //pay special attention on this! we can't write fast!= null && fase.next != null, instead, we use tail to replace null
            fast = fast.next.next;
            slow = slow.next;
        } //slow will be stopped at the position of right middle

        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head, slow);
        root.right = toBST(slow.next, tail);
        return root;
    }
}
