package game.build.geometric;

import game.build.calculators.CompareWithEpsilon;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.geometric.Point object (x,y) with: distance, equals, getX, getY methods.
 */
public class Point {

    private double x;
    private double y;


    /**
     * constructor
     * <p>
     *     The function receive two integer and insert into new game.build.geometric.Point.
     * </p>
     * @param x type double, width.
     * @param y type double, height.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * @return the x values of the received game.build.geometric.Point, type double.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y values of the received game.build.geometric.Point, type double.
     */
    public double getY() {
        return this.y;
    }

    ////////////////////////////////////////////////////////////////////////////

     /**
     * distance between two Points.
     * <p>
     *     The function receive two Points and return the distance
     *     of this point to the other point
     * </p>
     * @param other type game.build.geometric.Point.
     * @return distance between the Points, type double.
     */
    public double distance(Point other) {

        // Calculate the distance between the Points.
        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
    }

    /**
     * Check if two Points are equals.
     * <p>
     *     The function receive two Points and return true if the points are
     *     equal, false otherwise.
     * </p>
     * @param other type game.build.geometric.Point.
     * @return true if the points are equal, false otherwise, type boolean.
     */
    public boolean equals(Point other) {

        // Check if the received game.build.geometric.Point defined.
        if (other == null) {
            return false;
        }

        // Check if the Points equal and return the answer.
        return  CompareWithEpsilon.compare(other.getX(), this.x) == 0
                && CompareWithEpsilon.compare(other.getY(), this.y) == 0;

    }

}

