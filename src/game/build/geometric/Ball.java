package game.build.geometric;

import biuoop.DrawSurface;
import game.build.calculators.Collidable;
import game.build.calculators.CollisionInfo;
import game.build.calculators.GameEnvironment;
import game.build.calculators.Velocity;
import game.build.calculators.Sprite;
import game.build.calculators.CompareWithEpsilon;
import game.listeners.HitListener;
import game.gameflow.GameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.geometric.Ball object from game.build.geometric.Point (center) radius and color with: getX, getY,
 * getSize, getColor, drawOn, setVelocity, moveOneStep, getGameEnvironment,
 * velocityAfterCollision, timePassed, addToGame and isInBlock methods.
 */
public class Ball implements Sprite {

    private List<HitListener> hitListeners;
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * Constructor.
     * <p>
     *     The function receive two Points and create new line with them.
     * </p>
     * @param center type game.build.geometric.Point, the center point of the ball.
     * @param radius type int, radius of the ball.
     * @param color type java.awt.Color, the color of the ball.
     * @param environment game.build.calculators.GameEnvironment, the environment of the ball.
     */
    public Ball(Point center, int radius, java.awt.Color color,
                GameEnvironment environment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.environment = environment;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     * <p>
     *     The function receive two Points and create new line with them.
     * </p>
     * @param x type int, x value of the center point.
     * @param y type int, y value of the center point.
     * @param radius type int, radius of the ball.
     * @param color type java.awt.Color, the color of the ball.
     * @param environment game.build.calculators.GameEnvironment, the environment of the ball.
     */
    public Ball(int x, int y, int radius, java.awt.Color color,
                GameEnvironment environment) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.environment = environment;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * @return the environment values of the ball, type getGameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * @return the center point values of the center point of the ball,
     * type game.build.geometric.Point.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return the x values of the center point of the ball, type int.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y values of the center point of the ball, type int.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the size of the ball, type int.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color of the ball, type java.awt.Color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return the velocity of the ball, type game.build.calculators.Velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Set the ball velocity by game.build.calculators.Velocity parameter.
     * @param v type game.build.calculators.Velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set the ball velocity by dx,dy parameters.
     * @param dx type double.
     * @param dy type double.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Change the location of the ball one step ahead.
     * <p>
     *     The function move the ball one step foreword by the ball velocity.
     *     the function use velocityAfterCollision method to calculate the ball
     *     new velocity.
     * </p>
     */
    public void moveOneStep() {

        //Check if the there is no velocity.
        if (this.velocity == null) {
            return;
        }

        //Compute the ball trajectory.
        Line trajectory = new Line(this.center,
                this.getVelocity().applyToPoint(this.center));

        //Calculate the new velocity by the hit and the collision point.
        Velocity newVelocity = velocityAfterCollision(trajectory);

        //Move the ball.
        this.center = this.getVelocity().applyToPoint(this.center);

        //Set the new velocity.
        this.setVelocity(newVelocity);
    }

    /**
     * Notify the ball that time has passed.
     * <p>
     *     The function call isInBlock method to check if the ball stack in
     *     block and then call to moveOneStep.
     * </p>
     */
    public void timePassed()  {

        isInBlock();
        moveOneStep();
    }

    /**
     * Add the ball to the game.
     *
     * @param g type game.setup.GameLevel.
     */
    public void addToGame(GameLevel g) {

        g.addSprite(this);
    }

    /**
     * Draw the ball on the given DrawSurface.
     * <p>
     *     The function receive surface and draw upon it the given ball.
     * </p>
     * @param surface type DrawSurface.
     */
    public void drawOn(DrawSurface surface) {

        //Set the ball color.
        surface.setColor(this.getColor());

        //Draw the ball.
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(),
                this.radius);
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Check if the ball stack in block, if so get him out.
     * <p>
     *     The function check if the ball stack in one of the blocks in the
     *     game, if so we check where is the block (frame, paddle or ordinary
     *     block) and drop him out.
     * </p>
     */
    private void isInBlock() {

        //Create list of the blocks in the game.
        List<Collidable> blocks = this.environment.getBlocksList();

        //Run through the blocks and check if the ball stack in one of them.
        for (Collidable block : blocks) {

            //Calculate the sizes of the block.
            double minX = block.getCollisionRectangle().getUpperLeft().getX();
            double maxX = block.getCollisionRectangle().getUpperRight().getX();
            double minY = block.getCollisionRectangle().getUpperLeft().getY();
            double maxY = block.getCollisionRectangle().getLowerLeft().getY();

            //Calculate the ball location.
            double ballX = this.center.getX();
            double ballY = this.center.getY();

            //Check if the ball in the block.
            if (ballX > minX && ballX < maxX && ballY > minY && ballY < maxY) {

                //Check if the block is the upper border.
                if (minY - 5 == 0) {

                    this.center = new Point((maxX + minX) / 2, maxY + 10);
                }

                //Check if the block is the lower border.
                if (maxY + 5 == 600) {

                    this.center = new Point((maxX + minX) / 2, minY - 10);
                }

                //Else the block is the paddle or an ordinary block.
                this.center = new Point((maxX + minX) / 2, minY - 5);

            }
        }
    }

    /**
     * Calculate the ball velocity by collision.
     * <p>
     * The function checks if there is a collision point in the next step of the
     * ball, then the function calculates the new velocity of the ball according
     * to the hit and calls itself again.
     * </p>
     @param trajectory , type game.build.geometric.Line.
     @return the ball new velocity. type game.build.calculators.Velocity.
     */
    private Velocity velocityAfterCollision(Line trajectory) {

        //Get the closest collision point.
        CollisionInfo collision = environment.getClosestCollision(trajectory);

        //Check if there is not collision point.
        if (collision.collisionPoint() == null) {
            return this.velocity;
        }

        //Calculate the velocity after the collision.
        Velocity nextVelocity =
                collision.collisionObject().hit(this, collision.collisionPoint(), velocity);

        //Calculate the distance to the collision point.
        double distance = this.center.distance(collision.collisionPoint());

        //Check if the distance smaller from the radius.
        if (CompareWithEpsilon.compare(distance, radius) <= 0) {

            //Set the velocity to the new location.
            this.setVelocity(nextVelocity);

            //Create the new trajectory.
            trajectory =
                    new Line(this.center, nextVelocity.applyToPoint(this.center));

            //Call the function again with the new trajectory.
            nextVelocity = velocityAfterCollision(trajectory);
            return nextVelocity;
        }

        //Calculate the velocity if the distance is bigger than the radius.
        this.setVelocity(this.velocity.calculateNewVelocity(distance - radius));

        //Return the new velocity.
        return nextVelocity;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Remove the ball from the game.
     *
     * @param game type game.setup.GameLevel.
     */
    public void removeFromGame(GameLevel game) {

        //remove from the sprite list.
        game.removeSprite(this);

    }

}
