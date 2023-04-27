/*
You are given a list of rectangles represented by min and max x- and y-coordinates.
Compute whether a pair of rectangles overlap each other.
If one rectangle completely covers another, it is considered overlapping.

For example, given the following rectangles:

{
    "top_left": (1, 4),
    "dimensions": (3, 3) # width, height
},
{
    "top_left": (-1, 3),
    "dimensions": (2, 1)
},
{
    "top_left": (0, 5),
    "dimensions": (4, 3)
}
return true as the first and third rectangle overlap each other.
 */

public class Question187 {
    public static void main(String[] args) {
        Rectangle[] rectangles = {
                new Rectangle(1, 4, 3, 3),
                new Rectangle(-1, 3, 2, 1),
                new Rectangle(0, 5, 4, 3)
        };

        System.out.println(hasOverlappingRectangles(rectangles));
    }

    static class Rectangle {
        int x1, y1, x2, y2;

        // assumption: positive y-axis goes upwards, positive x-axis goes right
        Rectangle(int x1, int y1, int width, int height) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x1 + width;
            this.y2 = y1 - height;
        }
    }

    static boolean hasOverlappingRectangles(Rectangle[] rectangles) {
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = i + 1; j < rectangles.length; j++) {
                if (isOverlapping(rectangles[i], rectangles[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isOverlapping(Rectangle r1, Rectangle r2) {
        return (r1.x1 < r2.x2 &&
                r1.x2 > r2.x1 &&
                r1.y1 > r2.y2 &&
                r1.y2 < r2.y1
        );
    }
}
