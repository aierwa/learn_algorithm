package sort;

/**
 * 希尔排序
 * 在插入排序的基础上改进。先进行分组，比如8个元素分4组，0-4一组，1-5一组，先进行插入排序，再继续分组，比如两组。
 * 总体思想是每次分组排序后，宏观上是逐渐有序，最后分组为1时，仅需简单调整即可。
 *
 * @author xuxiang
 */
public class ShellSort {
    public void sort(int[] array) {
        int len = array.length;
        // 增量序列
        int gap = len;
        while ((gap = gap / 2) > 0) {
            for (int i = gap; i < len; i++) {
                // 从第gap个开始走，和 (i-gap), (i-2gap)， ... 位置元素进行插入比较
                int j = i;
                while (j - gap >= 0 && array[j] < array[j - gap]) {
                    swap(array, j, j - gap);
                    j -= gap;
                }
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int tem = array[i];
        array[i] = array[j];
        array[j] = tem;
    }
}
