/*
Given two rectangles on a 2D graph, return the area of their intersection. If the rectangles don't intersect, return 0.

For example, given the following rectangles:

{
    "top_left": (1, 4),
    "dimensions": (3, 3) # width, height
}
and

{
    "top_left": (0, 5),
    "dimensions": (4, 3) # width, height
}
return 6.
 */

public class Question185 {
    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(new int[]{1, 4}, new int[]{3, 3});
        Rectangle rect2 = new Rectangle(new int[]{0, 5}, new int[]{4, 3});

        System.out.println(areaOfIntersection(rect1, rect2));  // Output: 6
    }

    record Rectangle(int[] topLeft, int[] dimensions) {}

    public static int areaOfIntersection(Rectangle rect1, Rectangle rect2) {
        // Calculate the x-coordinates of the intersection
        int xLeft = Math.max(rect1.topLeft()[0], rect2.topLeft()[0]);
        int xRight = Math.min(
                rect1.topLeft()[0] + rect1.dimensions()[0],
                rect2.topLeft()[0] + rect2.dimensions()[0]
        );

        // Calculate the y-coordinates of the intersection
        int yTop = Math.min(rect1.topLeft()[1], rect2.topLeft()[1]);
        int yBottom = Math.max(
                rect1.topLeft()[1] - rect1.dimensions()[1],
                rect2.topLeft()[1] - rect2.dimensions()[1]
        );

        // check if the rects intersect
        if (xRight <= xLeft || yTop <= yBottom) {
            return 0;
        }

        // Calculate the width and height of the intersection
        int width = xRight - xLeft;
        int height = yTop - yBottom;

        // Calculate the area of the intersection
        return width * height;
    }
}
