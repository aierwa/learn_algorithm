package datastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuxiang
 * 自定义实现 LRU 算法
 * 定义两个方法：set(key, value)，get(key)
 **/
public class LRU2<K,V> {
    private int cap = 0;
    private Map<K,Node<K, V>> map = new HashMap<>();
    private Node<K, V> head;
    private Node<K, V> tail;

    public LRU2() {
    }

    public LRU2(int cap) {
        this.cap = cap;
    }

    /**
     * put 元素
     * 如果 map已经有了key，那么直接将元素移动到 tail
     * 否则，进行 put 操作，并且将元素移动到 tail
     * 如果 size 超过容量，则删除 head 所在的key
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new Node<>(key, value));
        }
        moveToTail(map.get(key));
        if (cap > 1 && map.size() > cap) {
            // 移除 head 元素
            map.remove(head.k);
            head = head.after;
            head.before = null;
        }
    }

    /**
     * get 元素
     * 如果有，则返回该元素，并且把 key 对应 Node 移动到 tail
     * 如果没有，返回 null
     * @param key
     * @return
     */
    public V get(K key){
        Node<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }
        moveToTail(node);
        return node.v;
    }

    private void moveToTail(Node<K,V> node) {
        if (head == null) {
            head = node;
            tail = head;
            return;
        }
        Node<K,V> b,a;
        b = node.before;
        a = node.after;
        if (b != null) {
            b.after = a;
        }
        if (a != null) {
            a.before = b;
        }
        if (node == head) {
            head = a;
        }
        tail.after = node;
        node.before = tail;
        tail = node;
    }

    static class Node<K, V> {
        K k;
        V v;
        Node(K k,V v) {
            this.k=k;
            this.v=v;
        }
        Node<K, V> before;
        Node<K, V> after;
    }
}
