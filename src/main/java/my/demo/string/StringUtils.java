package my.demo.string;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    public static void main(String[] args) {
        String a = "${Hello}, this is a ${test}. This is ${only} a ${test}.";
        HashMap<String, String> replacements = new HashMap<>();
        replacements.put("Hello", "Hi-1");
        replacements.put("test", "experiment-2");
        replacements.put("only", "just-3");

        String result = replaceWithMap(a, replacements);
        System.out.println(result);
    }

    public static String replaceWithMap(String str, HashMap<String, String> map) {
        // Iterate over each entry in the map
        for (Map.Entry<String, String> entry : map.entrySet()) {
            // Replace all occurrences of the key with the value
            String key = String.format("${%s}", entry.getKey());
            String value = entry.getValue();
            str = str.replace(key, value);
        }
        return str;
    }
}
