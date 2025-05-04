package other;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xuxiang
 */
public class DemoTest {
    public static void main(String[] args) {
        int[][] o = new int[][]{{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};

        DemoTest test = new DemoTest();
        System.out.println(Arrays.toString(test.LRU(o, 3)));
    }

    public int[] LRU (int[][] operators, int k) {
        // write code here
        // set get 时间复杂度为O(1)，那么应该用 hashmap 结构，需要有淘汰，那么用 LinkedHashMap
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(operators.length, 0.75f, true){
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return this.size() > k;
            }
        };
        // 判断有几个 get 操作
        int getCount = 0;
        for(int i=0; i<operators.length;i++){
            if(operators[i][0] == 2) {
                getCount++;
            }
        }

        int[] re = new int[getCount];
        int idx = 0;

        for(int i=0; i<operators.length;i++){
            if(operators[i][0] == 1) {
                map.put(operators[i][1], operators[i][2]);
            } else if (operators[i][0] == 2){
                re[idx++] = map.get(operators[i][1]) == null ? -1 : map.get(operators[i][1]);
            }
        }
        return re;

    }
}
