import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;

/**
 * Determine if a new point p lies inside a polygon defined in a list of N
 * points (x1, y1), (x2, y2), ..., (xN, yN), If p is on the boundary of the
 * polygon, you should return false
 */

public class Question236 {
    public static void main(String[] args) {
        // Create a list to hold the points of the polygon
        List<Point2D.Double> polygon = new ArrayList<>();
        polygon.add(new Point2D.Double(1, 1));
        polygon.add(new Point2D.Double(1, 3));
        polygon.add(new Point2D.Double(3, 3));
        polygon.add(new Point2D.Double(3, 1));

        // Test point inside the polygon
        Point2D.Double testPoint1 = new Point2D.Double(2, 2);
        boolean isInside1 = isPointInPolygon(polygon, testPoint1);
        System.out.println("Point (2,2) is inside the polygon: " + isInside1); // should print true

        // Test point outside the polygon
        Point2D.Double testPoint2 = new Point2D.Double(4, 4);
        boolean isInside2 = isPointInPolygon(polygon, testPoint2);
        System.out.println("Point (4,4) is inside the polygon: " + isInside2); // should print false

        // Test point on the polygon
        Point2D.Double testPoint3 = new Point2D.Double(1, 1);
        boolean isInside3 = isPointInPolygon(polygon, testPoint3);
        System.out.println("Point (1,1) is inside the polygon: " + isInside3); // should print false
    }

    // solve the problem using ray casting algorithm / point in polygon algorithm
    public static boolean isPointInPolygon(List<Point2D.Double> polygon, Point2D.Double testPoint) {
        boolean isInside = false;

        // Iterate over each edge of the polygon
        int numPoints = polygon.size();
        for (int i = 0, j = numPoints - 1; i < numPoints; j = i++) {
            Point2D.Double pointI = polygon.get(i);
            Point2D.Double pointJ = polygon.get(j);

            boolean isPointBetweenVerticalBounds = (pointI.y > testPoint.y) != (pointJ.y > testPoint.y);

            if (isPointBetweenVerticalBounds) {
                // Compute intersection of edge with horizontal line at y=testPoint.y
                double intersectionAtX = (pointJ.x - pointI.x) * (testPoint.y - pointI.y) / (pointJ.y - pointI.y)
                        + pointI.x;

                if (testPoint.x == intersectionAtX) {
                    // Point is on the boundary, so return false
                    return false;
                }

                boolean isIntersectionRightOfTestPoint = testPoint.x < intersectionAtX;

                if (isIntersectionRightOfTestPoint) {
                    isInside = !isInside;
                }
            }
        }

        return isInside;
    }
}
