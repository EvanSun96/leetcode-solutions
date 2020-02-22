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
public class _21_MergeTwoSortedLists {
    /**
     * Merge two sorted linked lists and return it as a new list. The new list should be made
     * by splicing together the nodes of the first two lists.

     time : O(n);
     space : O(n);
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;//这样l1实际上就是“指针”了 用完一个节点舍弃一个节点
            } else {
                cur.next = new ListNode(l2.val);
                l2= l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }
        return dummy.next;
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {//这个递归的方法猛啊 非常牛逼
        if (l1 == null) return l2;//l1先到结尾 return l2
        if (l2 == null) return l1;//反之l2先到结尾 return l1
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);//相当于留下l1 l1后面跟的就是要继续比较的
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }//想一想极端情况 如果此过程中l1的值一直比l2小 那么就一直执行if 知道l1==null returnl2 将l2添加到L1.next, 然后层层回溯 所以到最后就是返回的是整个l1后面跟着l2

    //自己写的方法
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1 == null || l2==null) return l1 == null ? l2:l1;

            ListNode dummy = new ListNode(0);
            ListNode dummy_pointer = dummy;
            ListNode l1_pointer = l1;//因为我们其实并不在意l1和l2被遍历完之后是不是还是完整的 因此其实这两个pointer就是完全没必要的
            ListNode l2_pointer = l2;

            while(l1_pointer != null && l2_pointer != null) {//when l1 and l2 are not the last nodes of each linkedlist, until one of them is
                if(l1_pointer.val < l2_pointer.val) {
                    dummy_pointer.next = new ListNode(l1_pointer.val);
                    dummy_pointer = dummy_pointer.next;
                    l1_pointer = l1_pointer.next;//the location of l1 is the node needs to be added in next round
                } else {
                    dummy_pointer.next = new ListNode(l2_pointer.val);
                    dummy_pointer = dummy_pointer.next;
                    l2_pointer = l2_pointer.next;//the location of l2 is the node needs to be added in next round
                }
            }

            if(l1_pointer == null) {
                dummy_pointer.next = l2_pointer;
            } else {
                dummy_pointer.next = l1_pointer;
            }
            return dummy.next;

        }
    }
}
