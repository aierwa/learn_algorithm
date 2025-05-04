package node;

import datastructure.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuxiang
 * 链表问题
 */
public class LinkedListSolution {

    /**
     * 合并两个有序的链表
     *
     * @param list1 链表1
     * @param list2 链表2
     * @return 新有序链表表头
     */
    public ListNode mergeTwoSortedList(ListNode list1, ListNode list2) {
        // 构建一个虚假头
        ListNode dummy, tail;
        dummy = tail = new ListNode(0);
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        if (list1 != null) {
            tail.next = list1;
        }
        if (list2 != null) {
            tail.next = list2;
        }
        return dummy.next;
    }

    /**
     * K个一组翻转链表，不够k个的一组不翻转
     * 链表：1-2-3-4-5
     * k=2: 2-1-4-3-5
     * k=3: 3-2-1-4-5
     *
     * @param head 表头
     * @param k    一组
     * @return 新表头
     */
    public ListNode reverseSplit(ListNode head, int k) {
        // k个翻转后，旧头的next指针指向下一组的新头
        // 先检查一下够不够k个，不够则直接返回头
        int c = 0;
        ListNode temp = head;
        while (temp != null && c < k) {
            c++;
            temp = temp.next;
        }
        if (c < k) {
            return head;
        }

        // 进行翻转
        ListNode oldHead = head, pre = null, next;
        while (c-- > 0) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        // 将旧头指向下一个分组的新头
        oldHead.next = reverseSplit(head, k);
        // 返回翻转后的新头，就是 pre
        return pre;

    }

    public int[] getNodeValues(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
