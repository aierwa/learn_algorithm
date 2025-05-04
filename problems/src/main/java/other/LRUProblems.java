package other;

import datastructure.LRU1;
import datastructure.LRU2;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author xuxiang
 * LRU: last recently used 最近最少使用，一般用于缓存淘汰
 * https://www.nowcoder.com/practice/e3769a5f49894d49b871c09cadd13a61?tpId=117&tqId=37804&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * [要求]
 * set和get方法的时间复杂度为O(1)
 * 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 * 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 对于每个操作2，输出一个答案
 *
 * 输入 [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]] , 3
 * 返回 [1,-1]
 */
public class LRUProblems {

    public static void main(String[] args) {
        int[][] o = new int[][]{{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        LRU1<Integer, Integer> lru1 = new LRU1<>(3);
        LRU2<Integer, Integer> lru2 = new LRU2<>(3);
//        System.out.println(Arrays.toString(lru1Test(lru1, 3, o)));
        System.out.println(Arrays.toString(lru2Test(lru2, 3, o)));

    }

    public static int[] lru1Test(LRU1<Integer, Integer> lru, int k, int[][] operators) {
        // 判断有几个 get 操作
        int getCount = 0;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 2) {
                getCount++;
            }
        }

        int[] re = new int[getCount];
        int idx = 0;

        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                lru.put(operators[i][1], operators[i][2]);
            } else if (operators[i][0] == 2) {
                re[idx++] = lru.get(operators[i][1]) == null ? -1 : lru.get(operators[i][1]);
            }
        }
        return re;
    }

    public static int[] lru2Test(LRU2<Integer, Integer> lru, int k, int[][] operators) {
        // 判断有几个 get 操作
        int getCount = 0;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 2) {
                getCount++;
            }
        }

        int[] re = new int[getCount];
        int idx = 0;

        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                lru.put(operators[i][1], operators[i][2]);
            } else if (operators[i][0] == 2) {
                re[idx++] = lru.get(operators[i][1]) == null ? -1 : lru.get(operators[i][1]);
            }
        }
        return re;
    }

}
