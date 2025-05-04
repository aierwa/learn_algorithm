package node;

import datastructure.Stack;
import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xuxiang
 * 二叉树问题
 */
public class TreeSolution {
    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public int[] layerTraverse(TreeNode root) {
        // 通过队列来辅助，先将节点入队，然后从头出队，出队后有子节点，再入队，依次循环
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> list = new ArrayList<>();
        layer0(list, queue);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 先序遍历
     *
     * @param root
     * @return
     */
    public int[] preTraverse(TreeNode root) {
        // 根左右
        List<Integer> list = new ArrayList<>();
        pre0(list, root);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public int[] middleTraverse(TreeNode root) {
        // 左根右
        List<Integer> list = new ArrayList<>();
        middle0(list, root);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public int[] postTraverse(TreeNode root) {
        // 左右根
        List<Integer> list = new ArrayList<>();
        post0(list, root);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 根据先序和中序构建树
     *
     * @param pre 先序
     * @param mid 中序
     * @return root节点
     */
    public TreeNode buildTreeByPreAndMid(List<Integer> pre, List<Integer> mid) {
        // 树的根节点就是 pre 的第一个数，根据值去 mid 找到对应根节点的位置
        // 这样就找到了左子树和右子树，左子树的根节点链接到上一层根节点的left，同理链接right
        // 所以整个过程就是一个 找根节点+拆分子树 的过程
        if (pre.size() == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre.get(0));
        if (pre.size() == 1) {
            return root;
        }
        for (int i = 0; i < mid.size(); i++) {
            if (mid.get(i) == root.val) {
                // i 就是根节点在 mid 中的位置，即左子树有i个元素
                // 递归
                root.left = buildTreeByPreAndMid(pre.subList(1, i + 1), mid.subList(0, i));
                root.right = buildTreeByPreAndMid(pre.subList(i + 1, pre.size()), mid.subList(i + 1, mid.size()));
            }
        }
        return root;
    }

    /**
     * 找出二叉搜索树中第 k 小的元素
     *
     * @param root 根节点
     * @param k k
     * @return 第 k 小的元素
     */
    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历即为顺序遍历
        // 再利用一个栈结构来保存，
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root=root.left; // 不停地压入左树
            }
            // 左树压完了，说明栈顶是最小的
            root = stack.pop();
            if (--k == 0) {
                // 如果已经到了第k个，则直接返回值
                return root.val;
            }
            // 如果还没有到第 k 个，则把节点的右子树重复压入其中
            root = root.right;
        }
    }

//    public int pathNumberTotal(TreeNode root){
//
//    }

    private void pre0(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        pre0(list, node.left);
        pre0(list, node.right);
    }

    private void middle0(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        middle0(list, node.left);
        list.add(node.val);
        middle0(list, node.right);
    }

    private void post0(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        post0(list, node.left);
        post0(list, node.right);
        list.add(node.val);
    }

    private void layer0(List<Integer> list, Queue<TreeNode> queue) {
        // 取头
        TreeNode head = queue.poll();
        if (head == null) {
            return;
        }
        list.add(head.val);
        if (head.left != null) queue.offer(head.left);
        if (head.right != null) queue.offer(head.right);
        layer0(list, queue);
    }

}
