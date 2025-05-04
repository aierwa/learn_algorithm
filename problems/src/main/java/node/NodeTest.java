package node;

import datastructure.ListNode;
import datastructure.TreeNode;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author xuxiang
 */
public class NodeTest {
    public static void main(String[] args) {
        testLinkedList();
//        testTree();
    }


    public static void testLinkedList() {
        System.out.println("========= test LinkedList start =========");
        LinkedListSolution solution = new LinkedListSolution();

        ListNode list1 = genList(new int[]{1,3,5}); //1-3-5
        ListNode list2 = genList(new int[]{2,3,8});//2-3-8
        System.out.println("list1:" + Arrays.toString(solution.getNodeValues(list1)));
        System.out.println("list2:" + Arrays.toString(solution.getNodeValues(list2)));
        System.out.println("list1 merge list2:" + Arrays.toString(solution.getNodeValues(solution.mergeTwoSortedList(list1, list2))));

        System.out.println("-----");
        ListNode list = genList(new int[]{1,2,3,4,5});
        System.out.println("list:" + Arrays.toString(solution.getNodeValues(list)));
        System.out.println("list reverse split by 2:" + Arrays.toString(solution.getNodeValues(solution.reverseSplit(list, 2))));
        list = genList(new int[]{1,2,3,4,5});
        System.out.println("list reverse split by 3:" + Arrays.toString(solution.getNodeValues(solution.reverseSplit(list, 3))));

        // 链表成环
        ListNode listCycle = genList(new int[]{1,3,5,7});
        ListNode node3 = listCycle.next;
        ListNode node7 = listCycle.next.next.next;
        node7.next = node3;
        NodeProblems nodeProblems = new NodeProblems();
        System.out.println("是否有环：" + nodeProblems.hasCycle(listCycle));
//        System.out.println("成环节点：" + nodeProblems.cycleNode(listCycle).val);
        System.out.println("成环节点：" + nodeProblems.cycleNode2(listCycle).val);

        System.out.println("========= test LinkedList end =========");
    }

    public static void testTree() {
        System.out.println("========= test Tree start =========");
        // 二叉树
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        // 先序 5314869
        // 中序 1345689
        // 后序 1436985
        // 层序 5381469

        TreeSolution treeSolution = new TreeSolution();
        System.out.println("先序：" + Arrays.toString(treeSolution.preTraverse(root)));
        System.out.println("中序：" + Arrays.toString(treeSolution.middleTraverse(root)));
        System.out.println("后序：" + Arrays.toString(treeSolution.postTraverse(root)));
        System.out.println("层序：" + Arrays.toString(treeSolution.layerTraverse(root)));
        // 根据先序和中序构建树
        TreeNode r = treeSolution.buildTreeByPreAndMid(
                Arrays.stream(treeSolution.preTraverse(root)).boxed().collect(Collectors.toList()),
                Arrays.stream(treeSolution.middleTraverse(root)).boxed().collect(Collectors.toList()));
        System.out.println("层序：" + Arrays.toString(treeSolution.layerTraverse(r)));
        System.out.println("第 1 小的数：" + treeSolution.kthSmallest(root, 1));
        System.out.println("第 2 小的数：" + treeSolution.kthSmallest(root, 2));
        System.out.println("第 3 小的数：" + treeSolution.kthSmallest(root, 3));
        System.out.println("========= test Tree end =========");
    }

    public static ListNode genList(int[] arr) {
        // 构造一个链表
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

}
