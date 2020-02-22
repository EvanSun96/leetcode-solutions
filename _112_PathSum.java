package leetcode_1To300;

import java.util.Stack;

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
public class _112_PathSum {
    /**
     * 112. Path Sum
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

     For example:
     Given the below binary tree and sum = 22,
           5
          / \
         4   8
        /   / \
       11  13  4
      /  \      \
     7    2      1
     return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

     time : O(n);
     space : O(n);
     * @param root
     * @param sum
     * @return
     */
    //递归版本
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {//如果root是叶子节点
            return sum == root.val; //check if 剩下的sum是不是等于此叶子节点
        }//如果root只有一个子节点  那么他就不会执行上述两个if 而是在下一轮执行第一个if和第二个if(因为是双递归嘛) 因为我们是要一条到叶节点的路径 如果到倒数第二层下面就没有了 肯定不能称作一条路径 所以必然返回false
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);//双尾递归 并且以或连接 只要有一个为true就为true
        //注意 好像只有boolean类型的函数尾递归的才会返回 || 或者 && 连接的双递归，而且||用在只要找到一个解就行 而&&要求所有都满足要求才能 一个false就会毁掉全部
    }

    // 宏观的来说 第二种方法 改变了每个节点的值 每个结点的val不再只是自己的值 而是从root到此节点的路径权重之和
    //宏观的来说 用stack来实现
    //还是外层一个while 用于控制stack是否为空
    //内层三个if 第一个if用于判断当前节点是否为叶节点 如果是 再判断当前节点的值是否等于sum
    //其他两个if分别用于判断cur的左右节点是否为空 不为空的话就压入栈中 并且更新其左右节点的节点值
    public static boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<TreeNode> stack = new Stack<>();//DFS必然要用stack
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {// if we reach leaf node
                if (cur.val == sum) { //and the value of leaf node is remaining sum
                    return true; //then it's true
                }
            }
            //为什么下面先push进右 后push进左？因为后Push会先pop.可是实际上把下面两个if的顺序反过来也是对的
            if (cur.right != null) { //if current node's right is not null
                stack.push(cur.right);//then we can push right node in stack
                cur.right.val += cur.val;// and update its val
            }
            if (cur.left != null) { //
                stack.push(cur.left);
                cur.left.val += cur.val;
            }
        }
        return false;//if every path did not return true, then it must be false(means have no right path)
    }
}
