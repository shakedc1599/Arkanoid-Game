package game.listeners;

import game.build.geometric.Ball;
import game.build.geometric.Block;
import game.build.ongame.Counter;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.listeners.ScoreTrackingListener from game.build.ongame.Counter
 * implement game.listeners.HitListener, with: hitEvent methods.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    private static final int SCOREPERBLOCK = 5;

    /**
     * Constructor.
     * <p>
     * The function receive counter and create game.listeners.ScoreTrackingListener.
     * </p>
     *
     * @param scoreCounter type game.build.ongame.Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the game.build.geometric.Ball that's doing the hitting.
     *
     * @param beingHit type game.build.geometric.Block.
     * @param hitter   type game.build.geometric.Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        this.currentScore.increase(SCOREPERBLOCK);
    }
}