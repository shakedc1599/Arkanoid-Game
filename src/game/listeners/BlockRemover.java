package game.listeners;

import game.build.geometric.Ball;
import game.build.geometric.Block;
import game.build.ongame.Counter;
import game.gameflow.GameLevel;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * In charge of removing blocks from the game, as well as keeping count of the
 * number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor
     * <p>
     * The function receive game and game.build.ongame.Counter and create new game.listeners.BlockRemover.
     * </p>
     *
     * @param game          type game.setup.GameLevel.
     * @param removedBlocks type game.build.ongame.Counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed.
     *
     * @param beingHit type game.build.geometric.Block.
     * @param hitter   type game.build.geometric.Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        //remove this listener from the block.
        beingHit.removeHitListener(this);

        //Remove the hit block from the game.
        beingHit.removeFromGame(this.game);

        //subtract number from current count.
        this.remainingBlocks.decrease(1);

    }
}