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
public class _2_AddTwoNumbers {
    /**

     Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     Output: 7 -> 0 -> 8

     time : O(n)
     space : O(n)

     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {//本题是输入两个链表 输出一个链表
        ListNode dummy = new ListNode(0);//输出的链表的假头，假头的数值不重要 但是必须要有 因为构造要求
        ListNode cur = dummy;//链表的遍历是通过指针来完成
        ListNode p1 = l1, p2 = l2;//链表的遍历是通过指针来完成 每次要遍历链表的时候要做这个准备工作 这三个指针只要指到头上即可
        //本题的特殊情况没有明确说明 特殊情况是空链表 但是由于是两个输入 所以就有了三种特殊情况，而下面的while语句则是取了反
        //也就是根本没管 如果两者都是空 那是不可能的 因为题目第一句话就说两个非空（是不是根本不用考虑存在空的情况 但是下面代码明显考虑了这一情况）
        int sum = 0;

        while (p1 != null || p2 != null) {
            if (p1 != null) {//
                sum += p1.val;
                p1 = p1.next;//这是链表指针移动方式
            }
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }
            cur.next = new ListNode(sum % 10);//这是链表的增长方式 四则运算中如果从个位开始加 那么sum取模就是当前位置上应该保留的数字 而sum/=10就是应该进的数字 保留sum进行下一轮循环
            sum /= 10;
            cur = cur.next;//
        }//这个while简直就是最美的交响乐
        if (sum == 1) {//最高位不进位的话sum最后是0
            cur.next = new ListNode(1);//那么就在cur后面加上一个节点就行了
        }
        return dummy.next;//最后返回的值注意一下
    }
}
