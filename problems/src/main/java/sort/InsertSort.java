package sort;

/**
 * 插入排序
 *
 * @author xuxiang
 */
public class InsertSort {
    public void sort(int[] numbers) {
        if (numbers.length < 2) {
            return;
        }
        // 前后比较，如果后面比前面大，则不交换，如果小，则交换，交换后继续比较
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j > 0; j--) {
                if (numbers[j] < numbers[j - 1]) {
                    swap(numbers, j, j - 1);
                }
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
