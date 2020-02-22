package leetcode_1To300;

import java.util.PriorityQueue;

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
public class _23_MergekSortedLists {
    /**
     *
     time : O(nlogk) where k is the number of linked lists
     space : O(n)


     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {  //这种代码一看就知道怎么回事 但是永远不可能写出来
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    public ListNode sort(ListNode[] lists, int lo, int hi) { //将lists二分 平行双递归
        if (lo >= hi) return lists[lo];
        int mid = (hi - lo) / 2 + lo;
        ListNode l1 = sort(lists, lo, mid);//不要想太深 想象一下l1是sort完左半部分
        ListNode l2 = sort(lists, mid + 1, hi);//l2是sort完右半部分
        return merge(l1, l2);//然后将之merge在一起 返回给mergeKLists function
    }

    public ListNode merge(ListNode l1, ListNode l2) { // merge的时候是有选择性的 因此选择双递归（实际上是单递归 因为递归存在于if else语句中）
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {//跟LC22一样 也是难以理解的选择双递归，所不同的是这是个有返回的 而上一题是没有返回的
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }


    public ListNode mergeKLists2(ListNode[] lists) {//用PQ 这个方法真的是天秀
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);//新建固定长度 固定比较方式的PQ
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);//将lists所有元素都添加到PQ
            }
        }
        while (!queue.isEmpty()) {//这一段代码可真是太秀了
            cur.next = queue.poll();//poll出来
            cur = cur.next;//就添加了一个
            if (cur.next != null) {
                queue.add(cur.next);//把剩下的放回去
            }
        }
        return dummy.next;
    }
}
//自己写的代码 通过了 但是非常的屎 自己心里也清楚 用递归去写更好 但是选择了迭代
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {//用递归去做更好
        if(lists == null || lists.length == 0 ) return null;
        if(lists.length == 1) return lists[0];

        ListNode dummy = lists[0];//想一想应该什么时候去创造一个dummy node,这里的dummy不能叫dummy 因为他根本不是个假头 而是迭代第一步的初始化
        //dummy.next = lists[0];
        int k = lists.length;

        for(int i = 1; i < k; i++){
            dummy = mergeTwoLists(dummy, lists[i]);//因为我们每次MergetwoList出来的是一个干干净净的链表 所以根本就不需要对输出做任何处理 直接更新即可
        }
        return dummy;//最后也是 直接输出dummy即可 完全不需要dummy.next.
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null || l2 == null) return l1==null? l2:l1;

        ListNode temp_dummy = new ListNode(0);
        ListNode dummy_pointer = temp_dummy;

        while(l1 != null && l2 != null) {
            if(l1.val > l2.val){
                dummy_pointer.next = new ListNode(l2.val);
                dummy_pointer = dummy_pointer.next;
                l2 = l2.next;
            } else {
                dummy_pointer.next = new ListNode(l1.val);
                dummy_pointer = dummy_pointer.next;
                l1 = l1.next;
            }
        }

        if(l1 == null) {
            dummy_pointer.next = l2;
        } else {
            dummy_pointer.next = l1;
        }

        return temp_dummy.next;
    }
}//写完之后 更宏观的来看 这只是个merge sort的merge阶段 只是用链表表示数组而已
