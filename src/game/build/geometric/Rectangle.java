package game.build.geometric;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.geometric.Rectangle object (location, width and height) with:
 * intersectionPoints, getWidth, getHeight, getUpperLeft, getLowerLeft,
 * getUpperRight, getLowerRight, setUpperLeft methods.
 */
public class Rectangle {

    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private double width;
    private double height;

    /**
     * constructor
     * <p>
     * The function receive point, width and height and insert into new
     * game.build.geometric.Rectangle.
     * </p>
     *
     * @param upperLeft type game.build.geometric.Rectangle, set the location.
     * @param width     type double, width.
     * @param height    type double, height.
     */
    public Rectangle(Point upperLeft, double width, double height) {

        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        //Calculate the other points of the rectangle by the upper left point.
        this.upperRight = new Point(upperLeft.getX() + width,
                upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + height);
        this.lowerRight = new Point(upperRight.getX(), lowerLeft.getY());

    }


    /**
     * Return a (possibly empty) List of intersection points.
     * <p>
     * The function receive line and check the possible intersection points
     * to the line and the rectangle, there can be up to two intersection
     * points.
     * </p>
     *
     * @param line type game.build.geometric.Line.
     * @return list of point, the intersection points with the line,
     * type List<game.build.geometric.Point>.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        List<Point> intersectionPoints = new ArrayList<>();

        //Check for intersection with the upper line of the rectangle.
        Point temp = line.intersectionWith(new Line(upperLeft, upperRight));

        //If there is, add to the list.
        if (temp != null) {
            intersectionPoints.add(temp);
        }

        //Check for intersection with the right side of the rectangle.
        temp = line.intersectionWith(new Line(upperRight, lowerRight));

        //If there is, add to the list.
        if (temp != null) {
            intersectionPoints.add(temp);
        }

        //Check for intersection with the left side of the rectangle.
        temp = line.intersectionWith(new Line(upperLeft, lowerLeft));

        //If there is, add to the list.
        if (temp != null) {
            intersectionPoints.add(temp);
        }

        //Check for intersection with the lower line of the rectangle.
        temp = line.intersectionWith(new Line(lowerLeft, lowerRight));

        //If there is, add to the list.
        if (temp != null) {
            intersectionPoints.add(temp);
        }

        //Return the list.
        return intersectionPoints;
    }

    /**
     * @return the width of the received rectangle, type double.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the received rectangle, type double.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the received rectangle, type game.build.geometric.Point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the lower-left point of the received rectangle, type game.build.geometric.Point.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * @return the upper-right point of the received rectangle, type game.build.geometric.Point.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * @return the lower-right point of the received rectangle, type game.build.geometric.Point.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * Set new upper-left point to rectangle and calculate the other points.
     *
     * @param newPoint the new upper-left point of the rectangle, type game.build.geometric.Point.
     */
    public void setUpperLeft(Point newPoint) {

        //Set the new upper left point.
        this.upperLeft = newPoint;

        //Calculate the other three points.
        this.upperRight = new Point(upperLeft.getX() + width,
                upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + height);
        this.lowerRight = new Point(upperRight.getX(), lowerLeft.getY());

    }
}
