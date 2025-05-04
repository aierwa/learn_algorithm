package string;

import java.util.*;

/**
 * @author xuxiang
 */
public class StringSolution {
    public static void main(String[] args) {
        StringSolution solution = new StringSolution();
        String str = "aaddbbccc";
        solution.test1(str);
        solution.test2("aaddbb33cccA");

        System.out.println("some 和 meso 是否异位词："+ solution.hasSameChars("some", "meso"));
        System.out.println("car 和 can 是否异位词："+ solution.hasSameChars("car", "can"));
        System.out.println("career 和 camel 是否异位词："+ solution.hasSameChars("career", "camel"));
    }


    /**
     * 字符串合并，输出次数，并排序
     * aaddbbccc -> a2b2c3d2
     *
     * @param str
     */
    public void test1(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }

        Character[] cs = new Character[map.size()];
        map.keySet().toArray(cs);
        Arrays.sort(cs);

        StringBuilder sb = new StringBuilder();
        for (Character c : cs) {
            sb.append(c).append(map.get(c));
        }

        System.out.println(sb.toString());
    }

    /**
     * 字符串合并，输出次数，并排序，过滤到非字母数字，有大写则输出大写
     * aaddbbc33ccA -> Aa3b2c3d2
     *
     * @param str
     */
    public void test2(String str) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isAlphabetic(c)) {
                continue;
            }
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
                set.add(c);
            }
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }

        Character[] cs = new Character[map.size()];
        map.keySet().toArray(cs);
        Arrays.sort(cs);

        StringBuilder sb = new StringBuilder();
        for (Character c : cs) {
            if (set.contains(c)) {
                sb.append(Character.toUpperCase(c));
            }
            sb.append(c).append(map.get(c));
        }

        System.out.println(sb.toString());
    }

    /**
     * 判断两个单词是否为异位单词（默认都是小写字母）
     * 如 some,meso 就是；  dog 和 goo 就不是
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean hasSameChars(String s1, String s2) {
        // 构造一个数组，分别遍历s1和s2，s1中的字母位置加1，s2的字母位置减1
        // O(n)
        int[] arr = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            arr[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            arr[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAlpha(char c) {
        return (c > 'A' && c < 'Z') || (c > 'a' && c < 'z');
    }

    public boolean isUpperCase(char c) {
        return (c > 'A' && c < 'Z');
    }
}
