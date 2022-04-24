package game.build.geometric;

import biuoop.DrawSurface;
import game.build.calculators.Collidable;
import game.build.calculators.CompareWithEpsilon;
import game.build.calculators.Sprite;
import game.build.calculators.Velocity;
import game.listeners.HitListener;
import game.listeners.HitNotifier;
import game.gameflow.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.geometric.Block object (Rectangle and color) with: hit, timePassed, drawOn,
 * getCollisionRectangle, addToGame methods.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle rect;
    private java.awt.Color color;

    /**
     * constructor
     * <p>
     * The function receive rectangle and color and create new game.build.geometric.Block.
     * </p>
     *
     * @param rect     type game.build.geometric.Rectangle, give the location width and height.
     * @param color    type java.awt.Color.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
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
     * Draw the block on the given DrawSurface.
     * <p>
     * The function receive surface and draw upon it the given block.
     * to create the black frame we draw black block in the size of the original
     * block and smaller block within it.
     * </p>
     *
     * @param surface type DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface surface) {

        //Upper left point values.
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();

        //block dimensions.
        int width = (int) this.rect.getWidth();
        int height = (int) this.rect.getHeight();

        //The width of the outline.
        int outline = 2;

        //Drawing the outline.
        surface.setColor(Color.BLACK);
        surface.fillRectangle(x, y, width, height);

        //Drawing the block.
        surface.setColor(this.color);
        surface.fillRectangle(x + (outline / 2), y + (outline / 2),
                width - outline, height - outline);
    }

    /**
     * Notify the block that time has passed.
     * <p>
     * The function do nothing. only here because the game.build.calculators.Sprite implement.
     * </p>
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add the block to the game.
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
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity)    {

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

                //Change the dy value.
                currentVelocity.setDy(-currentVelocity.getDy());
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
                currentVelocity.setDx(-currentVelocity.getDx());
            }
        }

        //Notify the hitter.
        this.notifyHit(hitter);

        //Return the new velocity.
        return currentVelocity;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Remove the block from the game.
     *
     * @param game type game.setup.GameLevel.
     */
    public void removeFromGame(GameLevel game) {

        //remove from the sprite list.
        game.removeSprite(this);

        //remove from the collidable list.
        game.removeCollidable(this);

    }

    /**
     * Notify all the listeners in the list that hit occur.
     *
     * @param hitter type Ball.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add game.listeners.HitListener as a listener to hit events.
     *
     * @param hl type game.listeners.HitListener.
     */
    @Override
    public void addHitListener(HitListener hl) {

        this.hitListeners.add(hl);
    }

    /**
     * Remove game.listeners.HitListener from the list of listener to hit events.
     *
     * @param hl type game.listeners.HitListener.
     */
    @Override
    public void removeHitListener(HitListener hl) {

        this.hitListeners.remove(hl);
    }
}
