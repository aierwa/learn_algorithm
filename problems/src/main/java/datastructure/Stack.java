package datastructure;

import java.util.EmptyStackException;

/**
 * @author xuxiang
 * 栈，使用单链表实现
 */
public class Stack<E> {
    private Node<E> head = new Node<>(null);

    /**
     * 压入
     *
     * @param e
     */
    public void push(E e) {
        Node<E> n = new Node<>(e);
        n.next = head.next;
        head.next = n;
    }

    /**
     * 弹出
     *
     * @return
     */
    public E pop() {
        Node<E> n = head.next;
        if (n == null) {
            throw new EmptyStackException();
        }
        head.next = n.next;
        return n.e;
    }

    /**
     * 获取栈顶元素（不弹出）
     * @return
     */
    public E peek(){
        return head.next.e;
    }

    public boolean empty() {
        return head.next == null;
    }


    static class Node<E> {
        E e;
        Node<E> next;

        public Node(E e) {
            this.e = e;
        }
    }
}
