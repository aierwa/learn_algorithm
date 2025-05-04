package array;

import java.util.Arrays;

/**
 * @author xuxiang
 */
public class ArrayTest {
    public static void main(String[] args) {
        MergeArray mergeArray = new MergeArray();
        System.out.println(Arrays.toString(mergeArray.merge(new int[]{1,3,5}, new int[]{2,4,5})));
    }
}
