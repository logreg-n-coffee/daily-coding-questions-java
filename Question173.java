/*
Write a function to flatten a nested dictionary. Namespace the keys with a period.

For example, given the following dictionary:

{
    "key": 3,
    "foo": {
        "a": 5,
        "bar": {
            "baz": 8
        }
    }
}
it should become:

{
    "key": 3,
    "foo.a": 5,
    "foo.bar.baz": 8
}
You can assume keys do not contain dots in them, i.e. no clobbering will occur.
 */

import java.util.HashMap;
import java.util.Map;

public class Question173 {
    public static void main(String[] args) {
        Map<String, Object> input = new HashMap<>();
        input.put("key", 3);

        Map<String, Object> foo = new HashMap<>();
        input.put("foo", foo);
        foo.put("a", 5);

        Map<String, Object> bar = new HashMap<>();
        foo.put("bar", bar);
        bar.put("baz", 8);

        Map<String, Object> flattened = flatten(input);
        System.out.println(flattened);
    }

    // time complexity is O(n), where n is the total number of key-value pairs in the input dictionary,
    // including both nested and non-nested pairs.
    // overall space complexity of the flatten function is O(n + d),
    // where 'n' is the total number of key-value pairs in the input dictionary,
    // and 'd' is the maximum depth of nested dictionaries.
    public static Map<String, Object> flatten(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        flattenHelper(input, output, "");
        return output;
    }

    public static void flattenHelper(Map<String, Object> input, Map<String, Object> output, String currentKey) {
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            String newKey = currentKey.isEmpty() ? entry.getKey() : currentKey + "." + entry.getKey();
            if (entry.getValue() instanceof Map) {
                // noinspection unchecked
                flattenHelper((Map<String, Object>) entry.getValue(), output, newKey);
            } else {
                output.put(newKey, entry.getValue());
            }
        }
    }
}
