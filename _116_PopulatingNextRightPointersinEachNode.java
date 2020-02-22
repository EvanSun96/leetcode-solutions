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
public class _116_PopulatingNextRightPointersinEachNode {
    /**
     * 116. Populating Next Right Pointers in Each Node
     * For example,
     Given the following perfect binary tree,
          1
        /  \
       2    3
      / \  / \
     4  5  6  7
     After calling your function, the tree should look like:
          1 -> NULL
        /  \
       2 -> 3 -> NULL
      / \  / \
     4->5->6->7 -> NULL

     time : O(n);
     space : O(n);

     * @param root
     */
    //还是那句话 递归每一层其实就把他想象成一个完整的解决问题的流程
    public void connect(TreeLinkNode root) { //总体思想 前序遍历？层次遍历？
        if (root == null) return;
        if (root.left != null) {//当root非叶节点（左子节点非空）[如果左子节点非空 那么说明这个root有子节点 那么无论right是否存在 左子节点都会连上]
            root.left.next = root.right;//将左子节点连接到右子节点
        }//想执行上面语句 root.left不为空 root不为空（这是肯定的）
        if (root.next != null && root.right != null) { //当root非最靠右节点 并且 root非叶节点（右子节点非空）【如果右子节点非空 他才会连接到下一个的左子节点】
            root.right.next = root.next.left; //将当前root的右子节点指向 当前root指向的节点的左子节点
        }//想执行上面的语句 root.right不为空，root.next不为空 root不为空（这是肯定的）
        connect(root.left);
        connect(root.right);
    }
    //所以上面的代码不是按顺序写的 而是：
    if(root == null) return;
    root.left.next = root.right;//内部连
    root.right.next = root.next.left;//外部连
    connect(root.left);
    connect(root.right);
    //然后发现里面的那两个语句需要很多东西不为null 因此才添加的那些if

    //space : O(1)
    //下面解决方法是用start和cur组成的滑窗 start从root开始起步 cur从start开始起步
    //双while结构 外层用start != null 控制 start每次往左移动
    //内层用 cur != null 控制 cur每次往下一个链表节点移动
    //cur就跟第一个方法中的root一样 判断其位置 然后做出相应的链接
    public void connect2(TreeLinkNode root) {
        TreeLinkNode start = root;//
        while (start != null) {
            TreeLinkNode cur = start;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            start = start.left;
        }
    }
}
