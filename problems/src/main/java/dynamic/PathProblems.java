package dynamic;

/**
 * @author xuxiang
 * 路径问题
 */
public class PathProblems {
    public static void main(String[] args) {

    }

    /**
     * 从左上角初始点开始，到点(m,n)可能经过的路径，只能往右或下走
     * 比如对于 (3,3)
     * x0 x x
     * x x x
     * x x x33
     * 思路：每一个点的可能性，就等于他上面点和左边点可能性之和，递归。第一排和第一列的可能性为 1
     *
     * @param m
     * @param n
     */
    public int test1(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        } else {
            return test1(m - 1, n) + test1(m, n - 1);
        }
    }
}
