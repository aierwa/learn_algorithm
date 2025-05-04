package deque;

import java.util.Arrays;

/**
 * @author xuxiang
 */
public class DequeTest {
    public static void main(String[] args) {
        DequeSolution solution = new DequeSolution();

        System.out.println("------ test sliding window ------");
        int[] tempArr = new int[]{1,3,-1,-3,5,3,6,7};
        System.out.println("window is 3, array: " + Arrays.toString(tempArr));
        System.out.println("result: " + Arrays.toString(solution.slidingWindowMaxValue(tempArr, 3)));
        System.out.println("result: " + Arrays.toString(solution.slidingWindowMaxValue(new int[]{1,3,1,2,0,5}, 3)));
        System.out.println("result: " + Arrays.toString(solution.slidingWindowMaxValue(new int[]{1,-1}, 1)));

    }
}
