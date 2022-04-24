package game.build.calculators;

import game.build.geometric.Line;
import game.build.geometric.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.calculators.GameEnvironment object (list of game.build.calculators.Collidable) with: addCollidable,
 * getClosestCollision, getBlocksList methods.
 */
public class GameEnvironment {

    private List<Collidable> blocks;

    /**
     * Constructor
     * <p>
     * The function create new ArrayList.
     * </p>
     */
    public GameEnvironment() {

        this.blocks = new ArrayList<>();
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * @return the list of the objects.
     */
    public List<Collidable> getBlocksList() {
        return this.blocks;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Add the given collidable to the environment.
     *
     * @param c , type game.build.calculators.Collidable.
     */
    public void addCollidable(Collidable c) {

        this.blocks.add(c);
    }

    /**
     * Remove the given collidable from the environment.
     *
     * @param c , type game.build.calculators.Collidable.
     */
    public void removeCollidable(Collidable c) {

        this.blocks.remove(c);
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Return the closest collision to line.
     * <p>
     * The function receive line and check what is the closest collation
     * point to object in the game.build.calculators.Collidable list.
     * </p>
     *
     * @param trajectory , type game.build.geometric.Line.
     * @return the closest collision point, if there is not return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        //Check if there is no object in the list.
        if (this.blocks.size() == 0) {
            return null;
        }

        //Temp game.build.calculators.CollisionInfo object.
        CollisionInfo closest = new CollisionInfo(null, null);

        //Run trough the objects in the list.
        for (Collidable block : this.blocks) {

            //Get the collation point between the object and the line.
            Point collisionPoint =
                    trajectory.closestIntersectionToStartOfLine(
                            block.getCollisionRectangle());

            //Check if there is no collation point.
            if (collisionPoint == null) {
                continue;
            }

            //Check if closest still null.
            if (closest.collisionPoint() == null) {

                //Update closest.
                closest.setCollisionPoint(collisionPoint);
                closest.setCollisionObject(block);

                /*
                Check if the previous point in closest, closest to the line from
                 the new collision point.
                 */
            } else if (closest.collisionPoint().distance(trajectory.getP1())
                    <= collisionPoint.distance(trajectory.getP1())) {

                //Update closest.
                closest.setCollisionPoint(collisionPoint);
                closest.setCollisionObject(block);
            }
        }

        //Return closest.
        return closest;
    }
}