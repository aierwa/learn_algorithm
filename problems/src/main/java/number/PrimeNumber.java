package number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuxiang
 * 质数问题
 */
public class PrimeNumber {
    public static void main(String[] args) {
        PrimeNumber primeNumber=new PrimeNumber();
        System.out.println(Arrays.toString(primeNumber.getPrimeNumbers(1)));
        System.out.println(Arrays.toString(primeNumber.getPrimeNumbers(2)));
        System.out.println(Arrays.toString(primeNumber.getPrimeNumbers(3)));
        System.out.println(Arrays.toString(primeNumber.getPrimeNumbers(100)));
    }

    public int[] getPrimeNumbers(int max) {
        if (max == 1) return new int[]{1};
        if (max == 2) return new int[]{1,2};
        // 枚举每一个是否能够被其他数整除
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            boolean b = true;
            for (int j = 2; j <= Math.sqrt(i);j++){ // 判断到i的平方根即可
                if (i % j == 0) {
                    b = false;
                    break;
                }
            }
            if (b) {
                list.add(i);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }


}
