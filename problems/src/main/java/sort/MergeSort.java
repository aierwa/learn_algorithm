package sort;

import java.util.Arrays;

/**
 * 归并排序
 * 不停的二分切割
 * 将每个二分进行比较，利用临时数组，比较完了复制回原数组
 *
 * @author xuxiang
 */
public class MergeSort {
//    public static void main(String[] args) {
//        int[] a = new int[]{2, 3, 9, 4, 5};
//        MergeSort mergeSort = new MergeSort();
//        mergeSort.sort(a);
//        System.out.println(Arrays.toString(a));
//    }
//
//    public void sort(int[] a) {
//        sort0(a, 0, a.length - 1, new int[a.length]);
//    }
//
//    private void sort0(int[] a, int left, int right, int[] temArr) {
//        if (left < right) { // 如果可以分就继续分
//            int mid = (left + right) / 2;
//            sort0(a, left, mid, temArr);
//            sort0(a, mid + 1, right, temArr);
//            // 分隔过后，就来合并结果
//            merge0(a, left, mid, right, temArr);
//        }
//    }
//
//    /**
//     * 合并结果
//     * left - mid 区间是有序的； mid - right 区间是有序的
//     * 需要把 left - right 区间进行重新排序（利用 temArr 中转）
//     *
//     * @param a
//     * @param left
//     * @param mid
//     * @param right
//     * @param temArr
//     */
//    private void merge0(int[] a, int left, int mid, int right, int[] temArr) {
//        int i = left, t = 0, j = mid + 1;
//
//        for (; i < mid + 1 && j < right + 1; ) {
//            temArr[t++] = a[i] < a[j] ? a[i++] : a[j++];
//        }
//        while (i < mid + 1) {
//            temArr[t++] = a[i++];
//        }
//        while (j < right + 1) {
//            temArr[t++] = a[j++];
//        }
//
//        while (left <= right) {
//            a[right--] = temArr[--t];
//        }
//    }

    public void sort(int[] numbers) {
        sort0(numbers, 0, numbers.length - 1);
    }

    private void sort0(int[] numbers, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        sort0(numbers, lo, mid);
        sort0(numbers, mid + 1, hi);
        merge(numbers, lo, mid, hi);
    }

    private void merge(int[] numbers, int lo, int mid, int hi) {
        // [1,8,2,3,5]
        int[] copy = numbers.clone();
        int k = lo, j = mid + 1;
        while (k <= hi) { // 这一轮比较完了
            if (lo > mid) { // 前半部分已经比完，直接移动后面指针即可
                numbers[k++] = copy[j++];
            } else if (j > hi) { // 后半部分已经比完，直接移动前面指针即可
                numbers[k++] = copy[lo++];
            } else if (copy[lo] < copy[j]) { // 取较小者放入原数组，再移动相应指针
                numbers[k++] = copy[lo++];
            } else {
                numbers[k++] = copy[j++];
            }
        }
    }


}
