package sort;

import java.util.Arrays;

/**
 * @author xuxiang
 */
public class QuickSort {
//    public static void main(String[] args) {
//        int[] a = new int[]{2, 3, 9, 4, 5};
//        QuickSort quickSort = new QuickSort();
//        quickSort.sort(a);
//        System.out.println(Arrays.toString(a));
//    }
//
//    public void sort(int[] a) {
//        sort(a, 0, a.length - 1);
//    }
//
//    private void sort(int[] a, int left, int right) {
//        if (left < right) {
//            int p = partition(a, left, right);
//            sort(a, left, p - 1);
//            sort(a, p + 1, right);
//        }
//    }
//
//    /**
//     * 分区，找到right位置的数字的正确位置，并且左边比之小，右边比之大
//     *
//     * @param a
//     * @param left
//     * @param right
//     * @return
//     */
//    private int partition(int[] a, int left, int right) {
//        int p = left; // 需要最终替换的位置
//        int x = a[right];
//        for (int i = left; i < right; i++) {
//            if (a[i] < x) {// 如果比之小，那么就和要替换的位置交换值，再替换位置+1
//                int tem = a[i];
//                a[i] = a[p];
//                a[p] = tem;
//                p++;
//            }
//        }
//        // 最后把数字放在正确的位置上
//        int tem = a[p];
//        a[p] = x;
//        a[right] = tem;
//        return p;
//    }

    // 例子：[3,7,2,4,6] 第一次partition：
    //      [3,7,2,4,6]
    //      [3,7,2,4,6]
    //      [3,2,7,4,6]
    //      [3,2,4,7,6]
    //      [3,2,4,6,7]
    public void sort(int[] numbers) {
        sort0(numbers, 0, numbers.length - 1);
    }

    private void sort0(int[] numbers, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 找出 hi 位置数字应该存在的位置，使得前面的数比之小，后面的数比之大
        int mid = partition(numbers, lo, hi);
        sort0(numbers, lo, mid - 1);
        sort0(numbers, mid + 1, hi);
    }

    private int partition(int[] numbers, int lo, int hi) {
        int cur = numbers[hi];
        int i, j = lo; // i代表遍历的元素，j代表比cur更大的元素的起始位置
        for (i = lo; i < hi; i++) {
            if (numbers[i] < cur) {
                // 小的数和j位置进行交换
                swap(numbers, i, j++);
            }
        }
        // 最后把当前数和较大数起始位置，即j进行交换
        swap(numbers, j, hi);
        return j;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
