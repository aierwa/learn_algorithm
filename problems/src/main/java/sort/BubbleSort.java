package sort;

/**
 * 冒泡排序
 *
 * @author xuxiang
 */
public class BubbleSort {
    public void sort(int[] numbers) {
        boolean hasChange = true;
        for (int i = 0; i < numbers.length && hasChange; i++) {
            hasChange = false;
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j, j + 1);
                    hasChange = true;
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
