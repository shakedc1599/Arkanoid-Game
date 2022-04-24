package game.build.geometric;

import game.build.calculators.CompareWithEpsilon;

import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.geometric.Line object from two Points with: length, middle, start, end,
 * determinant, isIntersecting, intersectionWith, equals, getM, getB, getP1,
 * getP2, getIsParallelY, isInRangeX, isInRangeY, intersectionWithPoint,
 * closestIntersectionToStartOfLine methods.
 */
public class Line {

    private Point p1;
    private Point p2;
    private double m;
    private double b;
    private boolean isParallelY;
    private boolean isPoint;

    /**
     * Constructor.
     * <p>
     * The function receive two Points and create new line with them.
     * </p>
     *
     * @param start type game.build.geometric.Point, the first point of the line.
     * @param end   type game.build.geometric.Point, the second point of the line.
     */
    public Line(Point start, Point end) {

        //First point.
        this.p1 = start;

        //Second point.
        this.p2 = end;

        //Check if the line is parallel to axis Y.
        this.isParallelY = (this.p1.getX() - this.p2.getX() == 0);

        //Calculate the determinant of the line.
        this.m = determinant(this);

        //Calculate the cutting with X axis.
        this.b = (this.p1.getY() - (this.m * this.p1.getX()));

        //Check if the line is a point.
        this.isPoint = (this.getP1().equals(getP2()));
    }

    /**
     * Constructor.
     * <p>
     * The function receive four integers that present two points and call
     * the first constructor to create new line with them.
     * </p>
     *
     * @param x1 type double, x value to the first point of the line.
     * @param y1 type double, y value to the first point of the line.
     * @param x2 type double, x value to the second point of the line.
     * @param y2 type double, y value to the second point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {

        //Call the first constructor with the two points.
        this(new Point(x1, y1), new Point(x2, y2));
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Length of the line.
     * <p>
     * The function receive line and return his length.
     * </p>
     *
     * @return the length of the line, type double.
     */
    public double length() {

        //Calculate the length of the line, the distance between the two points.
        return this.p1.distance(this.p2);
    }

    /**
     * Calculate the middle point of the line.
     * <p>
     * The function receive line and return his middle point.
     * </p>
     *
     * @return the middle point of the line, type game.build.geometric.Point.
     */
    public Point middle() {

        //Calculate the middle point of the line.
        return new Point((this.p1.getX() + this.p2.getX()) / 2,
                (this.p1.getY() + this.p2.getY()) / 2);
    }

    /**
     * Calculate start point of the line.
     * <p>
     * The function receive line and return his start point.
     * The start points is the point with the higher X value, and if
     * the X value is equals we take the point with the higher Y value.
     * </p>
     *
     * @return the start point of the line, type game.build.geometric.Point.
     */
    public Point start() {

        //Check which point has the higher X value.
        if (this.p1.getX() > this.p2.getX()) {
            return this.p1;
        }
        if (this.p1.getX() < this.p2.getX()) {
            return this.p2;
        }

        //Check which point has the higher Y value.
        if (this.p1.getY() >= this.p2.getY()) {
            return p1;
        }
        return p2;
    }

    /**
     * Calculate end point of the line.
     * <p>
     * The function receive line and return his end point.
     * We use the start function and return the opposite point that the
     * start function return.
     * </p>
     *
     * @return the end point of the line, type game.build.geometric.Point.
     */
    public Point end() {

        //Call start function and return the opposite point.
        return (this.start() == p1) ? p2 : p1;
    }

    /**
     * Calculate the determinant of the line.
     * <p>
     * The function receive line and return his determinant.
     * If the line is parallel to any of the axis we return 0.
     * </p>
     *
     * @param line type game.build.geometric.Line.
     * @return the determinant of the line, type double.
     */
    public double determinant(Line line) {

        //Check if the line is parallel to axis Y.
        if (line.isParallelY) {
            return 0;
        }

        //Calculate the determinant.
        return ((line.getP1().getY() - line.getP2().getY())
                / (line.getP1().getX() - line.getP2().getX()));
    }

    /**
     * @return the determinant values of the received line, type double.
     */
    public double getM() {
        return this.m;
    }

    /**
     * @return the value of the cut with axis X of the received line,
     * type double.
     */
    public double getB() {
        return this.b;
    }

    /**
     * @return the first point of the received line, type game.build.geometric.Point.
     */
    public Point getP1() {
        return this.p1;
    }

    /**
     * @return the second point of the received line, type game.build.geometric.Point.
     */
    public Point getP2() {
        return this.p2;
    }

    /**
     * @return the value of IsParallelY of the received line, type boolean.
     */
    public boolean getIsParallelY() {
        return this.isParallelY;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Check if two lines are equal.
     * <p>
     * The function receive two lines and return true if the lines are
     * equal, false otherwise.
     * </p>
     *
     * @param other type game.build.geometric.Line.
     * @return true if the lines are equal, false otherwise, type boolean.
     */
    public boolean equals(Line other) {

        //Check if the received line defined.
        if (other == null) {
            return false;
        }
        return (this.getP1().equals(other.getP1())
                && this.getP2().equals(other.getP2()));
    }

    /**
     * Check if the lines are intersect.
     * <p>
     * The function receive two lines and returns true if the lines are
     * intersect, false otherwise.
     * We use intersectionWith function and check if the received point is
     * null, if so the lines are not intersect.
     * </p>
     *
     * @param other type game.build.geometric.Line.
     * @return true if the lines are intersect, false otherwise, type boolean.
     */
    public boolean isIntersecting(Line other) {

        //Call intersectionWith and check if null return.
        return !(intersectionWith(other) == null);
    }

    /**
     * Check if X value in the range of the line.
     * <p>
     * The function receive X value and check if he is in the range
     * of the line.
     * </p>
     *
     * @param x type double.
     * @return true if the X in the range of the line, false otherwise,
     * type boolean.
     */
    public boolean isInRangeX(double x) {

        //Check if the X value is in the range of the line.
        return (this.start().getX() >= x && this.end().getX() <= x);
    }

    /**
     * Check if Y value in the range of the line.
     * <p>
     * The function receive Y value and check if he is in the range
     * of the line.
     * We need to check if the Y is between the two sides of the points,
     * because we arrange the start and the end point of the lines by
     * there X value.
     * </p>
     *
     * @param y type double.
     * @return true if the Y in the range of the line, false otherwise,
     * type boolean.
     */
    public boolean isInRangeY(double y) {

        //Check if the Y value is in the range of the line.
        return (CompareWithEpsilon.compare(this.start().getY(), y) >= 0
                && CompareWithEpsilon.compare(this.end().getY(), y) <= 0)
                || (CompareWithEpsilon.compare(this.start().getY(), y) <= 0
                && CompareWithEpsilon.compare(this.end().getY(), y) >= 0);
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Calculate the intersection point of the lines.
     * <p>
     * The function receive two lines and returns the intersection
     * point of the lines.
     * If there is not intersection point between the lines the function
     * return null.
     * </p>
     *
     * @param other type game.build.geometric.Line.
     * @return true if the lines are intersect, false otherwise, type game.build.geometric.Point.
     */
    public Point intersectionWith(Line other) {

        //Check if the first line is a point.
        if (this.isPoint) {
            return other.intersectionWithPoint(this.getP1());
        }

        //Check if the second line is a point.
        if (other.isPoint) {
            return this.intersectionWithPoint(other.getP1());
        }

        //Check if the first line is parallel to Y and the other parallel to X.
        if (this.isParallelY && other.getM() == 0 && !other.getIsParallelY()) {

            //Check if the intersection point is in the range of the lines.
            return (other.isInRangeX(this.getP1().getX())
                    && this.isInRangeY(other.getP1().getY()))
                    ? new Point(this.getP1().getX(), other.getP1().getY())
                    : null;
        }

        //Check if the first line is parallel to X and the other parallel to Y.
        if (other.getIsParallelY() && this.getM() == 0 && !this.isParallelY) {

            //Check if the intersection point is in the range of the lines.
            return (this.isInRangeX(other.getP1().getX())
                    && other.isInRangeY(this.getP1().getY()))
                    ? new Point(other.getP1().getX(), this.getP1().getY())
                    : null;
        }

        //Check if the first line is parallel to Y and the other parallel to Y.
        if (this.isParallelY && other.isParallelY) {

            /*
            Check if first line is the continue of the second line.
            if not check if the second line is the continue of the first line.
             */
            if (this.start().equals(other.end())) {
                return this.start();
            } else if (this.end().equals(other.start())) {
                return this.end();
            }
            return null;
        }
        //Check if the determinant of the lines is equals.
        if (CompareWithEpsilon.compare(this.m, other.getM()) == 0) {

            //Check if the cutting with X axis of the lines is different.
            if (CompareWithEpsilon.compare(this.b, other.getB()) != 0) {
                return null;
            }

            /*
            Check if first line is the continue of the second line.
            if not check if the second line is the continue of the first line.
             */
            if (this.start().equals(other.end())) {
                return this.start();
            } else if (other.start().equals(this.end())) {
                return this.end();
            }
            return null;
        }

        double x;
        double y;

        /*
        Check if the first line is parallel to axis.
        if not check if the second line is parallel to axis.
         */
        if (this.isParallelY) {
            //So the X of the intersection point is the X of the first line.
            x = this.getP1().getX();

            //Calculate the Y value of the intersection point with the X value.
            y = (x * this.m) + this.b;
        } else if (other.isParallelY) {

            //So the X of the intersection point is the X of the second line.
            x = other.getP1().getX();

            //Calculate the Y value of the intersection point with the X value.
            y = (x * this.m) + this.b;
        } else if (this.m == 0) {

            //So the Y of the intersection point is the Y of the first line.
            y = this.getP1().getY();

            //Calculate the X value of the intersection point with the Y value.
            x = (y - other.getB()) / other.getM();
        } else if (other.getM() == 0) {

            //So the Y of the intersection point is the Y of the second line.
            y = other.getP1().getY();

            //Calculate the X value of the intersection point with the Y value.
            x = (y - this.getB()) / this.getM();
        } else {

            //So non of the lines are parallel and we can calculate the X value.
            x = (other.getB() - this.b) / (this.m - other.getM());

            //Calculate the Y value of the intersection point with the X value.
            y = (x * this.m) + this.b;
        }

        //Check if the intersection point in the range of the lines.
        if ((this.isInRangeX(x)) && other.isInRangeX(x) && this.isInRangeY(y)
                && other.isInRangeY(y)) {
            return new Point(x, y);
        }
        return null;
    }


    /**
     * Check if there is intersection point of line and point.
     * <p>
     * The function receive line and a point and check if there is
     * intersection point between the line and the point.
     * </p>
     *
     * @param other type game.build.geometric.Point.
     * @return game.build.geometric.Point if there is intersection point, null else.
     */
    private Point intersectionWithPoint(Point other) {

        //Check if the first line is point to.
        if (this.isPoint) {

            //Check if the two point are equals.
            if (this.getP1().equals(other)) {
                return other;
            }
            return null;
        }

        //Check if the point in the range of the line.
        if (!(this.isInRangeX(other.getX()) && this.isInRangeY(other.getY()))) {
            return null;
        }

        /*
        The point in range so if the line is parallel to axis Y there is
        intersection.
        */
        if (this.isParallelY) {
            return other;
        }

        //Check if the point on the line.
        if (CompareWithEpsilon.compare(other.getY(),
                (this.getM() * other.getX()) + this.getB()) == 0) {
            return other;
        }
        return null;
    }

    /**
     * Check what the closest intersection point to start of line.
     * <p>
     * The function receive rectangle and a point and check if there is
     * intersection point between the four lines (sides) of the rectangle
     * and the point and return the closest intersect point to the line.
     * </p>
     *
     * @param rect type game.build.geometric.Rectangle.
     * @return The closest intersection point to start of line,
     * null if there is no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        //Create list of the intersection points, there can be at max 2 points.
        List<Point> intersectionPoints = rect.intersectionPoints(this);

        //If there is no point in the list so there is no intersection.
        if (intersectionPoints.size() == 0) {
            return null;

            //If there is one point. its the closest.
        } else if (intersectionPoints.size() == 1) {
            return intersectionPoints.get(0);

            //There is two points, return the closest to the start of the line.
        } else {

            //To compare between the distance we use game.build.calculators.CompareWithEpsilon method.
            return (CompareWithEpsilon.compare(
                    intersectionPoints.get(0).distance(this.getP1()),
                    intersectionPoints.get(1).distance(this.getP1()))) >= 0
                    ? intersectionPoints.get(1) : intersectionPoints.get(0);
        }
    }
}