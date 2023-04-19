/*
You are given a list of data entries that represent entries and exits of groups of people into a building.
An entry looks like this:
{"timestamp": 1526579928, count: 3, "type": "enter"}

This means 3 people entered the building. An exit looks like this:
{"timestamp": 1526580382, count: 2, "type": "exit"}

This means that 2 people exited the building. timestamp is in Unix time.

Find the busiest period in the building, that is, the time with the most people in the building.
Return it as a pair of (start, end) timestamps.
You can assume the building always starts off and ends up empty, i.e. with 0 people inside.
 */

import java.util.Arrays;
import java.util.Comparator;

public class Question171 {
    public static void main(String[] args) {
        DataEntry[] dataEntries = {
          new DataEntry(1526579928, 3, "enter"),
          new DataEntry(1526580382, 2, "exit"),
        };

        long[] busiestPeriod = findBusiestPeriod(dataEntries);
        System.out.println("Busiest period: (" + busiestPeriod[0] + ", " + busiestPeriod[1] + ").");
    }

    public static class DataEntry {
        long timestamp;
        int count;
        String type;

        public DataEntry(long timestamp, int count, String type) {
            this.timestamp = timestamp;
            this.count = count;
            this.type = type;
        }
    }

    // solve the problem with O(n * log(n)) time and O(n) space
    public static long[] findBusiestPeriod(DataEntry[] dataEntries) {
        // Sort the data entries by timestamp. - O(n * log(n))
        Arrays.sort(dataEntries, Comparator.comparing(entry -> entry.timestamp));

        // Initialize variables for the current number of people in the building,
        // the maximum number of people, and the start and end timestamps of the busiest period.
        int currentPeople = 0, maxPeople = 0;
        long startTime = 0, endTime = 0;

        // Iterate through the sorted data entries,
        // updating the current number of people in the building based on the entry or exit events.
        for (int i = 0; i < dataEntries.length; i++) {
            DataEntry entry = dataEntries[i];

            if ("enter".equalsIgnoreCase(entry.type)) {
                currentPeople += entry.count;
            } else {
                currentPeople -= entry.count;
            }

            // check two conditions
            // c1: i < dataEntries.length - 1: ensures that the current index i is not the last index in the array.
            // c2: entry.timestamp == dataEntries[i + 1].timestamp: checks if the current data entry's timestamp
            // is equal to the next data entry's timestamp.
            if (i < dataEntries.length - 1 && entry.timestamp == dataEntries[i + 1].timestamp) {
                continue;
            }

            // When the current number of people is higher than the maximum,
            // update the maximum and the start and end timestamps accordingly.
            if (currentPeople > maxPeople) {
                maxPeople = currentPeople;
                startTime = entry.timestamp;
                endTime = i < dataEntries.length - 1 ? dataEntries[i + 1].timestamp : entry.timestamp;
            }
        }
        return new long[]{startTime, endTime};
    }
}
