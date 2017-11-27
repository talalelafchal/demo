package application;

import java.util.Comparator;

/**
 * Created by Talal on 27.11.17.
 */
public class Point implements Comparable<Point> {
    private double x;
    private double y;

    public final Comparator<Point> SLOPE_ORDER = new PointSlopeComparator();


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    /**
     * calculate the slope between 2 Points
     */
    public double getSlope(Point p) {
        if (p.x == x) {
            if (p.y == y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (p.y == y) {
            return 0.0;
        }
        return (p.y - this.y) / (p.x - this.x);
    }


    /**
     *  Order the point by slope
     */
    private class PointSlopeComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slopeP1 = getSlope(p1);
            double slopeP2 = getSlope(p2);
            if (slopeP1 == slopeP2) return 0;
            if (slopeP1 < slopeP2) return -1;
            return 1;
        }
    }

    /**
     * compare two points lexicographically . Comparing y-coordinates and breaking ties by x-coordinates
     */
    @Override
    public int compareTo(Point point) {
        if (this.y == point.y && this.x == point.x) return 0;
        if (this.y < point.y || (this.y == point.y && this.x < point.x)) return -1;
        return 1;
    }

    /**
     * two points are equal if they have the same coordinates
     */
    @Override
    public boolean equals(Object point) {
        if (point == null || !(point instanceof Point)) {
            return false;
        }
        if (point == this) {
            return true;
        }

        Point p = (Point) point;
        if (p.getX() == this.x && p.getY() == this.y) {
            return true;
        }
        return false;
    }
}
