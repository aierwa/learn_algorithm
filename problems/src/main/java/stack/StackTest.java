package stack;


import java.util.Arrays;

/**
 * @author xuxiang
 */
public class StackTest {
    public static void main(String[] args) {
        StackSolution solution = new StackSolution();

        System.out.println("------ test valid brackets ------");
        String s1 = "1+[2-(7+8)]";
        String s2 = "1+[2-(7+8)]}";
        String s3 = "{1+[2-(7+8)]}";
        System.out.println(s1 + " brackets is valid ? " + solution.checkValidBrackets(s1));
        System.out.println(s2 + " brackets is valid ? " + solution.checkValidBrackets(s2));
        System.out.println(s3 + " brackets is valid ? " + solution.checkValidBrackets(s3));

        System.out.println("------ test dailyTemperature ------");
        int[] tempArr = new int[]{23, 24, 25, 21, 19, 22, 26, 23};
        System.out.println("dailyTemperature: " + Arrays.toString(tempArr));
        System.out.println("tem result: " + Arrays.toString(solution.dailyTemperature(tempArr)));

    }

}
