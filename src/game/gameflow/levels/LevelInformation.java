package game.gameflow.levels;

import game.build.calculators.Sprite;
import game.build.calculators.Velocity;
import game.build.geometric.Block;

import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * interface LevelInformation with: numberOfBalls, initialBallVelocities,
 * paddleSpeed, paddleWidth, levelName, getBackground, blocks and
 * numberOfBlocksToRemove methods.
 */
public interface LevelInformation {

    /**
     *
     * @return the number of balls in the game, type int.
     */
    int numberOfBalls();

    /**
     *
     * @return list of the balls velocities, type List<Velocity>.
     */
    List<Velocity> initialBallVelocities();

    /**
     *
     * @return the speed of the paddle, type int.
     */
    int paddleSpeed();

    /**
     *
     * @return the width of the paddle, type int.
     */
    int paddleWidth();

    /**
     *
     * @return the level name, type String.
     */
    String levelName();

    /**
     *
     * @return a sprite with the background of the level, type Sprite.
     */
    Sprite getBackground();

    /**
     * Create the Blocks that make up this level.
     *
     * @return  list of blocks , type  List<Block>.
     */
    List<Block> blocks();

    /**
     *
     * @return number of blocks that should be removed before the level is
     * considered to be "cleared", type int.
     */
    int numberOfBlocksToRemove();
}
