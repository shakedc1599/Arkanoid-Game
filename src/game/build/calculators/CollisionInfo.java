package game.build.calculators;

import game.build.geometric.Point;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.calculators.CollisionInfo object (collision game.build.geometric.Point and collision Object) with:
 * collisionPoint, collisionObject, setCollisionPoint, setCollisionObject
 * methods.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor
     * <p>
     * The function receive rectangle, color and keyboard sensor and insert into
     * new game.build.geometric.Paddle.
     * </p>
     *
     * @param collisionPoint  , type game.build.geometric.Point.
     * @param collisionObject , type game.build.calculators.Collidable.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {

        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {

        return this.collisionObject;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Set new collision point.
     *
     * @param newCollisionPoint , type game.build.geometric.Point.
     */
    public void setCollisionPoint(Point newCollisionPoint) {
        this.collisionPoint = newCollisionPoint;
    }

    /**
     * Set new collision object.
     *
     * @param newCollisionObject , type game.build.calculators.Collidable.
     */
    public void setCollisionObject(Collidable newCollisionObject) {
        this.collisionObject = newCollisionObject;
    }
}