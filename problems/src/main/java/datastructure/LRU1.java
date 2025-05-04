package datastructure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xuxiang
 * 通过 LinkedHashMap 实现 LRU
 */
public class LRU1<K, V> extends LinkedHashMap<K, V> {
    // 允许存放的最大数目
    private int size;

    public LRU1(int size) {
        super(16, 0.75f, true);
        this.size = size;
    }

    // 重写 removeEldestEntry，当超过指定数目就删除最老元素
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.size;
    }
}
