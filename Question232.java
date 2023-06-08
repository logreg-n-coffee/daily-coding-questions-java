
/**
 * Implement a PrefixMapSum class with the following methods:
 * 
 * insert(key: str, value: int): Set a given key's value in the map. If the key
 * already exists, overwrite the value.
 * sum(prefix: str): Return the sum of all values of keys that begin with a
 * given prefix.
 * For example, you should be able to run the following code:
 * 
 * mapsum.insert("columnar", 3)
 * assert mapsum.sum("col") == 3
 * 
 * mapsum.insert("column", 2)
 * assert mapsum.sum("col") == 5
 */

import java.util.HashMap;
import java.util.Map;

public class Question232 {
    static class PrefixMapSum {
        private Map<String, Integer> map;

        public PrefixMapSum() {
            map = new HashMap<>();
        }

        public void insert(String key, int value) {
            map.put(key, value);
        }

        public int sum(String prefix) {
            int sum = 0;
            for (String key : map.keySet()) {
                if (key.startsWith(prefix)) {
                    sum += map.get(key);
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        PrefixMapSum mapsum = new PrefixMapSum();

        mapsum.insert("columnar", 3);
        System.out.println(mapsum.sum("col")); // Should print 3

        mapsum.insert("column", 2);
        System.out.println(mapsum.sum("col")); // Should print 5
    }
}
