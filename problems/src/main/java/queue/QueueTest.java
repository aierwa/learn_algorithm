package queue;

import java.util.Arrays;

/**
 * @author xuxiang
 */
public class QueueTest {
    public static void main(String[] args) {
        PriorityQueueSolution solution = new PriorityQueueSolution();
        String[] words = new String[]{"he","she","he","he","she","me","me","this"};
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("top 2: " + Arrays.toString(solution.topKWords(words, 2)));
        System.out.println("top 3: " + Arrays.toString(solution.topKWords(words, 3)));
        int[] nums = new int[]{1,2,3,1,1,2,0};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("top 1: " + Arrays.toString(solution.topKNumbers(nums, 1)));
        System.out.println("top 2: " + Arrays.toString(solution.topKNumbers(nums, 2)));
        System.out.println("top 3: " + Arrays.toString(solution.topKNumbers(nums, 3)));
    }
}
