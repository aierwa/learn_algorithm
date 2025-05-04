package queue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 优先级队列
 *
 * @author xuxiang
 */
public class PriorityQueueSolution {

    /**
     * 前 k 个出现次数最高的单词
     *
     * @param words 单词数组
     * @param k     取 k 个
     * @return 单词数组
     */
    public String[] topKWords(String[] words, int k) {
        // 先用 map 保存单词出现次数，复杂度 O(n)
        // 再构建
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

//        // 方式1，直接利用 List.sort() 进行排序，复杂度 O(nlogn)，然后再取前k
//        List<String> keys = new ArrayList<>(countMap.keySet());
//        keys.sort((k1, k2) ->
//                countMap.get(k1).equals(countMap.get(k2)) ? k1.compareTo(k2) : countMap.get(k2).compareTo(countMap.get(k1))
//        );
//        return keys.subList(0, k).toArray(new String[0]);

        // 方式2，构建小根堆（优先级小的在根顶，超过数量k则弹出，最终保留最大的前k个
        PriorityQueue<String> heap = new PriorityQueue<>((k1, k2) ->
                countMap.get(k1).equals(countMap.get(k2)) ? k2.compareTo(k1) : countMap.get(k1).compareTo(countMap.get(k2))
        );
        countMap.keySet().forEach(word -> {
            heap.offer(word);
            if (heap.size() > k) {
                heap.poll();
            }
        });
        List<String> re = new ArrayList<>();
        while (!heap.isEmpty()) {
            re.add(heap.poll());
        }
        Collections.reverse(re);
        return re.toArray(new String[0]);
    }

    /**
     * 获取数组中出现次数前K的数
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKNumbers(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(countMap::get));
        countMap.keySet().forEach(num -> {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        });
        return queue.stream().mapToInt(Integer::intValue).toArray();

    }
}
