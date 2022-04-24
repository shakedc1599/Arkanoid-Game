package game.listeners;

import game.build.geometric.Ball;
import game.build.geometric.Block;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Interface game.listeners.HitListener with: hitEvent methods.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the game.build.geometric.Ball that's doing the hitting.
     *
     * @param beingHit type game.build.geometric.Block.
     * @param hitter   type game.build.geometric.Ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}