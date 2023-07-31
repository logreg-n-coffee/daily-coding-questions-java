/**
 * You are given an array representing the heights of neighboring buildings on a
 * city street, from east to west. The city assessor would like you to write an
 * algorithm that returns how many of these buildings have a view of the setting
 * sun, in order to properly value the street.
 * 
 * For example, given the array [3, 7, 8, 3, 6, 1], you should return 3, since
 * the top floors of the buildings with heights 8, 6, and 1 all have an
 * unobstructed view to the west.
 * 
 * Can you do this using just one forward pass through the array?
 */

public class Question285 {
    public static void main(String[] args) {
        int[] buildings = { 3, 7, 8, 3, 6, 1 };
        int result = buildingsWithSunsetView(buildings);
        System.out.println("The number of buildings with a sunset view is: " + result);
    }

    public static int buildingsWithSunsetView(int[] buildings) {
        if (buildings == null || buildings.length == 0) {
            return 0;
        }

        int count = 1; // the last building always has a view
        int maxHeight = buildings[buildings.length - 1];

        for (int i = buildings.length - 2; i >= 0; i--) {
            if (buildings[i] > maxHeight) {
                count++;
                maxHeight = buildings[i];
            }
        }

        return count;
    }
}
