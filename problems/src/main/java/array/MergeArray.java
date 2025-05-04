package array;

/**
 * 合并有序数组
 *
 * @author xuxiang
 */
public class MergeArray {

    public int[] merge(int[] array1, int[] array2) {
        if (array1 == null) return array2;
        if (array2 == null) return array1;
        int[] arr = new int[array1.length + array2.length];
        int i = 0, m = 0, n = 0;
        while (i < arr.length) {
            if (m == array1.length) {
                arr[i++] = array2[n++];
            } else if (n == array2.length) {
                arr[i++] = array1[m++];
            } else if (array1[m] < array2[n]) {
                arr[i++] = array1[m++];
            } else {
                arr[i++] = array2[n++];
            }
        }
        return arr;
    }
}
