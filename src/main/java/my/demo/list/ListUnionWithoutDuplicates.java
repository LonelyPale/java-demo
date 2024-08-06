package my.demo.list;

import java.util.*;

public class ListUnionWithoutDuplicates {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("苹果");
        a.add("香蕉");
        a.add("橘子");

        List<String> b = new ArrayList<>();
        b.add("香蕉");
        b.add("葡萄");
        b.add("橘子");

        // 合并并去重
        List<String> c = mergeAndRemoveDuplicates(a, b);

        // 输出结果
        for (String item : c) {
            System.out.println(item);
        }
    }

    public static List<String> mergeAndRemoveDuplicates(List<String> listA, List<String> listB) {
        // 使用Set来去重
        Set<String> set = new HashSet<>(listA);
        set.addAll(listB);

        // 将Set转换回List
        return new ArrayList<>(set);
    }
}
