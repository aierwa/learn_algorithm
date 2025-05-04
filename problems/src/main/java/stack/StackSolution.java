package stack;

import datastructure.Stack;

/**
 * @author xuxiang
 * 栈相关问题
 */
public class StackSolution {

    /**
     * 有效括号判断
     *
     * @param input
     * @return
     */
    public boolean checkValidBrackets(String input) {
        // 遍历string
        char[] leftBrackets = new char[]{'{', '[', '('};
        char[] rightBrackets = new char[]{'}', ']', ')'};
        Stack<Character> stack = new Stack<>();
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            for (int j = 0; j < leftBrackets.length; j++) {
                if (c == leftBrackets[j]) {
                    stack.push(c);
                    break;
                }
            }
            for (int j = 0; j < rightBrackets.length; j++) {
                if (c == rightBrackets[j]) {
                    if (stack.empty()) {
                        return false;
                    }
                    if (!stack.pop().equals(leftBrackets[j])) {
                        return false;
                    }
                }
            }
        }
        return stack.empty();
    }

    /**
     * 每日温度 LeetCode-739
     * 根据输入温度，输出当天温度需要等待几天后温度更高
     *
     * @param temperatures [23,24,25,21,19,22,26,23]
     * @return [1, 1, 4, 2, 1, 1, 0, 0]
     */
    public int[] dailyTemperature(int[] temperatures) {
        // 后面和前面相比，大则相差天数为索引之差，并弹出，小则压入栈中
        Stack<TempNode> stack = new Stack<>();
        int[] re = new int[temperatures.length];
        if (temperatures.length < 2) {
            return re;
        }
        stack.push(new TempNode(0, temperatures[0]));
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.empty() && temperatures[i] > stack.peek().val) {
                TempNode n = stack.pop();
                re[n.index] = i - n.index;
            }
            stack.push(new TempNode(i, temperatures[i]));
        }
        return re;
    }

    static class TempNode {
        int index;
        int val;

        public TempNode(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }


}
