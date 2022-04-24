package game.listeners;

import game.build.geometric.Ball;
import game.build.geometric.Block;
import game.build.ongame.Counter;
import game.gameflow.GameLevel;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Responsible for removing a balls that hits the death block, as well as
 * keeping count of the number of balls that remain.
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor
     * <p>
     * The function receive game and game.build.ongame.Counter and create new game.listeners.BallRemover.
     * </p>
     *
     * @param game         type game.setup.GameLevel.
     * @param removedBalls type game.build.ongame.Counter.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * game.build.geometric.Ball that hit the "death" block are remove from the game.
     *
     * @param beingHit type game.build.geometric.Block.
     * @param hitter   type game.build.geometric.Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        //Remove the hitter ball from the game.
        hitter.removeFromGame(this.game);

        //Subtract the number of balls from current count.
        this.remainingBalls.decrease(1);
    }

}