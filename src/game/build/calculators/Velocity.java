package game.build.calculators;

import game.build.geometric.Point;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.calculators.Velocity object from dx,dy with: applyToPoint , getDx, getDy, getSize
 * fromAngleAndSpeed, calculateNewVelocity, setDx, setDy methods.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * constructor
     * <p>
     * The function receive two double and insert into new game.build.calculators.Velocity.
     * </p>
     *
     * @param dx type double, width.
     * @param dy type double, height.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * @return the Dx values of the received game.build.calculators.Velocity, type double.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the Dy values of the received game.build.calculators.Velocity, type double.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @return the size of the received game.build.calculators.Velocity, type double.
     */
    public double getSize() {

        return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Take a point and return a new point with position with the velocity.
     * <p>
     * The function receive point with position (x,y) and return a new point
     * with position (x+dx, y+dy.
     * </p>
     *
     * @param p type game.build.geometric.Point.
     * @return the point with the velocity.
     */
    public Point applyToPoint(Point p) {

        //Return the point with the dx,dy.
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }

    /**
     * Set new dx to the velocity.
     *
     * @param newDx , type double.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * Set new dy to the velocity.
     *
     * @param newDy , type double.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * The function gets angle and speed and return the velocity.
     * <p>
     * The function gets angle and speed and converts them to dx, dy and
     * return new game.build.calculators.Velocity by them.
     * </p>
     *
     * @param angle type double.
     * @param speed type double.
     * @return the game.build.calculators.Velocity by the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        //Calculate the dx from the angle and speed.
        double dx = Math.cos(Math.toRadians(angle - 90)) * speed;

        //Calculate the dy from the angle and speed.
        double dy = Math.sin(Math.toRadians(angle - 90)) * speed;

        //Return the new velocity.
        return new Velocity(dx, dy);
    }

    /**
     * The function size and return the velocity minos this size.
     * <p>
     * The function gets size and calculate the new velocity, the current
     * velocity minos the size.
     * </p>
     *
     * @param size type double.
     * @return the game.build.calculators.Velocity by the angle and speed.
     */
    public Velocity calculateNewVelocity(double size) {

        //Calculate new radius.
        double radius = size / this.getSize();

        //Return the new velocity.
        return new Velocity(radius * this.getDx(), radius * this.getDy());
    }
}
