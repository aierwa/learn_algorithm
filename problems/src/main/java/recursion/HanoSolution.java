package recursion;

/**
 * 汉内塔
 * A 上从小到大的盘子，移动到 C 上
 * 思想：递归
 *
 * @author xuxiang
 */
public class HanoSolution {
    public static void main(String[] args) {
        HanoSolution solution = new HanoSolution();
        System.out.println(" 1 plant: ");
        solution.hano('A', 'B', 'C', 1);
        System.out.println(" 2 plants: ");
        solution.hano('A', 'B', 'C', 2);
        System.out.println(" 3 plants: ");
        solution.hano('A', 'B', 'C', 3);
        System.out.println(" 3 plants: ");
        solution.hano('A', 'B', 'C', 5);
    }

    /**
     * 打印出移动盘子的步骤
     *
     * @param A A 柱
     * @param B B 柱
     * @param C C 柱
     * @param n 一共几个盘子
     */
    public void hano(char A, char B, char C, int n) {
        // 思路：A 要移动到C，那么需要把 n-1个盘子整体挪到B上，然在再把A中的最后一个大盘子挪到C上，再把B上的盘子整体挪到C上
        // 递归
        if (n > 0) {
            hano(A, C, B, n - 1); // 把 n-1个盘子整体挪到B上
            System.out.println(A + " -> " + C); // A中的最后一个大盘子挪到C上
            hano(B, A, C, n - 1); // B上的盘子整体挪到C上
        }
    }
}
