package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author xuxiang
 * 排序问题
 * <p>
 * 冒泡
 * 快速排序
 */
public class SortProblem {
    public static void main(String[] args) {
        int[] array1 = {4, 5, 2, 34, 2, 9, 1};
        SortProblem sortProblem = new SortProblem();
        BubbleSort bubbleSort = new BubbleSort();
        InsertSort insertSort = new InsertSort();
//        MergeSort mergeSort = new MergeSort();
        QuickSort quickSort = new QuickSort();
        ShellSort shellSort = new ShellSort();
//        bubbleSort.sort(array1); // 冒泡
//        insertSort.sort(array1); // 插入排序
//        mergeSort.sort(array1); // 归并排序
//        quickSort.sort(array1); // 快速排序
        shellSort.sort(array1); // 希尔排序
        System.out.println(Arrays.toString(array1));
//        System.out.println(Arrays.toString(sortProblem.sortByArrays(array1))); // Arrays 工具
//        System.out.println(Arrays.toString(sortProblem.sortByBubble(array1))); // 冒泡
//        System.out.println(Arrays.toString(sortProblem.sortByQuick(array1))); // 快速排序
//        System.out.println(Arrays.toString(sortProblem.sortByRandomQuick(array1))); // 随机快速排序
//        System.out.println(Arrays.toString(sortProblem.sortByMerge(array1))); // 归并排序

    }

    /**
     * 使用 Arrays 工具
     * 底层采用 Dual-Pivot Quicksort 双基准快速排序
     *
     * @param arr
     * @return
     */
    public int[] sortByArrays(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    /**
     * 冒泡
     *
     * @param arr
     * @return
     */
    public int[] sortByBubble(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                }
            }
        }
        return arr;
    }

    /**
     * 快排
     * https://blog.csdn.net/nrsc272420199/article/details/82587933
     * 思想：挖一个坑（取一个基准数），从后面往前面判断，比基准数小则把索引位置值挪到前面的坑来，这是索引位置留出坑来了
     * 然后再从前面往后面找，比基准数大则把索引位置值挪到后面的坑
     * 最终前后索引相等时的位置就是基准数最终排序的位置
     *
     * @param arr
     * @return
     */
    public int[] sortByQuick(int[] arr) {
//        sortByQuick(arr, 0, arr.length - 1);
        QuickSort(arr, 0, arr.length - 1);
        return arr;
    }

    public int[] sortByRandomQuick(int[] arr) {
        randomQuickSort(arr, 0, arr.length - 1);
        return arr;
    }

//    private void sortByQuick(int[] arr, int low, int high) {
//        int initLow = low;
//        int initHigh = high;
//        if (low < high) {
//            // 基准数
//            int temp = arr[low];
//
//            while (low < high) {
//                // 从后往前找，如果比基准大，就移动位置继续找
//                while (low < high && arr[high] >= temp) {
//                    high--;
//                }
//                arr[low] = arr[high];
//                // 继续从前往后找，如果比基准小，就移动位置继续找
//                while (low < high && arr[low] <= temp) {
//                    low++;
//                }
//                arr[high] = arr[low];
//            }
//
//            // 一次while循环后得到的low就是temp应该处于的位置
//            arr[low] = temp;
//
//            // 继续以low位置为分界点，递归排序两边
//            sortByQuick(arr, initLow, low - 1);
//            sortByQuick(arr, low + 1, initHigh);
//
//        }
//    }
//
//    private void sortByQuick(int[] arr, int low, int high) {
//        if (low < high) {
//            int idx =
//        }
//    }
//
//    private int partition(int[] arr, int low, int high) {
//        while (low < high) {
//            while (low < high && )
//        }
//    }

    /**
     * 快速排序
     * partition 另外一种划分方式 https://blog.csdn.net/haelang/article/details/44496387  https://img-blog.csdn.net/20150320213934600
     * 从图上很清晰，始终把大的挨着，然后往后挪。 p 和 i 是关键。
     * @param a
     * @param left
     * @param right
     */
    public static void QuickSort(int[] a, int left, int right) {
        if (left < right) {
            int p = partition(a, left, right);
            QuickSort(a, left, p - 1);
            QuickSort(a, p + 1, right);
        }
    }

    //快速排序数组划分，这种方式感觉更容易理解！！
    private static int partition(int[] a, int left, int right) {
        int x = a[right];
        int p = left - 1;
        for (int i = left; i < right; i++) {
            if (a[i] <= x) {
                p++;
                swap(a, p, i);
            }
        }
        swap(a, p + 1, right);
        return p + 1;
    }

    //交换数组a中的a[i]和a[j]
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 快速排序的随机化版本，除了调用划分函数不同，和之前快排的代码结构一模一样
     * 避免这种情况： 1 2 3 4 5，从5开始快排，不变，然后快排 1 2 3 4，直到 1
     * 随机，比如打乱后：1 2 5 4 3，那么从3开始快排，得到 1 2 3 4 5，再快排 1 2 和 4 5，就更快了。
     */
    public void randomQuickSort(int[] a, int left, int right) {
        if (left < right) {
            int p = randomPartition(a, left, right);
            randomQuickSort(a, left, p - 1);
            randomQuickSort(a, p + 1, right);
        }
    }

    //随机化划分
    public int randomPartition(int[] a, int left, int right) {
        int r = new Random().nextInt(right - left) + left; //生成一个随机数，即是主元所在位置
        swap(a, right, r); //将主元与序列最右边元素互换位置，这样就变成了之前快排的形式。
        return partition(a, left, right); //直接调用之前的代码
    }


    /**
     * 归并排序 https://www.cnblogs.com/chengxiao/p/6194356.html
     * 思想：先分后合，分就是一分为二，左右两边分别进行比较，直到分到只有两个数或一个数为止
     * 合就是把两边的数（已排好序）分别进行比较，放入临时数组，最后把临时数组（已排好序）替换到原数组
     *            {4, 5, 2, 34, 2, 9}                临时数组：{0, 0, 0, 0, 0, 0}
     *     分组后：{4, 5} {2} {34, 2} {9}             临时数组：{0, 0, 0, 0, 0, 0}     {4,5}不用合并
     * 第一次合并：{4, 5} {2} {34, 2} {9}                临时数组：{2, 4, 5, 0, 0, 0}
     * 第一次拷贝：{2, 4} {5} {34, 2} {9}                临时数组：{2, 4, 5, 0, 0, 0}
     * 第二次合并：{2, 4} {5} {34, 2} {9}                临时数组：{2, 4, 5, 2, 34, 0}
     * 第二次拷贝：{2, 4} {5} {2, 34} {9}                临时数组：{2, 4, 5, 2, 34, 0}
     * 第三次合并：{2, 4} {5} {2, 34} {9}                临时数组：{2, 4, 5, 2, 9, 34}
     * 第三次拷贝：{2, 4, 5} {2, 9, 34}                临时数组：{2, 4, 5, 2, 9, 34}
     * 第四次合并：{2, 4, 5} {2, 9, 34}                临时数组：{2, 2, 3, 5, 9, 34}
     * 第四次拷贝：{2, 2, 3, 5, 9, 34}              临时数组：{2, 2, 3, 5, 9, 34}
     * @param arr
     */
    public int[] sortByMerge(int[] arr) {
        sortByMerge0(arr, 0, arr.length - 1, new int[arr.length]);
        return arr;
    }

    private void sortByMerge0(int[] arr, int left, int right, int[] tempArr) {
        if (left < right) {
            // 左边比右边大，说明还可以分，找到中间数 mid
            int mid = (left + right)/2;
            // 继续分
            sortByMerge0(arr, left, mid, tempArr); // 左边
            sortByMerge0(arr, mid + 1, right, tempArr); // 右边
            // 将左右的结果进行合并
            merge0(arr, left, mid, right, tempArr);
        }
    }

    // 将左右的结果进行合并：i=left, j=mid+1，分别进行比较，较小者放入tempArr[++left]位置，如果i达到了mid或j达到了right，说明走完了
    // 就把剩下的i和j分别放到tempArr对应位置即可
    private void merge0(int[] arr, int left, int mid, int right, int[] tempArr) {
        int i = left, j = mid + 1;
        for (; i < mid + 1 && j < right + 1; ) {
            if (arr[i] < arr[j]) {
                tempArr[left++] = arr[i];
                i++;
            } else {
                tempArr[left++] = arr[j];
                j++;
            }
        }
        while (i < mid + 1) {
            tempArr[left++] = arr[i];
            i++;
        }
        while (j < right + 1) {
            tempArr[left++] = arr[j];
            j++;
        }
        // 把 tempArr 的内容拷贝到 arr 中
        while (left-- > 0) {
            arr[left] = tempArr[left];
        }
    }


    /**
     * TODO 堆排序 https://www.cnblogs.com/chengxiao/p/6129630.html
     * 思想：将数组映射成大顶推或小顶堆，然后将最顶上的数据放在末尾，继续调整剩下的元素为堆，再继续放，直到放完为止
     * 主要是对如何映射不是很懂
     *
     * @param arr
     * @return
     */
    public int[] sortByHeap(int[] arr) {
        return arr;
    }

}
