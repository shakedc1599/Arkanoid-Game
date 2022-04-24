package game.build.geometric;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.build.calculators.Collidable;
import game.build.calculators.CompareWithEpsilon;
import game.build.calculators.Sprite;
import game.build.calculators.Velocity;
import game.gameflow.GameLevel;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.geometric.Paddle object (Rectangle, color and KeyboardSensor) with: moveLeft,
 * moveRight, timePassed, drawOn, getCollisionRectangle, addToGame, hit,
 * collisionVelocity methods.
 */
public class Paddle implements Sprite, Collidable {

    private Rectangle rect;
    private java.awt.Color color;
    private KeyboardSensor keyboard;

    private static final int LEFTBOUTDER = 30;
    private static final int RIGHTBOURDER = 770;
    private static final int ONESTEP = 10;

    /**
     * constructor
     * <p>
     * The function receive rectangle, color and keyboard sensor and insert into
     * new game.build.geometric.Paddle.
     * </p>
     *
     * @param rect     type game.build.geometric.Rectangle, give the location width and height.
     * @param color    type java.awt.Color.
     * @param keyboard type KeyboardSensor.
     */
    public Paddle(Rectangle rect, java.awt.Color color, KeyboardSensor keyboard) {
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * @return the paddle rectangle, type game.build.geometric.Rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Move the paddle one step to the left.
     */
    public void moveLeft() {

        //Check if we got to the left border, if so we dont move.
        if (CompareWithEpsilon.compare(this.rect.getUpperLeft().getX(),
                LEFTBOUTDER) >= 0) {

            //Calculate the new location of the upper left point of the paddle.
            Point newPoint = new Point(this.rect.getUpperLeft().getX() - ONESTEP,
                    this.rect.getUpperLeft().getY());

            //Set the new location.
            this.rect.setUpperLeft(newPoint);
        }
    }

    /**
     * Move the paddle one step to the right.
     */
    public void moveRight() {

        //Check if we got to the right border, if so we dont move.
        if (CompareWithEpsilon.compare(this.rect.getUpperRight().getX(),
                RIGHTBOURDER) <= 0) {

            //Calculate the new location of the upper left point of the paddle.
            Point newPoint = new Point(this.rect.getUpperLeft().getX()
                    + ONESTEP, this.rect.getUpperLeft().getY());

            //Set the new location.
            this.rect.setUpperLeft(newPoint);

        }
    }

    /**
     * Notify the paddle that time has passed.
     * <p>
     * The function check if the left or right key are pressed, and if so
     * call the proper move method.
     * </p>
     */
    @Override
    public void timePassed() {

        //Check if the user press on the LEFT key
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();

            //Check if the user press on the RIGHT key
        } else {
            if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                moveRight();
            }
        }
    }

    /**
     * Draw the paddle on the given DrawSurface.
     * <p>
     * The function receive surface and draw upon it the given paddle.
     * </p>
     *
     * @param surface type DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface surface) {

        //Set the paddle color.
        surface.setColor(this.color);

        //Draw the paddle.
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(), (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
    }

    /**
     * Add the paddle to the game.
     *
     * @param g type game.setup.GameLevel.
     */
    public void addToGame(GameLevel g) {

        //Add to the collidable list.
        g.addCollidable(this);

        //Add to the sprite list.
        g.addSprite(this);
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Calculate new velocity after collision.
     * <p>
     * The function receive collision point and the current ball velocity and
     * calculate the new ball velocity.
     * </p>
     *
     * @param collisionPoint  , type game.build.geometric.Point.
     * @param currentVelocity , type game.build.calculators.Velocity.
     * @return the new velocity. type game.build.calculators.Velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        /*
        Check if the collision point is on the upper/lower line
        (if the y's value are equals).
         */
        if (CompareWithEpsilon.compare(collisionPoint.getY(),
                this.rect.getUpperLeft().getY()) == 0
                || CompareWithEpsilon.compare(collisionPoint.getY(),
                this.rect.getLowerRight().getY()) == 0) {

            //If the x value of the point in the range of the rectangle lines.
            if (CompareWithEpsilon.compare(this.rect.getUpperLeft().getX(),
                    collisionPoint.getX()) <= 0
                    && CompareWithEpsilon.compare(collisionPoint.getX(),
                    this.rect.getLowerRight().getX()) <= 0) {

                //Return the new velocity by collisionVelocity method.
                return collisionVelocity(collisionPoint, currentVelocity);
            }
        }

        /*
        Check if the collision point is on the sides of the paddle.
        (if the x's value are equals).
         */
        if (CompareWithEpsilon.compare(collisionPoint.getX(),
                this.rect.getUpperLeft().getX()) == 0
                || CompareWithEpsilon.compare(collisionPoint.getX(),
                this.rect.getUpperRight().getX()) == 0) {

            //If the y value of the point in the range of the rectangle lines.
            if (CompareWithEpsilon.compare(this.rect.getUpperLeft().getY(),
                    collisionPoint.getY()) <= 0
                    && CompareWithEpsilon.compare(collisionPoint.getY(),
                    this.rect.getLowerRight().getY()) <= 0) {

                //Return the new velocity, change the dx value.
                return new Velocity(-currentVelocity.getDx(),
                        currentVelocity.getDy());
            }
        }

        //So there is no collision, return the current velocity.
        return currentVelocity;
    }

    /**
     * Calculate new velocity after collision at the upper/lower lines.
     * <p>
     * The function receive collision point and the current ball velocity and
     * calculate the new ball velocity after collision at the upper/lower line
     * of the paddle.
     * </p>
     *
     * @param colPoint        , type game.build.geometric.Point.
     * @param currentVelocity , type game.build.calculators.Velocity.
     * @return the new velocity. type game.build.calculators.Velocity.
     */
    private Velocity collisionVelocity(Point colPoint, Velocity currentVelocity) {

        //Calculate the len of fifth of the paddle.
        double fifthPaddle = (this.rect.getUpperRight().getX()
                - this.rect.getUpperLeft().getX()) / 5;

        //Get the lower x value.
        double startX = this.rect.getUpperLeft().getX();

        //If in the first region.
        if (startX + fifthPaddle >= colPoint.getX()) {

            //Calculate the new velocity from the new angle and current speed.
            return Velocity.fromAngleAndSpeed(-60,
                    currentVelocity.getSize());
        }

        //If in the second region.
        if (startX + (2 * fifthPaddle) >= colPoint.getX()) {

            //Calculate the new velocity from the new angle and current speed.
            return Velocity.fromAngleAndSpeed(-30,
                    currentVelocity.getSize());
        }

        //If in the third region.
        if (startX + (3 * fifthPaddle) >= colPoint.getX()) {

            //Calculate the new velocity from the new angle and current speed.
            return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }

        //If in the second region.
        if (startX + (4 * fifthPaddle) >= colPoint.getX()) {

            //Calculate the new velocity from the new angle and current speed.
            return Velocity.fromAngleAndSpeed(30,
                    currentVelocity.getSize());
        }

        //So collision point in the fifth region.
        return Velocity.fromAngleAndSpeed(60, currentVelocity.getSize());
    }
}