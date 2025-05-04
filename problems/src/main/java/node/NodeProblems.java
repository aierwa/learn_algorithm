package node;

import datastructure.ListNode;

/**
 * @author xuxiang
 * 节点问题，如链表
 * reverseNode：反转链表
 * hasCycle：链表是否成环
 */
public class NodeProblems {
    public static void main(String[] args) {
        NodeProblems nodeProblems = new NodeProblems();
        ListNode a = new ListNode(0);
        ListNode head = a;
        for (int i = 0; i < 5; i++) {
            a.next = new ListNode(i + 1);
            a = a.next;
        }
        nodeProblems.printList(head);
//        nodeProblems.printList(nodeProblems.reverseNode(head));
        nodeProblems.printList(nodeProblems.reverseNode2(head));

    }

    /**
     * 反转链表，并返回反转后的表头
     *
     * @param head 当前链表的表头
     * @return 反转后的表头
     */
    public ListNode reverseNode(ListNode head) {
        // 思路：当前 node
        // 首先临时保存它的next节点（nxt），然后把它的next指向上一个node（pre），然后把上一个节点应用到当前节点，最后再把当前节点引用到 nxt ，开启下一次判断
        // 直到当前节点为 null，说明遍历到了尾部了，最后一个就是 pre 节点
        ListNode cur = head;
        ListNode nxt;
        ListNode pre = null;

        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    public ListNode reverseNode2(ListNode head) {
        if (head == null) return null;
        ListNode pre = null, n;
        while ((n = head.next) != null) {
            head.next = pre;
            pre = head;
            head = n;
        }
        head.next = pre;
        return head;
    }


    /**
     * 链表是否成环
     * 只要有一个元素的 next 为 null，那么就不是成环的。
     * 使用快慢指针的办法，一个节点走1步，一个走2步，如果成环，那么走两步的始终会追上走一步的，并且两者总有一次会相等
     *
     * @param head 头
     * @return bool
     */
    public boolean hasCycle(ListNode head) {
        ListNode node1 = head, node2 = head;
        while (node1 != null && node1.next != null) {
            node1 = node1.next.next;
            node2 = node2.next;
            if (node1 == node2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 成环节点
     * 此方法会修改链表！（过河拆桥）
     *
     * @param head
     * @return
     */
    public ListNode cycleNode(ListNode head) {
        if (!hasCycle(head)) {
            return null;
        }
        ListNode n;
        while ((n = head.next) != null) {
            head.next = null;
            head = n;
        }
        return head;
    }

    public ListNode cycleNode2(ListNode head) {
        ListNode slow = head, quick = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (slow == quick) { // 成环
                slow = head; // slow 从初始头节点开始
                while (slow != quick) {
                    slow = slow.next;
                    quick = quick.next;
                }
                return slow;
            }
        }
        return null;
    }

    public void printList(ListNode head) {
        while (head != null) {
            System.out.println(head.getVal());
            head = head.next;
        }
    }
}
