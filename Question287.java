
/**
 * You are given a list of (website, user) pairs that represent users visiting
 * websites. Come up with a program that identifies the top k pairs of websites
 * with the greatest similarity.
 * 
 * For example, suppose k = 1, and the list of tuples is:
 * 
 * [('a', 1), ('a', 3), ('a', 5),
 * ('b', 2), ('b', 6),
 * ('c', 1), ('c', 2), ('c', 3), ('c', 4), ('c', 5)
 * ('d', 4), ('d', 5), ('d', 6), ('d', 7),
 * ('e', 1), ('e', 3), ('e': 5), ('e', 6)]
 * Then a reasonable similarity metric would most likely conclude that a and e
 * are the most similar, so your program should return [('a', 'e')].
 */

import java.util.*;

public class Question287 {
    /**
     * Use Jaccard similarity to calculate the similarity between two sets.
     * J(A, B) = |A ∩ B| / |A ∪ B| - pseudo code:
     * len(visitors[a] & visitors[b]) /
     * len(visitors[a] | visitors[b])
     * 
     * @param args
     */
    public static void main(String[] args) {
        List<Pair<String, Integer>> visits = Arrays.asList(
                new Pair<>("a", 1), new Pair<>("a", 3), new Pair<>("a", 5),
                new Pair<>("b", 2), new Pair<>("b", 6),
                new Pair<>("c", 1), new Pair<>("c", 2), new Pair<>("c", 3), new Pair<>("c", 4), new Pair<>("c", 5),
                new Pair<>("d", 4), new Pair<>("d", 5), new Pair<>("d", 6), new Pair<>("d", 7),
                new Pair<>("e", 1), new Pair<>("e", 3), new Pair<>("e", 5), new Pair<>("e", 6));

        List<Pair<String, String>> topPairs = topKSimilarWebsites(1, visits);
        for (Pair<String, String> pair : topPairs) {
            System.out.println("the top k pairs of websites with the greatest similarity: (" + pair.getKey() + ", "
                    + pair.getValue() + ")");
        }

    }

    public static List<Pair<String, String>> topKSimilarWebsites(int k, List<Pair<String, Integer>> visits) {
        // Create a Map from website to set of users
        Map<String, Set<Integer>> websiteUsers = new HashMap<>();
        for (Pair<String, Integer> visit : visits) {
            if (!websiteUsers.containsKey(visit.getKey())) {
                websiteUsers.put(visit.getKey(), new HashSet<>());
            }
            websiteUsers.get(visit.getKey()).add(visit.getValue());
        }

        // Calculate the Jaccard similarity for each pair of websites
        List<Pair<String, String>> websitePairs = new ArrayList<>();
        Map<Pair<String, String>, Double> similarities = new HashMap<>();
        for (String website1 : websiteUsers.keySet()) {
            for (String website2 : websiteUsers.keySet()) {
                if (!website1.equals(website2)) {
                    Pair<String, String> pair = new Pair<>(website1, website2);
                    if (!similarities.containsKey(pair)) {
                        Set<Integer> users1 = websiteUsers.get(website1);
                        Set<Integer> users2 = websiteUsers.get(website2);
                        double jaccardSimilarity = (double) intersection(users1, users2).size()
                                / union(users1, users2).size();
                        similarities.put(pair, jaccardSimilarity);
                        websitePairs.add(pair);
                    }
                }
            }
        }

        // Sort the website pairs based on their similarities in descending order
        websitePairs.sort((pair1, pair2) -> Double.compare(similarities.get(pair2), similarities.get(pair1)));

        // Print the website users
        for (Map.Entry<String, Set<Integer>> w : websiteUsers.entrySet()) {
            System.out.println(w.getKey() + ", " + w.getValue());
        }

        System.out.println();

        // Print the website pairs
        for (Pair<String, String> pair : websitePairs) {
            System.out.println("(" + pair.getKey() + ", " + pair.getValue() + ")");
        }

        // Return the top k pairs
        return websitePairs.subList(0, k);
    }

    private static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> intersect = new HashSet<>(set1);
        intersect.retainAll(set2);
        return intersect;
    }

    private static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        return union;
    }

    public static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
