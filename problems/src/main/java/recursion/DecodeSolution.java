package recursion;

/**
 * Leetcode-91
 * 解码问题
 * A-Z 映射 1-26，给一个数字字符串，计算解码总数
 * 如 226，有3种，[2,2,6],[2,26],[22,6]
 * 递归
 *
 * @author xuxiang
 */
public class DecodeSolution {
    public static void main(String[] args) {
        DecodeSolution solution = new DecodeSolution();
        long start = System.currentTimeMillis();
        System.out.println("数字 226 的解法为：" + solution.decode("226"));
        System.out.println("数字 22 的解法为：" + solution.decode("22"));
        System.out.println("数字 27 的解法为：" + solution.decode("27"));
        System.out.println("数字 2 的解法为：" + solution.decode("2"));
        System.out.println("数字 0 的解法为：" + solution.decode("0"));
        System.out.println("数字 06 的解法为：" + solution.decode("06"));
        System.out.println("数字 111111111111111111111111111111111111111111111 的解法为：" + solution.decode(
                "111111111111111111111111111111111111111111111"));
        System.out.println("解法一耗时：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        System.out.println("数字 226 的解法为：" + solution.decode2("226"));
        System.out.println("数字 22 的解法为：" + solution.decode2("22"));
        System.out.println("数字 27 的解法为：" + solution.decode2("27"));
        System.out.println("数字 2 的解法为：" + solution.decode2("2"));
        System.out.println("数字 0 的解法为：" + solution.decode2("0"));
        System.out.println("数字 06 的解法为：" + solution.decode2("06"));
        System.out.println("数字 111111111111111111111111111111111111111111111 的解法为：" + solution.decode2(
                "111111111111111111111111111111111111111111111"));
        System.out.println("解法二耗时：" + (System.currentTimeMillis() - start));

    }


    public int decode(String nums) {
        // 思路：从最后一个字符来算，可能的解法就是他当前的解法+前面一个字符的解法
        char[] chars = nums.toCharArray();
        if (chars[0] == '0') {
            return 0;
        }
        return decode0(chars, nums.length() - 1);
    }

    private int decode0(char[] chars, int index) {
        if (index <= 0) {
            return 1; // 如果已经在第一个位置了，那么就是一种解法
        }
        int count = 0;
        if (chars[index] > '0') {
            // 如果字符大于0，那么解法就是前面那个字符的解法
            count = decode0(chars, index - 1);
        }
        // 如果前面的是1或者（前面是2但当前小于6），那么说明当前字符和上一个字符可以组合在一起，总解法还需要加上前面两个字符的解法
        if (chars[index - 1] == '1' || (chars[index - 1] == '2' && chars[index] <= '6')) {
            count += decode0(chars, index - 2);
        }
        return count;
    }

    public int decode2(String nums) {
        // 思路：递归是自顶向下，这里采用自底向上
        // 第一个字符的解法是1
        // 第二个字符的解法是第一个字符的解法
        // 第三个字符的解法是第二个字符的解法，加上第一个字符的解法（假如第三个字符和第二个字符可以组合为10-26的数字）
        // 以此类推
        char[] chars = nums.toCharArray();
        if (chars[0] == '0') {
            return 0;
        }
        int[] counts = new int[chars.length];
        counts[0] = 1;
        for (int i = 1; i < chars.length; i++) {
            counts[i] = chars[i] == '0' ? 0 : counts[i - 1]; // 如果当前是0的话，加上上一个字符的解法就不成立，只能看当前字符和上一个字符的组合模式
            if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] <= '6')) {
                counts[i] += (i >= 2) ? counts[i - 2] : 1;
            }
        }
        return counts[chars.length - 1];
    }
}
