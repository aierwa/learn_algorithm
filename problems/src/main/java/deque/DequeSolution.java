package deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 双端队列
 *
 * @author xuxiang
 */
public class DequeSolution {

    /**
     * 滑动窗口最大值
     *
     * @param nums 输入数组，如 [1,3,-1,-3,5,3,6,7]
     * @param k    窗口大小，如 3
     * @return 窗口右移过程中，每个窗口中的最大值组成的数组，如 [3,3,5,5,6,7]
     */
    public int[] slidingWindowMaxValue(int[] nums, int k) {
        // 利用双端队列，大的数把前面小的数弹出来，直到队列头部（则当前数为最大），小的数则添加到尾部
        // 如果队列大小等于windowSize，那么添加到尾部之前先出队一个，每次最大的数就是头部的数
        // *为了保证出队不在窗口中的元素，队列就保存下标值*
        if (nums.length < 1) {
            return new int[0];
        }
        int[] re = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                // 如果不为空，且值大于队尾的值，则队尾出队
                deque.pollLast();
            }
            // 如果头部不在窗口内，则弹出
            if (!deque.isEmpty() && i - k + 1 > deque.peekFirst()) {
                deque.pollFirst();
            }

            // 将这次的值加入队尾
            deque.addLast(i);
            if (i + 1 - k >= 0) {
                re[i + 1 - k] = nums[deque.peekFirst()];
            }
        }
        return re;
    }


}
