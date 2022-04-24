package game.build.calculators;

import game.build.geometric.Ball;
import game.build.geometric.Point;
import game.build.geometric.Rectangle;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Interface game.build.calculators.Collidable with: getCollisionRectangle and hit methods.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Calculate new velocity after collision.
     * <p>
     * Notify the object that we collided with it at collisionPoint with a
     * give velocity. The return is the new velocity expected after the hit
     * (based on the force the object inflicted on us).
     * </p>
     *
     * @param collisionPoint  , type game.build.geometric.Point.
     * @param currentVelocity , type game.build.calculators.Velocity.
     * @param hitter          , type game.build.geometric.Ball.
     * @return the new velocity. type game.build.calculators.Velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}